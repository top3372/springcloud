package com.haili.ins.common.utils.security;

import com.haili.ins.common.utils.HexUtil;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



/**
 * <p>
 * Title: <B>AES encryption and decryption using Java Crypto </B>
 * </p>
 * <p>
 * Description:
 * </p>
 * This class provides a decryption and encryption service based on AES
 * (Advanced Encryption Standard). It always generates a key on encryption. On
 * decryption, it uses the embedded key. As such, it is not really secure and
 * only prevents simple attacks. In addition, the channel on which it is being
 * used is supposed to be secure.
 * <p>
 * It is similar to <code>DESUtil</code>. We can merge them to a new class named
 * CryptoUtil in the future.
 */
public final class AESUtil {

	private AESUtil() {
	}

	private static final String ALGORITHM_OPTIONS = "AES/ECB/PKCS5Padding";

	private static final String ALGORITHM_KEY = "AES";

	private static final String ALGORITHM_ENCODING = "UTF-8";

	/**
	 * 生成密钥
	 * @param size  must be equal to 128, 192 or 256
	 * @return
	 */
	public static String generateKey(int size) {

		// generate a secret key
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(ALGORITHM_KEY);

			kg.init(size); // keysize: must be equal to 128, 192 or 256
			SecretKey key = kg.generateKey();
			// get the encoded value for the string and convert to hex
			byte[] encodedKey = key.getEncoded();
			return HexUtil.toHexString(encodedKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Encrypts the provided clear text and returns encoded data as a string of
	 * hex char representations.
	 * 
	 * @param clearText
	 *            Clear text to be encrypted
	 * @return String with the encrypted data of hex char.
	 */
	public static String encrypt(String clearText) {
		String keyValue = null;
		try {
			// generate a secret key
			KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_KEY);
			kg.init(128); // keysize: must be equal to 128, 192 or 256
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
	 * @param encryptedText
	 *            encrypted text to be decrypted
	 * @return String with the decrypted data.
	 */
	public static String decrypt(String encryptedText) {
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
}
