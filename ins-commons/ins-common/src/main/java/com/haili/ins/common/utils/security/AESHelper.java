package com.haili.ins.common.utils.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * SER20150203999 by zhy6060 易捷接入
 *
 * @author zhy6060
 */
public class AESHelper {

    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    public static final String IV = "\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0";

    private Cipher cipher;

    private SecretKey secretKey;
    private IvParameterSpec ivspec;

    public AESHelper() {
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);

            secretKey = null;
            ivspec = new IvParameterSpec(IV.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public void initKey(String password) {

        secretKey = new SecretKeySpec(password.getBytes(), "AES");
    }

    public void initCipher(String transformation) {
        try {
            cipher = Cipher.getInstance(transformation);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String plaintext) throws UnsupportedEncodingException {
        // 转换得到字节流
        // SER20150203999 begin
        // byte[] data = plaintext.getBytes("GBK");//modi by wuyuelei 20140704 因为编码问题，强制改成GBK
        byte[] data = plaintext.getBytes("GB18030");//modi by wuyuelei 20140704 因为编码问题，强制改成GBK
        // SER20150203999 end

        // 输出buffer
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream();

        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

            byte[] encryptedBlock = cipher.doFinal(data);
            // 追加结果到输出buffer中
            outbuf.write(encryptedBlock);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return Base64.encodeBase64String(outbuf.toByteArray()); // ciphertext
    }

    public String decrypt(String cryptedBase64Str) {
        // 转换得到字节流
        byte[] data = Base64.decodeBase64(cryptedBase64Str);

        // 输出buffer
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream();

        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

            byte[] decryptedBlock = cipher.doFinal(data);
            // 追加结果到输出buffer中
            outbuf.write(decryptedBlock);
            outbuf.flush();
            outbuf.close();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        try {
            // SER20150203999 begin
            // return outbuf.toString("GBK");//对解密后的数据指定输出编码格式GBK Created by liuzheng
            return outbuf.toString("GB18030");//对解密后的数据指定输出编码格式GBK Created by liuzheng
            // SER20150203999 end
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
