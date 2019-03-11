package com.haili.ins.common.utils.security;

import java.io.UnsupportedEncodingException;

public class AESHelperTest {

	public static final String passwd = "0123456789abcdef0123456789abcdef";

	public static final String plaintext = "Hello,AES!";

	public static void main(String[] args) throws UnsupportedEncodingException {

		System.out.println("=====> init <=====");
		AESHelper cipher = new AESHelper();
		cipher.initKey(passwd);
		//System.exit(0);

		//测试加密 和 解密
		System.out.println("=====> encrypt & decrypt <=====");

		String cryptedBase64Str = cipher.encrypt(plaintext);
		System.out.println("encrypted: " + cryptedBase64Str);

		String decryptedResult = cipher.decrypt(cryptedBase64Str);
		System.out.println("decrypted: " + decryptedResult);



//		//测试加密 和 解密
//		System.out.println("=====> encrypt & decrypt <=====");
//
//		String str = plaintext;
//		while (str.length() < 1000)
//		{
//			str += plaintext;
//		}
//
//		cryptedBase64Str = cipher.encrypt(str);
//		System.out.println("encrypted: " + cryptedBase64Str);
//
//		decryptedResult = cipher.decrypt("AqNBrEdjmL/1biWCNAGMDA==");
//		String[] reconList = decryptedResult.split("\n");
//		
//		for(String rst : reconList){
//			System.out.println(rst);
//		}
		
//		System.out.println("length: " + reconList.length);
//		System.out.println("decrypted: " + decryptedResult);
//		System.out.println(AESUtil.encrypt("1234567"));
//		System.out.println(AESUtil.decrypt("Kv4kdUt9IqdK0+eX27STjf8wxfnKVazb4GMmLYxb7JiQeputNzru0OC8GSiy50npgYwlKhez/aflqP++NaYxsNJy2NKIcQdK8PRKH4m+TDJk1FUYNadmA6CuRyCpExm/N86VLJXmKVDFaV9afj7KjHZXW0QwSzQvVUajdXfsjyc="));
	}

}
