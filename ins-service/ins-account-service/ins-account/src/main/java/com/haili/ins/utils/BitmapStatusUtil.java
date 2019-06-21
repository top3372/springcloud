/**
 * File: BitmapStatusUtil.java
 * Description:
 * Copyright 2015 GAOEX Corporation. All rights reserved.
 * Date      Author      Changes
 * MAY 25, 2015   Jack     Create
 */
package com.haili.ins.utils;


import com.haili.ins.enums.AccountInfoStatusMapEnum;
import com.haili.ins.enums.BitmapStatusEnumable;

/**
 *
 */
public class BitmapStatusUtil {

    /**
     * Set the status with given parameter
     *
     * @param bitMap original bit map
     * @param status which status will be set
     * @param val value of status
     * @return a new status bit map
     */
    public static String setStatus(String bitMap, BitmapStatusEnumable status, char val) {
        if (bitMap == null || status == null) {
            throw new NullPointerException("Parameter [bitMap] or [status] should not be null.");
        }

        if (bitMap.length() <= status.getSequence()) {
            throw new IllegalArgumentException("Status sequence is larger than bitMap length");
        }

        StringBuilder sb = new StringBuilder(bitMap);
        sb.replace(status.getSequence(), status.getSequence() + 1, String.valueOf(val));

        return sb.toString();
    }

    /**
     * Get the status from bitmap with the specified status enumeration
     *
     * @param bitMap
     * @param status
     * @return
     */
    public static char getStatus(String bitMap, BitmapStatusEnumable status) {
        if (bitMap == null || status == null) {
            throw new NullPointerException("Parameter [bitMap] or [status] should not be null.");
        }

        if (bitMap.length() <= status.getSequence()) {
            throw new IllegalArgumentException("Status sequence is larger than bitMap length");
        }

        return bitMap.charAt(status.getSequence());
    }

    /**
     * Get the status from bitmap with the specified status enumeration
     *
     * @param bitMap
     * @param status
     * @return
     */
    public static String getStringStatus(String bitMap, BitmapStatusEnumable status) {
        if (bitMap == null || status == null) {
            throw new NullPointerException("Parameter [bitMap] or [status] should not be null.");
        }

        if (bitMap.length() <= status.getSequence()) {
            throw new IllegalArgumentException("Status sequence is larger than bitMap length");
        }

        return bitMap.substring(status.getSequence(), status.getSequence() + 1);
    }

    /**
     * Whether the bitmap is satisfied with the given pattern
     *
     * @param expectedPattern the expected bitmap, the character ? represents any status value
     * @param bitMap
     * @return
     */
    public static boolean isBitmapEqual(String expectedPattern, String bitMap) {
        if (expectedPattern == null || bitMap == null) {
            throw new NullPointerException("Parameter [expectedPattern] or [bitMap] should not be null.");
        }

        if (expectedPattern.length() != bitMap.length()) {
            throw new IllegalArgumentException("Pattern length not equals with bitmap");
        }

        for (int i = 0; i < expectedPattern.length(); i++) {
            char expectedStatus = expectedPattern.charAt(i);
            if ('?' != expectedStatus) {
                if (expectedStatus != bitMap.charAt(i)) {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     * Whether the status of specified bitmap and statusEnum equals with expected status
     *
     * @param bitMap
     * @param statusEnum
     * @param expectedStatus
     * @return
     */
    public static boolean isStatusEqual(String bitMap, BitmapStatusEnumable statusEnum, char expectedStatus) {

        char status = getStatus(bitMap, statusEnum);
        return status == expectedStatus;

    }

    /**
     * Whether the status of specified bitmap and statusEnum equals with expected status
     *
     * @param bitMap
     * @param statusEnum
     * @return
     */
    public static boolean isStatusEqual(String bitMap, BitmapStatusEnumable statusEnum, AccountInfoStatusMapEnum statusMapenum) {

        String status = getStringStatus(bitMap, statusEnum);
        return status.equals(statusMapenum.getCode());

    }

}
