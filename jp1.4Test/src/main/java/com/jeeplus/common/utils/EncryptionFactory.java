package com.jeeplus.common.utils;

import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class EncryptionFactory {
	
	private static final String KEY_ALGORITHM = "AES";
	
	private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	
	public static Key toKey(byte[] key) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
			
			return secretKey;
		} catch (Exception e) {}
		return null;
	}
	
	public static byte[] initKey() {
		try {
			KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM); // 实例化
			SecretKey secretKey = null;
			
			kg.init(128); // AES要求密钥长度为128位、192位或256位
			secretKey = kg.generateKey(); // 生成秘密密钥
			return secretKey.getEncoded(); // 返回密钥的二进制编码形式
		} catch (Exception e) {}
		return null;
	}
	
	public static byte[] encrypt(byte[] data, byte[] key) {
		try {
			Key k = toKey(key); // 还原密钥
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM); // 实例化
			
			cipher.init(Cipher.ENCRYPT_MODE, k); // 初始化，设置为加密模式
			return cipher.doFinal(data); // 执行操作
		} catch (Exception e) {}
		return null;
	}
	
	public static byte[] decrypt(byte[] data, byte[] key) {
		try {
			Key k = toKey(key); // 还原密钥
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM); // 实例化
			
			cipher.init(Cipher.DECRYPT_MODE, k); // 设置为解密模式
			return cipher.doFinal(data); // 执行操作
		} catch (Exception e) {}
		return null;
	}
	
	public static void AESByteTest() {
		String inputStr = "AES";
		byte[] inputData = inputStr.getBytes();
		System.out.println("原文：" + inputStr);
		
		byte[] key = initKey();
		System.out.println("密钥：" + Base64.encodeBase64String(key));
		
		inputData = encrypt(inputData, key);
		System.out.println("加密后：" + Base64.encodeBase64String(inputData));
		
		byte[] outputData = decrypt(inputData, key);
		String outputStr = new String(outputData);
		System.out.println("解密后：" + outputStr);
	}
	
	private static final String HEX = "0123456789ABCDEF";
	
	public static void appendHex(StringBuffer sbuf, byte b) {
		sbuf.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}
	
	public static String toHex(byte[] buf) {
		if (buf == null) { return ""; }
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}
	
	public static String toHex(String txt) {
		return toHex(txt.getBytes());
	}
	
	public static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		
		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
		}
		return result;
	}
	
	public static String encrypt(String strData, String key) {
		try {
			byte[] byteKey = toByte(key);
			byte[] result = encrypt(strData.getBytes(), byteKey);
			
			return toHex(result);
		} catch (Exception e) {}
		return null;
	}
	
	public static String decrypt(String strData, String key) {
		try {
			byte[] byteKey = toByte(key);
			byte[] enc = toByte(strData);
			byte[] result = decrypt(enc, byteKey);
			
			return new String(result);
		} catch (Exception e) {}
		return null;
	}
	
	public static void AESStringTest() {
		String inputStr = "AES";
		System.out.println("原文：" + inputStr);
		
		byte[] key = initKey();
		System.out.println("密钥原文：" + toHex(key) + " , 密钥加密后：" + Base64.encodeBase64String(key));
		
		String encInputData = encrypt(inputStr, toHex(key));
		System.out.println("加密后：" + encInputData);
		String decOutputData = decrypt(encInputData, toHex(key));
		System.out.println("解密后：" + decOutputData);
	}
	
	// =================================================================
	
	/**
	 * <p>
	 * <strong>encodeRSA256</strong> - RSA算法
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author lilin
	 * 
	 * @param data : 
	 * @return 
	 * @throws
	 */
	private static final String KEY_ALGORITHMS = "RSA";
	
	/** 
     * 公钥加密 
     * @param data待加密数据 
     * @param key 密钥 
     * @return byte[] 加密数据 
     * */  
    public static String encryptByPublicKey(byte[] data,PublicKey key) throws Exception{  
        
        //实例化密钥工厂  
        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHMS);   
        //数据加密  
        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, key);  
        return RSACryption.byte2hex((cipher.doFinal(data)));  
    } 
    
	// =================================================================
	
	/**
	 * <p>
	 * <strong>encodeSHA256</strong> - SHA-256信息摘要
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param data : 明文字节数组
	 * @return SHA-256信息摘要字节数组
	 * @throws
	 */
	public static byte[] encodeSHA256(byte[] data) {
		try {
			MessageDigest mdt = MessageDigest.getInstance("SHA-256");
			
			return mdt.digest(data);
		} catch (Exception e) {}
		return null;
	}
	
	public static byte[] encodeSHA256(String data) {
		try {
			return DigestUtils.sha256(data);
		} catch (Exception e) {}
		return null;
	}
	
	public static void SHA256Test() {
		String str = "SHA256消息摘要";
		byte[] data1 = encodeSHA256(str);
		byte[] data2 = encodeSHA256(str);
		
		System.out.println(data1 == data2);
	}
	
	public static String mergerDataAndSalt(String data, String salt) {
		if (AppUtil.isEmpty(salt) == false) {
			data = data + "{" + salt + "}";
		}
		return data;
	}
	
	/**
	 * <p>
	 * <strong>encodeSHA256Hex</strong> - SHA-256的十六进制信息摘要
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param data : 明文
	 * @return SHA-256的十六进制信息摘要字符串
	 * @throws
	 */
	public static String encodeSHA256Hex(String data) {
		try {
			return DigestUtils.sha256Hex(data).toUpperCase();
		} catch (Exception e) {}
		return null;
	}
	
	public static String encodeMD5Hex(String data) {
		try {
			return DigestUtils.md5Hex(data).toUpperCase();
		} catch (Exception e) {}
		return null;
	}
	
	// =================================================================
	
	public static void main(String[] args) throws Exception {
		System.out.println("SHA-256加密测试：");
		System.out.println("admin : " + toHex(encodeSHA256("admin".getBytes())) + "(" + encodeSHA256("admin".getBytes()).length + "bit)");
		System.out.println("admin : " + encodeSHA256Hex("admin") + "(" + encodeSHA256Hex("admin").length() + "bit)");
		System.out.println("admin : " + encodeMD5Hex("admin") + "(" + encodeMD5Hex("admin").length() + "bit)");
		
		System.out.println("\nAES字节数组加密测试：");
		AESByteTest();
		System.out.println("\nAES字符串加密测试：");
		AESStringTest();
	}
	
}
