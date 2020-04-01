package com.jeeplus.modules.sys.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


public class PassUnit {

 private static String privateKey;
 private static String publicKey;
	
  private static void start() throws Exception{
	  Map<String, Key> keyMap = initKey();
	    publicKey = getPublicKey(keyMap);
	    privateKey =getPrivateKey(keyMap);
	    System.out.println("公钥: \n\r" + publicKey);
	    System.out.println("私钥： \n\r" + privateKey);
  }
  /**
   * 获取公钥
   * @return 
   * @throws Exception
   */
  public static String getPublicKey() throws Exception{
	  if(publicKey==null&&privateKey==null){
		  start();
	  }
	  return publicKey;
  }
  /**
   * 解密
   * @param p 
   * @return
   * @throws Exception
   */
  public static String decrypt(String p) throws Exception{
	  if(publicKey==null&&privateKey==null){
		  start();
	  }
	  byte[] decodedData = decryptByPrivateKey(p,privateKey);
	    String outputStr = new String(decodedData);
	    return outputStr;
  }
	
  public static final String KEY_ALGORITHM = "RSA";
  public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

  private static final String PUBLIC_KEY = "RSAPublicKey";
  private static final String PRIVATE_KEY = "RSAPrivateKey";

  public static byte[] decryptBASE64(String key) {
    return Base64.decodeBase64(key);
  }

  public static String encryptBASE64(byte[] bytes) {
    return Base64.encodeBase64String(bytes);
  }

  /**
   * 用私钥对信息生成数字签名
   *
   * @param data    加密数据
   * @param privateKey 私钥
   * @return
   * @throws Exception
   */
  public static String sign(byte[] data, String privateKey) throws Exception {
    // 解密由base64编码的私钥
    byte[] keyBytes = decryptBASE64(privateKey);
    // 构造PKCS8EncodedKeySpec对象
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    // KEY_ALGORITHM 指定的加密算法
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    // 取私钥匙对象
    PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
    // 用私钥对信息生成数字签名
    Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
    signature.initSign(priKey);
    signature.update(data);
    return encryptBASE64(signature.sign());
  }

  /**
   * 校验数字签名
   *
   * @param data   加密数据
   * @param publicKey 公钥
   * @param sign   数字签名
   * @return 校验成功返回true 失败返回false
   * @throws Exception
   */
  public static boolean verify(byte[] data, String publicKey, String sign)
      throws Exception {
    // 解密由base64编码的公钥
    byte[] keyBytes = decryptBASE64(publicKey);
    // 构造X509EncodedKeySpec对象
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    // KEY_ALGORITHM 指定的加密算法
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    // 取公钥匙对象
    PublicKey pubKey = keyFactory.generatePublic(keySpec);
    Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
    signature.initVerify(pubKey);
    signature.update(data);
    // 验证签名是否正常
    return signature.verify(decryptBASE64(sign));
  }

  public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception{
    // 对密钥解密
    byte[] keyBytes = decryptBASE64(key);
    // 取得私钥
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    // 对数据解密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    return cipher.doFinal(data);
  }

  /**
   * 解密<br>
   * 用私钥解密
   *
   * @param data
   * @param key
   * @return
   * @throws Exception
   */
  public static byte[] decryptByPrivateKey(String data, String key)
      throws Exception {
    return decryptByPrivateKey(decryptBASE64(data),key);
  }

  /**
   * 解密<br>
   * 用公钥解密
   *
   * @param data
   * @param key
   * @return
   * @throws Exception
   */
  public static byte[] decryptByPublicKey(byte[] data, String key)
      throws Exception {
    // 对密钥解密
    byte[] keyBytes = decryptBASE64(key);
    // 取得公钥
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key publicKey = keyFactory.generatePublic(x509KeySpec);
    // 对数据解密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    return cipher.doFinal(data);
  }

  /**
   * 加密<br>
   * 用公钥加密
   *
   * @param data
   * @param key
   * @return
   * @throws Exception
   */
  public static byte[] encryptByPublicKey(String data, String key)
      throws Exception {
    // 对公钥解密
    byte[] keyBytes = decryptBASE64(key);
    // 取得公钥
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key publicKey = keyFactory.generatePublic(x509KeySpec);
    // 对数据加密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return cipher.doFinal(data.getBytes());
  }

  /**
   * 加密<br>
   * 用私钥加密
   *
   * @param data
   * @param key
   * @return
   * @throws Exception
   */
  public static byte[] encryptByPrivateKey(byte[] data, String key)
      throws Exception {
    // 对密钥解密
    byte[] keyBytes = decryptBASE64(key);
    // 取得私钥
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    // 对数据加密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    return cipher.doFinal(data);
  }

