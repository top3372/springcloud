package com.haili.ins.common.utils.security;

public class LuhnUtil {
    /**
     * Luhn算法
     * 根据卡号获取校验位
     *
     * @param cardNumber
     * @return
     */
    public static int getCheckNumber(String cardNumber) {
        int totalNumber = 0;
        for (int i = cardNumber.length() - 1; i >= 0; i -= 2) {
            int tmpNumber = calculate(Integer.parseInt(String.valueOf(cardNumber.charAt(i))) * 2);
            if (i == 0) {
                totalNumber += tmpNumber;
            } else {
                totalNumber += tmpNumber + Integer.parseInt(String.valueOf(cardNumber.charAt(i - 1)));
            }

        }
        if (totalNumber >= 0 && totalNumber < 9) {
            return (10 - totalNumber);
        } else {
            String str = String.valueOf(totalNumber);
            if (Integer.parseInt(String.valueOf(str.charAt(str.length() - 1))) == 0) {
                return 0;
            } else {
                return (10 - Integer.parseInt(String.valueOf(str.charAt(str.length() - 1))));
            }
        }

    }

    /**
     * 计算数字各位和
     *
     * @param number
     * @return
     */
    private static int calculate(int number) {
        String str = String.valueOf(number);
        int total = 0;
        for (int i = 0; i < str.length(); i++) {
            total += Integer.valueOf(Integer.parseInt(String.valueOf(str.charAt(i))));
        }
        return total;
    }

    public static void main(String[] args) {
        String cardNumber = "439225831791777";
        System.out.println(getCheckNumber(cardNumber));
    }
}
