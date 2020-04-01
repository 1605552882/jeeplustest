package com.jeeplus.common.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;

public class RSACryption {
	
	private final static String RSA = "RSA";
	public static PublicKey uk;
	public static PrivateKey rk;
	
	public static void generateKey() throws Exception { // 1,获取密钥
		// 实例化密钥工厂
		KeyPairGenerator gen = KeyPairGenerator.getInstance(RSA);
		// 初始化密钥生成器
		gen.initialize(512, new SecureRandom());
		// 生成密钥对
		KeyPair keyPair = gen.generateKeyPair();
		
		// Properties porp = new Properties();
		// InputStream is = RSACrypto.class.getResourceAsStream("RSAKey.properties");
		// porp.load(is);
		// FileInputStream fis = new FileInputStream("D:\\public_key.dat");
		// ObjectInputStream ois = new ObjectInputStream(fis);
		// uk = (PublicKey) ois.readObject();
		// FileInputStream fis1 = new FileInputStream("D:\\private_key.dat");
		// ObjectInputStream ois1 = new ObjectInputStream(fis1);
		// rk = (PrivateKey) ois1.readObject();
		
		// 公钥
		uk = keyPair.getPublic();
		// 私钥
		rk = keyPair.getPrivate();
	}
	
	private static byte[] encrypt(String text, PublicKey pubRSA) throws Exception { // 3,加密
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, pubRSA);
		return cipher.doFinal(text.getBytes());
	}
	
	public final static String encrypt(String text) { // 2，加密
		try {
			return byte2hex(encrypt(text, uk));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public final static String decrypt(String data) { // 5,解密
		try {
			return new String(decrypt(hex2byte(data.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static byte[] decrypt(byte[] src) throws Exception { // 6,解密
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, rk);
		return cipher.doFinal(src);
	}
	
	public static String byte2hex(byte[] b) { // 4，加密
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1) hs += ("0" + stmp);
			else hs += stmp;
		}
		// System.out.println("好，开始");
		// System.out.println(hs.toUpperCase());
		return hs.toUpperCase();
	}
	
	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	} // just for test
	
	public static void main(String args[]) {
		try {
			RSACryption.generateKey();
			// System.out.println(new SecureRandom().getAlgorithm());
			// System.out.println("公钥:"+uk);
			// System.out.println("私钥:"+rk);
			// OutputStream ops = new FileOutputStream("D:\\public_key.dat");
			// ObjectOutputStream oops = new ObjectOutputStream(ops);
			// oops.writeObject(uk);
			// OutputStream ops1 = new FileOutputStream("D:\\private_key.dat");
			// ObjectOutputStream oops1 = new ObjectOutputStream(ops1);
			// oops1.writeObject(rk);
			String cipherText = RSACryption.encrypt("123");
			System.out.println(cipherText);
			String plainText = RSACryption.decrypt(cipherText);
			System.out.println(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
