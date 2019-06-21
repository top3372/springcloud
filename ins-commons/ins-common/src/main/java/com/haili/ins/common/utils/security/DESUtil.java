package com.haili.ins.common.utils.security;

import com.haili.ins.common.utils.HexUtil;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * <p>
 * Title: <B>DES encryption and decryption using Java Crypto </B>
 * </p>
 * <p>
 * Description:
 * </p>
 * This class provides a decryption and encryption service based on DES (Data
 * Encryption Standard). It always generates a key on encryption. On decryption,
 * it uses the embedded key. As such, it is not really secure and only prevents
 * simple attacks. In addition, the channel on which it is being used is
 * supposed to be secure.
 */
public final class DESUtil {

    private DESUtil() {
    }

    private static final String ALGORITHM_OPTIONS = "DES/ECB/PKCS5Padding";

    private static final String ALGORITHM_KEY = "DES";

    private static final String ALGORITHM_ENCODING = "UTF-8";

    private static final String PASSWORD_CRYPT_KEY = "__PAY__DES__KINGO__";

    /**
     * Encrypts the provided clear text and returns encoded data as a string of
     * hex char representations.
     *
     * @param clearText Clear text to be encrypted
     * @return String with the encrypted data of hex char.
     */
    public static String encrypt(String clearText, String key) {
        String keyValue = null;

        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // 从原始密匙数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            // 一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance(ALGORITHM_KEY);
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(ALGORITHM_KEY);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
            // 现在，获取数据并加密
            // 正式执行加密操作
            keyValue = HexUtil
                    .toHexString(cipher.doFinal(clearText.getBytes()));
        } catch (Exception e) {
            keyValue = null;
            e.printStackTrace();
        }
        if (keyValue == null) {
            keyValue = "NOKEY " + clearText;
        }
        return keyValue;
    }

    public static String encrypt(String clearText) {
        return encrypt(clearText, PASSWORD_CRYPT_KEY);
    }

    public static String decrypt(String encryptedText) {
        return decrypt(encryptedText, PASSWORD_CRYPT_KEY);
    }

    public static String decrypt(String encryptedText, String key) {
        String clearText = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // 从原始密匙数据创建一个DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
            // 一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_KEY);
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(ALGORITHM_KEY);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
            // 现在，获取数据并解密
            // 正式执行解密操作
            byte[] dataPart = HexUtil.toByteArray(encryptedText);
            byte[] decryptedData = cipher.doFinal(dataPart);
            clearText = new String(decryptedData, ALGORITHM_ENCODING);
        } catch (Exception e) {
            clearText = null;
        }

        return clearText;
    }

    public static String encryptRandom(String clearText) {
        String keyValue = null;
        try {
            // generate a secret key
            KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_KEY);
            kg.init(56);
            SecretKey key = kg.generateKey();

            // get the encoded value for the string and convert to hex
            byte[] encodedKey = key.getEncoded();
            keyValue = HexUtil.toHexString(encodedKey);
            keyValue += " ";

            // run the encryption algorithm
            Cipher cipher = Cipher.getInstance(ALGORITHM_OPTIONS);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            keyValue += HexUtil.toHexString(cipher.doFinal(clearText
                    .getBytes(ALGORITHM_ENCODING)));

            /*
             * None of below exceptions should happen in runtime because we do
             * both the encoding and the decoding and we assert a very common
             * encrypt algorthm (DES). That is why we ignore them. Later on, we
             * can add log4j to log these unexpected exceptions.
             */
        } catch (IllegalStateException e) {
            keyValue = null;
        } catch (IllegalBlockSizeException e) {
            keyValue = null;
        } catch (BadPaddingException e) {
            keyValue = null;
        } catch (NoSuchAlgorithmException e) {
            keyValue = null;
        } catch (NoSuchPaddingException e) {
            keyValue = null;
        } catch (InvalidKeyException e) {
            keyValue = null;
        } catch (UnsupportedEncodingException e) {
            keyValue = null;
        }

        if (keyValue == null) {
            keyValue = "NOKEY " + clearText;
        }
        return keyValue;
    }

    /**
     * Decrypts the string, which is supposed to have been encrypted with above
     * encrypt function.
     *
     * @param encryptedText encrypted text to be decrypted
     * @return String with the decrypted data.
     */
    public static String decryptRandom(String encryptedText) {
        String clearText = null;
        // split the hex into key and data parts.
        int spaceLoc = encryptedText.indexOf(' ');
        String encryptKey = encryptedText.substring(0, spaceLoc);
        String encryptData = encryptedText.substring(spaceLoc + 1);

        // special case of no encryption
        if (encryptKey.equals("NOKEY")) {
            return encryptData;
        }

        // convert hex data to byte array
        byte[] keyPart = HexUtil.toByteArray(encryptKey);
        byte[] dataPart = HexUtil.toByteArray(encryptData);

        // reconstruct the secret key
        SecretKeySpec sks = new SecretKeySpec(keyPart, ALGORITHM_KEY);
        try {
            // decrypt using the recovered key spec
            Cipher cipher = Cipher.getInstance(ALGORITHM_OPTIONS);
            cipher.init(Cipher.DECRYPT_MODE, sks);
            byte[] decryptedData = cipher.doFinal(dataPart);
            clearText = new String(decryptedData, ALGORITHM_ENCODING);

        } catch (NoSuchAlgorithmException e) {
            clearText = null;
        } catch (NoSuchPaddingException e) {
            clearText = null;
        } catch (InvalidKeyException e) {
            clearText = null;
        } catch (IllegalStateException e) {
            clearText = null;
        } catch (IllegalBlockSizeException e) {
            clearText = null;
        } catch (BadPaddingException e) {
            clearText = null;
        } catch (UnsupportedEncodingException e) {
            clearText = null;
        }

        return clearText;
    }

    public static String generatorKey() throws Exception {
        String keyValue = null;
        // generate a secret key
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_KEY);
        kg.init(56);
        SecretKey key = kg.generateKey();

        // get the encoded value for the string and convert to hex
        byte[] encodedKey = key.getEncoded();
        keyValue = HexUtil.toHexString(encodedKey);
        return keyValue;
    }
}