  /**
   * 取得私钥
   *
   * @param keyMap
   * @return
   * @throws Exception
   */
  public static String getPrivateKey(Map<String, Key> keyMap)
      throws Exception {
    Key key = (Key) keyMap.get(PRIVATE_KEY);
    return encryptBASE64(key.getEncoded());
  }

  /**
   * 取得公钥
   *
   * @param keyMap
   * @return
   * @throws Exception
   */
  public static String getPublicKey(Map<String, Key> keyMap)
      throws Exception {
    Key key = keyMap.get(PUBLIC_KEY);
    return encryptBASE64(key.getEncoded());
  }

  /**
   * 初始化密钥
   *
   * @return
   * @throws Exception
   */
  public static Map<String, Key> initKey() throws Exception {
    KeyPairGenerator keyPairGen = KeyPairGenerator
        .getInstance(KEY_ALGORITHM);
    keyPairGen.initialize(1024);
    KeyPair keyPair = keyPairGen.generateKeyPair();
    Map<String, Key> keyMap = new HashMap(2);
    keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
    keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
    return keyMap;
  }
//  public static void main(String[] args) throws Exception {
////	  Map<String, Key> keyMap = initKey();
////	   String publicKey = getPublicKey(keyMap);
////	   String privateKey =getPrivateKey(keyMap);
////	    System.err.println("公钥: \n\r" + publicKey);
////	    System.err.println("私钥： \n\r" + privateKey);
//	  
//	  
////	  System.err.println("公钥加密——私钥解密");
////	    String inputStr = "abc";
////	    byte[] encodedData = encryptByPublicKey(inputStr, publicKey);
////	    String m=new String(encodedData);
////	    byte[] decodedData = decryptByPrivateKey(encodedData,privateKey);
////	    String outputStr = new String(decodedData);
////	    System.err.println("加密: " + m + "\n\r" + "解密后: " + outputStr);
//	    
//	    
////		  System.err.println("公钥加密——私钥解密");
////		    String inputStr = "abc";
////		    byte[] encodedData = encryptByPublicKey(inputStr, publicKey);
////		    String m=new String(encodedData);
//		    byte[] decodedData = decryptByPrivateKey("Nc4HIoqLbPXkAJ7sLfIStxYzUGCiEU5K9gbLaWJFtRD+oGU3yLFEP/XpuS5uFbL4AwG/4KOOm79d/3fUAuqxQOg075WO9/hG4VB7WkpmR++LJ+9g1Pw0ivBLL7HH77J2fyvM9CShJHmZ2fbD8EvQXChRnEu8UaAz1aYciVp1his=","MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJY7BYFIMQTehBnNnu2ykANBQj2ni26VdjytK9aGUAo3ykKrESfCqx3+HSoK9MKH2QSxg+wxCNtbb/wJ/pmhLttAo7qRhVyJTDD3fIEuU7CkrblroMZpaFyTVPZjK/ohhK6qbaQaEmb+lsYTbSd1jvQ17tikCvAhj9L3LbYM8yzJAgMBAAECgYAShsDNCqwQ36gGzpa01XpxDFEw7UKCpqfoH6ryT6n7OJoW0qXQ827KyhWqo6M/giU/MO0F6Z4f/wy2Vj0xkPEIIzRSaZpjwW59FzHth4+ptqRdiqu/2A9fiUKpxrl9UHPNQQ+OuSJT+CNgmNafdDuoLS+14z8Ya9hYYx4fwy1coQJBAPZfrA9S5D5s8GIKUPBoyQPec2YWjRyCiq4KzIKm4CtkOI8hm0hEUkCanjOu0C3/pa0r8liJpHNT436/1XU9OgUCQQCcGa/trYRl6kI9rFJHAJf8mi4K0gwS9Oj/oyNvRa+pLwqSwCm9b6dSHphgwArJxgddXqUTqXhwkdThUlrx4u71AkEA4j8TS3MshKMX0IB2uX8QcYwbXvmAlljOr2rdSXGjVFWRluceqSuMeSVD+GQlashkRXWg9GtZ6Ep2FFRn2mTiXQJAEvRzjnLhftahUIfaPdDuo7e9G4dB/BNNL62JofteRzb1G7jF2MD0Gl6otibbqICpaEcT2H1dqJ2sseekL3cU+QJAHHddsbxtKfQbchtEwsZRjHRC65QehwSnJ1fViSayfiJ7E5Vt93dpHynvTcfAE5GgWmW1IFfFl4Vc+HXrb1h4OQ==");
//		    String outputStr = new String(decodedData);
//		    System.err.println( outputStr);
//	   
//}
}