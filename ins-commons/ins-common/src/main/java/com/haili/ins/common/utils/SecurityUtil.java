package com.haili.ins.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class SecurityUtil {

	// 数据库中存储密码为MD5，使用MD5串进行密码验证
	public static String toPassowd(String userName,String pwd){
		String s1 = DigestUtils.md5Hex(userName.getBytes());
		String s2 = DigestUtils.md5Hex(pwd.getBytes());
		return DigestUtils.md5Hex(s2 + s1);
	}
	
	public static String hidePhone(String phone){
		if(StringUtils.isNotEmpty(phone)){
			return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
		}
		return phone;
	}
	
	
	public static String hideEmail(String email){
		if(StringUtils.isNotEmpty(email)){
			return email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)","$1****$3$4");
		}
		return email;
	}
	
	public static boolean checkPwd(String pwd){
		if(pwd.length()>=6){
			return true;
		}
		return false;
	}
	
	public static String getPrivateKey(){
		return "";
	}
	
	
	
	public static void main(String a[]) throws Exception{
		
	}

}
