package com.haili.ins.utils;

import com.haili.ins.enums.member.MaskTypeEnum;

/**
 * @Author: leon
 * @Date: 2019/3/4 13:14
 * @Version 1.0
 */
public class MaskUtil {

    public static String getNameMask(String name) {
        if (name != null && name.length() > 1) {
            String str = "";
            for (int i = 0; i < name.length() - 1; i++) {
                str += "*";
            }
            return name.substring(0, 1) + str;
        }
        return name;
    }


    /**
     * 获得银行卡号和身份证号掩码
     * @Title: getMask
     * @Description: TODO
     * @param number
     * @param maskType
     * @return
     * @author: sjc
     * @date: 2017年4月19日 下午2:54:01
     */
    public static String getMask(String number, String maskType) {
        if(number == null || number.equals("")){
            return "";
        }
        int starCount;
        StringBuilder sb = new StringBuilder();
        // 为银行卡号时
        if (maskType.equals(MaskTypeEnum.MASK_TYPE_BANKNO.getCode())) {
            sb.append(number.substring(0, 4));
            starCount = number.length() - 4 - 4;
            for (int i = 0; i < starCount; i++) {
                sb.append("*");
            }
        } else if (maskType.equals(MaskTypeEnum.MASK_TYPE_IDNO.getCode())) {
            sb.append(number.substring(0, 3));
            starCount = number.length() - 3 - 4;
            for (int i = 0; i < starCount; i++) {
                sb.append("*");
            }
        }
        return sb.append(number.substring(number.length() - 4, number.length())).toString();
    }
}
