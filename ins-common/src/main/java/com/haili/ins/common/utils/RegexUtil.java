package com.haili.ins.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具
 * 
 * @author liyangxi
 * @date 2015年11月6日
 */
public class RegexUtil {

	/** 只能是汉字、字母和数字 */
	public static final String regex1 = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";

	/** 只能是字母和数字 */
	public static final String regex2 = "^[a-zA-Z0-9]+$";
	
	/** 只能是字母 */
	public static final String regex3 = "^[a-zA-Z]+$";
	
	/** 只能是数字 */
	public static final String regex4 = "^[0-9]+$";
	
	/** 浮点数和整数 */
	public static final String regex5 = "^\\d+(\\.\\d+)?$";

	/** 匹配URL：http和www开头的都可匹配且忽略大小写 */
	public static final String regex6 = "((?i)http://)?((?i)www)\\.[a-zA-Z\\d]+\\.[a-zA-Z]+.*";
	
	/** 匹配数字开头的字符串 */
	public static final String regex7 = "^\\d+.*$";
	
	
	/**
	 * "^\\d+$"　　//非负整数（正整数   +   0）     
  "^[0-9]*[1-9][0-9]*$"　　//正整数     
  "^((-\\d+)|(0+))$"　　//非正整数（负整数   +   0）     
  "^-[0-9]*[1-9][0-9]*$"　　//负整数     
  "^-?\\d+$"　　　　//整数     
  "^\\d+(\\.\\d+)?$"　　//非负浮点数（正浮点数   +   0）     
  "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"　　//正浮点数    
  "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$"　　//非正浮点数（负浮点数   +   0）     
  "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$"　　//负浮点数    
  "^(-?\\d+)(\\.\\d+)?$"　　//浮点数
	 */
	
	
	/**
	 * 正则匹配
	 * @param regex	正则表达式
	 * @param content	进行匹配的字符串
	 * @return
	 */
	public static boolean match(String regex, String content) {
		if(StringUtils.isEmpty(content) || StringUtils.isEmpty(regex)){
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(content);
		boolean b = match.matches();
		if (b) {
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		
		String regex = "((?i)http://)?((?i)www)\\.[a-zA-Z\\d]+\\.[a-zA-Z]+.*"; 
//				"((http(?i))://)?(www(?i))+\\.[a-zA-Z\d]+\\.[a-zA-Z]+";
		
		
		String regex7 = "^\\d+.*$";
		
		String content = "19.aaa";
		
		boolean a = match(regex7, content);
		System.out.println(a);
		
		
	}
	

}
