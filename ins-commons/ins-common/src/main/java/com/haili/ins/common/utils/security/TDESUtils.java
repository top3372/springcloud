package com.haili.ins.common.utils.security;

import java.io.ByteArrayOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TDESUtils {

    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;
    
    public void init(String argKey) throws Exception {
        Key key = this.getKey(argKey);
        encryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        decryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }

    
    /**
     * 单des
     * @param arrB
     * @return
     * @throws Exception
     */
    public byte[] enCrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    /**
     * 单des
     * @param strIn
     * @return
     * @throws Exception
     */
    public String encrypt(String strIn, String code) throws Exception {
        byte[] byteResult = enCrypt(strIn.getBytes(code));
        String tmp = new String(byteResult, code);
        return BCD2ASC(tmp).toUpperCase();
    }

    /**
     * 单des
     * @param arrB
     * @return
     * @throws Exception
     */
    public byte[] deCrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    /**
     * 单des
     * @param strIn
     * @return
     * @throws Exception
     */
    public String decrypt(String strIn, String code) throws Exception {
        return new String(deCrypt(hexStr2ByteArr(strIn, code)));
    }
    
    public String makeMAC(String sData) throws Exception {
        byte[] rtn = new byte[8];
        if (sData != null) {
            byte[] resultData = new byte[8];
            for (int j = 0; j < 8; j++) {
                resultData[j] = 00;
            }
            int len = sData.getBytes().length;
            int thisMove = 0;
            for (int thisPos = 0; thisPos < len; thisPos += thisMove) {
                thisMove = Math.min(len - thisPos, 8);
                for (int i = 0; i < thisMove; i++) {
                    resultData[i] = (byte) (rtn[i] ^ sData.charAt(thisPos + i));
                }
                if (thisMove != 8) {
                    for (int j = thisMove; j < 8; j++) {
                        resultData[j] = rtn[j];
                    }
                }
                for (int i = 0; i < 8; i++) {
                    rtn[i] = resultData[i];
                }
                rtn = this.enCrypt(rtn);
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < rtn.length; i++)
            sb.append(byteHEX(rtn[i]));
        return sb.toString().toUpperCase();
    }
    
    /**
     * 3des
     * @param sData
     * @param sKey
     * @return
     * @throws Exception
     */
    public String enCrypt(String sData, String sKey, String enccode) throws Exception {
        this.init(sKey.substring(0, 16));
        byte[] b = this.enCrypt(sData.getBytes(enccode));
        this.init(sKey.substring(16));
        b = this.deCrypt(b);
        this.init(sKey.substring(0, 16));
        b = this.enCrypt(b);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
        	sb.append(byteHEX(b[i]));
        }
        
        return sb.toString().toUpperCase();
    }
    
    /**
     * 3des
     * @param sData
     * @param sKey
     * @return
     * @throws Exception
     */
    public String deCrypt(String sData, String sKey, String code) throws Exception {
        this.init(sKey.substring(0, 16));
        byte[] b = this.deCrypt(hexStr2ByteArr(sData, code));
        this.init(sKey.substring(16));
        b = this.enCrypt(b);
        this.init(sKey.substring(0, 16));
        b = this.deCrypt(b);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            sb.append(byteHEX(b[i]));
        return sb.toString().toUpperCase();
    }
    
    public static String byteHEX(byte ib) {
        char[] digitNormal = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        char[] ob = new char[2];
        ob[0] = digitNormal[(ib >>> 4) & 0x0F];
        ob[1] = digitNormal[ib & 0x0F];
        String s = new String(ob);
        return s;
    }
    
    private String[] subString(String str) {
        int length = str.length();
        String[] strs = null;
        if (str != null) {
            if (length % 2 == 0) {
                strs = new String[length / 2];
                for (int i = 0; i < length / 2; i++) {
                    strs[i] = str.substring(i * 2, i * 2 + 2);
                }
            } else {
                strs = new String[length / 2 + 1];
                for (int i = 0; i < length / 2 + 1; i++) {
                    if (i < length / 2) {
                        strs[i] = str.substring(i * 2, i * 2 + 2);
                    } else {
                        strs[i] = str.substring(i * 2, length);
                    }

                }
            }
        }
        return strs;
    }

    /**
     * 转ascii
     * @param p_SourStr
     * @return
     */
    private String BCD2ASC(String p_SourStr) {
        String resultData = null;
        try {
            if (p_SourStr != null) {
                StringBuffer resultBuf = new StringBuffer();
                int sourStrLen = p_SourStr.length();
                String tempStr;
                char tempChar;
                for (int i = 0; i < sourStrLen; i++) {
                    tempChar = p_SourStr.charAt(i);
                    tempStr = Integer.toHexString((int) tempChar);
                    //tempStr = StringUtil.steadyLength(tempStr, "0", 2, "L");
                    resultBuf.append(tempStr);
                }
                resultData = resultBuf.toString();
            } else {
                throw new RuntimeException("参数错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

    public byte[] hexStr2ByteArr(String strIn, String code) throws Exception {
        byte[] arrB = strIn.getBytes(code);
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2, code);//郑梦久改
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
    
    private Key getKey(String argKey) throws Exception {
        String[] keys = subString(argKey);
        byte[] b1 = new byte[keys.length];
        for (int i = 0; i < keys.length; i++) {
            int n = Integer.valueOf(keys[i], 16).intValue();
            b1[i] = (byte) n;
        }
        byte[] b2 = new byte[8];
        for (int i = 0; i < b1.length && i < b2.length; i++) {
            b2[i] = b1[i];
        }
        Key key = new SecretKeySpec(b2, "DES");
        return key;
    }
    
    public String en3DESCrpt(String sData, String pinKey, String enccode) throws Exception {
    	String newData = getNewString(sData,enccode);
    	return enCrypt(newData, pinKey, enccode);
    }
    
    public static String getNewString(String src,String enccode){
		StringBuffer sbf = new StringBuffer(src);
		int l = getLength(src,enccode);
		int tmp =((l+7)/8*8)-l;
		for(int i=0;i<tmp;i++){
			sbf.append(' ');
		}
		return sbf.toString();
	}
	
	public static int getLength(String src,String enccode){
		int strc = 0;
		for (int i = 0; i < src.length(); i++) {
			if (src.toCharArray()[i] > 255) {
				if("UTF-8".equalsIgnoreCase(enccode)){
					strc+=3;
				}else if("GBK".equalsIgnoreCase(enccode)){
					strc += 2;
				}
			} else {
				strc++;
			}
		}
		return strc;
	}
 
    
    /**
     * 解码
     * @param sData
     * @param pinKey
     * @return
     * @throws Exception
     */
    public String de3DESCrpt1(String sData, String pinKey, String code) throws Exception {
        byte[] bb = pinKey.getBytes();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bb.length; i++)
            sb.append(TDESUtils.byteHEX(bb[i]));
        String key16bits = sb.toString().toUpperCase();
        return this.deCrypt(sData, key16bits, code);
    }
    
    /**
     * 解码
     * @param sData
     * @param pinKey
     * @return
     * @throws Exception
     */
    public byte[] de3DESCrpt2(String sData, String pinKey, String decode) throws Exception {
        byte[] b = hexStr2ByteArr(this.deCrypt(sData, pinKey, decode), decode);
        return b;
    }
    
    
    private static String hexString = "0123456789ABCDEF";

    /**
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     * @param str
     * @return
     * @throws Exception
     */
    public String byteToHex(String str, String code) throws Exception{
       // 根据默认编码获取字节数组
       byte[] bytes = str.getBytes(code);//GBK     ISO-8859-1
       StringBuilder sb = new StringBuilder(bytes.length * 2);
       // 将字节数组中每个字节拆解成2位16进制整数
       for (int i = 0; i < bytes.length; i++) {
    	   
    	     sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));//1-4
    	     sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));//1-2
    	     
       }
       return sb.toString();
    }

    /**
     * 将16进制数字解码成字符串,适用于所有字符（包括中文） decode(String
     * bytes)方法里面的bytes字符串必须大写，即toUpperCase()
     * @param bytes
     * @return
     * @throws Exception
     */
    public String hexToByte(String bytes, String code) throws Exception{
       ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
       // 将每2位16进制整数组装成一个字节
       for (int i = 0; i < bytes.length(); i += 2){
    	   int ii = (hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1)));
           baos.write(ii);
       }
       return new String(baos.toByteArray(), code);
    }
     
    /**
     * Description : Test <br>
     * Created on 2008-12-15 下午02:00:08 <br>
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            TDESUtils desPlus = new TDESUtils();
            System.out.println("加密::" + desPlus.en3DESCrpt("郑梦久啥", "BC945DE342907ACE1433A3B4C578D2A9", "GBK"));
            byte[] temp = desPlus.de3DESCrpt2(desPlus.en3DESCrpt("郑梦久啥", "BC945DE342907ACE1433A3B4C578D2A9", "GBK"),
            									"BC945DE342907ACE1433A3B4C578D2A9", "GBK");
            System.out.println("解密::" + new String(temp,"GBK"));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}