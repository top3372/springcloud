package com.haili.ins.common.utils.security;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.haili.ins.common.utils.HexUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class RSAUtil {

	public static final String ALGORITHM = "RSA";

	private int keySize = 0;

	private String transformation = null;

	private String signatureAlgorithm = null;

	private static RSAUtil instance = null;

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	static {
		// for load public/private key from *.pem files

		// JCE does not work correctly! WHY?

		if (Security.getProvider("BC") == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
	}

	private RSAUtil(int keySize, String transformation, String signatureAlgorithm) {
		super();
		this.keySize = keySize;
		this.transformation = transformation;
		this.signatureAlgorithm = signatureAlgorithm;
	}

	public static synchronized RSAUtil getInstance(int keySize) {
		if (instance == null) {
			instance = new RSAUtil(keySize, "RSA/ECB/PKCS1Padding", "SHA1withRSA");
		}
		return instance;
	}

	public static synchronized RSAUtil getInstance() {
		if (instance == null) {
			instance = new RSAUtil(1024, "RSA/ECB/PKCS1Padding", "SHA1withRSA");
		}
		return instance;
	}

	public KeyPair generateRandomKeyPair() throws RuntimeException {
		try {
			KeyPairGenerator keyPairGen = null;
			keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
			keyPairGen.initialize(keySize, new SecureRandom());
			KeyPair keyPair = keyPairGen.genKeyPair();
			return keyPair;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public KeyPair generateKeyPair(byte[] seed) throws RuntimeException {
		try {
			KeyPairGenerator keyPairGen = null;
			keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
			keyPairGen.initialize(keySize, new SecureRandom(seed));
			KeyPair keyPair = keyPairGen.genKeyPair();
			return keyPair;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static PrivateKey loadPrivateKey(File keyFile) throws RuntimeException {
		try {

			byte[] data = FileUtils.readFileToByteArray(keyFile);

			PKCS8EncodedKeySpec pkcs8Enc = new PKCS8EncodedKeySpec(data);

			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			return keyFactory.generatePrivate(pkcs8Enc);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

	public static PrivateKey loadPrivateKey(String keyPath) throws RuntimeException {
		File keyFile = new File(keyPath);
		return loadPrivateKey(keyFile);
	}

	@SuppressWarnings("resource")
	public static PrivateKey loadPrivateKeyFromPem(String keyPath) throws RuntimeException {

		BufferedReader bufReader = null;
		byte[] data = null;

		try {
			bufReader = new BufferedReader(new FileReader(keyPath));
			StringBuffer buf = new StringBuffer();
			String next = null;

			while ((next = bufReader.readLine()) != null) {
				if (next.indexOf("RSA PRIVATE KEY") != -1) {
					break;
				}
			}
			if (next == null) {
				throw new RuntimeException("Not exist [PRIVATE KEY] section " + "in private key file.");
			}

			next = bufReader.readLine();
			if (next == null) {
				throw new RuntimeException("Invalid [PRIVATE KEY] section .");
			}
			if (next.startsWith("Proc-Type: 4,ENCRYPTED")) {
				throw new RuntimeException("Not supported encrypted private key.");
			} else {
				buf.append(next);
			}

			while ((next = bufReader.readLine()) != null) {
				if (next.startsWith("-----END")) {
					break;
				}
				buf.append(next);
			}
			bufReader.close();
			data = Base64.decodeBase64(buf.toString().getBytes());
			PKCS8EncodedKeySpec pkcs8Enc = new PKCS8EncodedKeySpec(data);

			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PrivateKey priKey = keyFactory.generatePrivate(pkcs8Enc);
			return priKey;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	public static PublicKey loadPublicKey(String keyPath) throws RuntimeException {
		try {
			File file = new File(keyPath);
			FileInputStream in = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			in.read(data);
			in.close();

			X509EncodedKeySpec x509Enc = new X509EncodedKeySpec(data);

			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			return keyFactory.generatePublic(x509Enc);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

	public static PublicKey loadPublicKeyFromPemCert(String certPath) throws RuntimeException {
		try {
			InputStream bis = null;
			bis = new BufferedInputStream(new FileInputStream(certPath));
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			Certificate cert = cf.generateCertificate(bis);
			return cert.getPublicKey();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (CertificateException e) {
			throw new RuntimeException(e);
		}

	}

	public static void savePrivateKey(PrivateKey key, String keyPath) throws RuntimeException {
		try {
			FileOutputStream out = new FileOutputStream(new File(keyPath));
			PKCS8EncodedKeySpec pkcs8Enc = null;
			pkcs8Enc = new PKCS8EncodedKeySpec(key.getEncoded());
			out.write(pkcs8Enc.getEncoded());
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void savePublicKey(PublicKey key, String keyPath) throws RuntimeException {
		try {
			FileOutputStream out = new FileOutputStream(new File(keyPath));
			X509EncodedKeySpec x509Enc = null;
			x509Enc = new X509EncodedKeySpec(key.getEncoded());
			out.write(x509Enc.getEncoded());
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] generateSignature(InputStream in, PrivateKey prikey) throws RuntimeException {
		try {
			Signature sig = Signature.getInstance(signatureAlgorithm);
			sig.initSign(prikey);

			byte[] buffer = new byte[1024];
			int len;
			while (0 <= (len = in.read(buffer))) {
				sig.update(buffer, 0, len);
			}
			in.close();

			return sig.sign();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (SignatureException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean verifySignature(InputStream in, PublicKey pubKey, byte[] orgSig) throws RuntimeException {
		try {
			Signature sig = Signature.getInstance(signatureAlgorithm);

			sig.initVerify(pubKey);

			byte[] buffer = new byte[1024];
			int len;
			while (0 <= (len = in.read(buffer))) {
				sig.update(buffer, 0, len);
			}
			in.close();

			return sig.verify(orgSig);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (SignatureException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] encode(Key key, byte[] data) throws RuntimeException {
		try {
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] decode(Key key, byte[] data) throws RuntimeException {
		try {
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 转换密钥为String型
	 * @param key
	 * @return
	 */
	public static String transKeyToStr(Key key) {
		byte[] encodedKey = key.getEncoded();
		String keyValue = HexUtil.toHexString(encodedKey);
		return keyValue;
	}

	/**
	 * 获取私钥对象
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	public static PrivateKey getPrivateKey(String privateKey) throws Exception {
		byte[] data = HexUtil.toByteArray(privateKey);
		PKCS8EncodedKeySpec pkcs8Enc = new PKCS8EncodedKeySpec(data);

		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		return keyFactory.generatePrivate(pkcs8Enc);
	}

	/**
	 * 获取公钥对象
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String publicKey) throws Exception {
		byte[] data = HexUtil.toByteArray(publicKey);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		return keyFactory.generatePublic(spec);
	}

	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData 已加密数据
	 * @param privateKey 私钥(十六进制编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		byte[] keyBytes = HexUtil.toByteArray(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * <p>
	 * 公钥解密
	 * </p>
	 * 
	 * @param encryptedData 已加密数据
	 * @param publicKey 公钥(十六进制编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
		byte[] keyBytes = HexUtil.toByteArray(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 * 
	 * @param data 源数据
	 * @param publicKey 公钥(十六进制编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = HexUtil.toByteArray(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * <p>
	 * 私钥加密
	 * </p>
	 * 
	 * @param data 源数据
	 * @param privateKey 私钥(十六进制编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = HexUtil.toByteArray(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData 已加密数据(十六进制编码)
	 * @param privateKey 私钥(十六进制编码)
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(String encryptedData, String privateKey) throws Exception {
		return new String(decryptByPrivateKey(HexUtil.toByteArray(encryptedData), privateKey));
	}

	/**
	 * <p>
	 * 公钥解密
	 * </p>
	 * 
	 * @param encryptedData 已加密数据(十六进制编码)
	 * @param publicKey 公钥(十六进制编码)
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPublicKey(String encryptedData, String publicKey) throws Exception {
		return new String(decryptByPublicKey(HexUtil.toByteArray(encryptedData), publicKey));
	}

	/**
	 * <p>
	 * 公钥加密，返回十六进制编码的密文
	 * </p>
	 * 
	 * @param data 源数据
	 * @param publicKey 公钥(十六进制编码)
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String data, String publicKey) throws Exception {
		return HexUtil.toHexString(encryptByPublicKey(data.getBytes(), publicKey));
	}

	/**
	 * <p>
	 * 私钥加密，返回十六进制编码的密文
	 * </p>
	 * 
	 * @param data 源数据
	 * @param privateKey 私钥(十六进制编码)
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPrivateKey(String data, String privateKey) throws Exception {
		return HexUtil.toHexString(encryptByPrivateKey(data.getBytes(), privateKey));
	}

	public static void main(String[] args) throws Exception {
		test1();

		//		RSAUtil rsaUtil = RSAUtil.getInstance(1024);
		//		KeyPair kp = rsaUtil.generateRandomKeyPair();
		//		PrivateKey priKey = kp.getPrivate();
		//		PublicKey pubKey = kp.getPublic();
		//
		//		String priKeyStr = RSAUtil.transKeyToStr(priKey);
		//		String pubKeyStr = RSAUtil.transKeyToStr(pubKey);
		//		System.out.println(priKeyStr);
		//		System.out.println(pubKeyStr);
		//		priKey = getPrivateKey(priKeyStr);
		//		pubKey = getPublicKey(pubKeyStr);
		//
		//		byte[] data = "Hello World!".getBytes();
		//		byte[] encData = rsaUtil.encode(priKey, data);
		//		byte[] decData = rsaUtil.decode(pubKey, encData);
		//		System.out.println(new String(data));
		//		System.out.println(new String(decData));
		//		System.out.println(Arrays.equals(decData, data)); // true

		/*
		KeyPair kp = rsaUtil.generateRandomKeyPair();
		PrivateKey priKey1 = kp.getPrivate();
		PublicKey pubKey1 = kp.getPublic();

		RSAUtil.savePrivateKey(priKey1, "d:/keys/mallpri.dat");
		RSAUtil.savePublicKey(pubKey1, "d:/keys/mallpub.dat");
		*/
		//        byte[] data = "Hello World!".getBytes();
		//
		//        PrivateKey priPayKey = RSAUtil.loadPrivateKey("d:/keys/hnapaypri.dat");
		//        PublicKey pubPayKey = RSAUtil.loadPublicKey("d:/keys/hnapaypub.dat");
		//        PrivateKey priMallKey = RSAUtil.loadPrivateKey("d:/keys/mallpri.dat");
		//        PublicKey pubMallKey = RSAUtil.loadPublicKey("d:/keys/mallpub.dat");

		/* byte[] encData = rsaUtil.encode(priKey, data);
		 byte[] decData = rsaUtil.decode(pubKey, encData);
		 System.out.println(Arrays.equals(decData, data)); // true


		 encData = rsaUtil.encode(pubKey, data);
		 decData = rsaUtil.decode(priKey, encData);
		 System.out.println(Arrays.equals(decData, data)); // true
		*/

		//        InputStream in = new ByteArrayInputStream(data);
		//        byte[] mallsig = rsaUtil.generateSignature(in, priMallKey);
		//        String sigs = new String(Base64.encodeBase64(mallsig));
		//        System.out.println(sigs);
		//        in.reset();
		//        boolean result = rsaUtil.verifySignature(in, pubMallKey, Base64.decodeBase64(sigs.getBytes()));
		//        System.out.println(result); // true

	}

	/**
	 * 测试公钥加密——私钥解密
	 * @throws Exception
	 */
	static void test1() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String source = "iofjio范德萨 iof912108";
		System.out.println("\r加密前文字：\r\n" + source);

		RSAUtil rsaUtil = RSAUtil.getInstance(1024);
		KeyPair kp = rsaUtil.generateRandomKeyPair();

		String encodedData = RSAUtil.encryptByPublicKey(source, transKeyToStr(kp.getPublic()));
		System.out.println("加密后文字长度：\r\n" + encodedData.length());
		System.out.println("加密后文字：\r\n" + encodedData);
		String decodedData = RSAUtil.decryptByPrivateKey(encodedData, transKeyToStr(kp.getPrivate()));
		String target = decodedData;
		System.out.println("解密后文字: \r\n" + target);
	}
}