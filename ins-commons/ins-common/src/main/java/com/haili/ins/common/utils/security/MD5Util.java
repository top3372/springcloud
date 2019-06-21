package com.haili.ins.common.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.haili.ins.common.utils.HexUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Static functions to simplifiy common {@link MessageDigest} tasks.  This
 * class is thread safe.
 *
 * @author pengjiong
 */
public class MD5Util {

    private MD5Util() {
    }

    /**
     * Returns a MessageDigest for the given <code>algorithm</code>.
     *
     * @param algorithm The MessageDigest algorithm name.
     * @return An MD5 digest instance.
     * @throws RuntimeException when a {@link NoSuchAlgorithmException} is
     *                          caught
     */

    static MessageDigest getDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(byte[] data) {
        return getDigest("MD5").digest(data);
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(String data) {
        return md5(data.getBytes());
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(byte[] data) {
        return HexUtil.toHexString(md5(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(String data) {
        return HexUtil.toHexString(md5(data));
    }

    /**
     * 验证输入的数据src加密后是否与dest相等.
     *
     * @param src  输入的数据
     * @param dest 加密后的数据
     * @return
     */
    public static boolean validateData(String src, String dest) {
        src = StringUtils.defaultString(src);
        dest = StringUtils.defaultString(dest);
        MD5 md5 = new MD5();
        if (("".equals(src) && src.equals(dest)) || md5Hex(src).equals(dest)) {
            return true;
        }
        if (md5.getMD5ofStr(src).equals(dest)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MD5 md5 = new MD5();
        System.out.println(md5.getMD5ofStr("admin"));
        System.out.println(md5Hex("admin"));
    }
}
