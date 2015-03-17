package com.tool.util.frequent;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;



public class UtilForEncrypt {
	
	private static final String SECRET_AES = "AES";
	private static final String SECRET_PATTERN_CBC = "AES/CBC/PKCS5Padding";
	private static final String KEY_WORD = "1234567890123456";
	private static final String CODE = "UTF-8";
	
	private static Cipher cipher;
	private static SecretKeySpec secretKey;
	private static IvParameterSpec ivParameterSpec;
	
	static{
		try {
			byte[] bs = KEY_WORD.getBytes(CODE);
        	secretKey = new SecretKeySpec(bs, SECRET_AES);
			cipher = Cipher.getInstance(SECRET_PATTERN_CBC);
			ivParameterSpec = new IvParameterSpec(new byte[16]);
		} catch (Exception e) {
		}
	}
	
	/***
	 * AES加密(CBC/模式)
	 * @param cont
	 * @return
	 */
	public static byte[] encodeAES(String cont) {  
        try {
        	cipher.init(Cipher.ENCRYPT_MODE, secretKey,ivParameterSpec);
            return cipher.doFinal(cont.getBytes(CODE));
		} catch (Exception e) {
			return null;
		}
    }  
	
	
	/***
	 * AES解密(CBC/模式)
	 * @param cont
	 * @return
	 */
	public static String decodeAES(byte[] cont){
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey,ivParameterSpec);
			byte[] decrypt = cipher.doFinal(cont);  
			return new String(decrypt);
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/***
	 * Base64加密
	 * @param content
	 * @return
	 */
	public static String encodeBase64(byte[] bs) {
		try {
			return new String(Base64.encodeBase64(bs));
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/***
	 * Base64解密
	 * @param content
	 * @return
	 */
	public static byte[] decodeBase64(byte[] cont) {
		try {
			return Base64.decodeBase64(cont);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	
}
