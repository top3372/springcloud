/**
 *  File: AccountStatusUtil.java
 *  Description:
 *  Copyright 2015 GAOEX Corporation. All rights reserved.
 *  Date      Author      Changes
 *  MAY 25, 2015   Jack     Create
 *
 */
package com.haili.ins.utils;


import com.haili.ins.dao.model.AccAccountInfo;
import com.haili.ins.enums.AcctStatusMapEnum;

/**
 * 静态工具类
 * 用于方便的判断账户的各种状态
 */
public class AccountStatusUtil {

	/**
	 * 判断指定账户的状态下是否可被进行交易处理,即检查账户状态是否为:
	 * 已激活
	 * 未锁定
	 * 未销户
	 * 
	 * Note: 注意不包括冻结
	 * 
	 * @param account
	 */
	public static boolean isTxnable(AccAccountInfo account) {
		
		String expectedStatusMap = "10?0";
		
		return BitmapStatusUtil.isBitmapEqual(expectedStatusMap, account.getStatusMap());
		
	}
	
	/**
	 * 判断指定账户是否被激活
	 * 
	 * @param account
	 * @return
	 */
	public static boolean isActived(AccAccountInfo account) {
		
		String expectedStatusMap = "1???";
		
		return BitmapStatusUtil.isBitmapEqual(expectedStatusMap, account.getStatusMap());
		
	}
	
	/**
	 * 判断指定账户是否被锁定
	 * 
	 * @param account
	 * @return
	 */
	public static boolean isLocked(AccAccountInfo account){
		
		String expectedStatusMap = "?1??";
		
		return BitmapStatusUtil.isBitmapEqual(expectedStatusMap, account.getStatusMap());
		
	}
	
	/**
	 * 判断指定账户是否被销户
	 * 
	 * @param account
	 * @return
	 */
	public static boolean isClosed(AccAccountInfo account) {
		
		String expectedStatusMap = "???1";
		
		return BitmapStatusUtil.isBitmapEqual(expectedStatusMap, account.getStatusMap());
		
	}
	
	/**
	 * 判断指定账户是否为未销户
	 * 
	 * @param account
	 * @return
	 */
	public static boolean isUnclosed(AccAccountInfo account) {
		
		String expectedStatusMap = "???0";
		
		return BitmapStatusUtil.isBitmapEqual(expectedStatusMap, account.getStatusMap());
		
	}
	
	/**
	 * 判断指定账户是否为长期结转不动期
	 * 
	 * @param account
	 * @return
	 */
	public static boolean isEternal(AccAccountInfo account) {
		
		String expectedStatusMap = "???2";
		
		return BitmapStatusUtil.isBitmapEqual(expectedStatusMap, account.getStatusMap());
		
	}
	
	/**
	 * 判断指定账户是否为已撤销
	 * 
	 * @param account
	 * @return
	 */
	public static boolean isCancel(AccAccountInfo account) {
		
		String expectedStatusMap = "???3";
		
		return BitmapStatusUtil.isBitmapEqual(expectedStatusMap, account.getStatusMap());
		
	}
	
	
	/**
	 * 设置帐户状态位为激活
	 * 
	 * @param account
	 */
	public static void active(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.ACTIVE_STATUS, '1');
	}
	
	/**
	 * 设置帐户状态位为未激活
	 * 
	 * @param account
	 */
	public static void unactive(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.ACTIVE_STATUS, '0');
	}
	
	/**
	 * 设置帐户状态位为锁定
	 * 
	 * @param account
	 */
	public static void lock(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.LOCK_STATUS, '1');
	}
	
	/**
	 * 设置帐户状态位为未锁定
	 * 
	 * @param account
	 */
	public static void unlock(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.LOCK_STATUS, '0');
	}
	
	/**
	 * 设置帐户状态位为未锁定
	 * 
	 * @param account
	 */
	public static void unfreeze(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.FROZEN_STATUS, '0');
	}
	
	/**
	 * 设置帐户状态位为限额冻结
	 * 
	 * @param account
	 */
	public static void freezeAmount(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.FROZEN_STATUS, '1');
	}
	
	/**
	 * 设置帐户状态位为借方冻结
	 * 
	 * @param account
	 */
	public static void freezeDebit(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.FROZEN_STATUS, '2');
	}
	
	/**
	 * 设置帐户状态位为贷方冻结
	 * 
	 * @param account
	 */
	public static void freezeCredit(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.FROZEN_STATUS, '3');
	}
	
	/**
	 * 设置帐户状态位为双向冻结
	 * 
	 * @param account
	 */
	public static void freezeDouble(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.FROZEN_STATUS, '4');
	}
	
	/**
	 * 设置帐户状态位为未销户
	 * 
	 * @param account
	 */
	public static void unclose(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.CLOSE_STATUS, '0');
	}
	
	/**
	 * 设置帐户状态位为已销户
	 * 
	 * @param account
	 */
	public static void close(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.CLOSE_STATUS, '1');
	}
	
	/**
	 * 设置帐户状态位为已结转长期不动户
	 * 
	 * @param account
	 */
	public static void eternal(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.CLOSE_STATUS, '2');
	}
	
	/**
	 * 设置帐户状态位为已撤销
	 * 
	 * @param account
	 */
	public static void cancel(AccAccountInfo account) {
		BitmapStatusUtil.setStatus(account.getStatusMap(), AcctStatusMapEnum.CLOSE_STATUS, '3');
	}
	
}
