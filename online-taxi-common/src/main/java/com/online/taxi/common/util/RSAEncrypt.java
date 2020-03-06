package com.online.taxi.common.util;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {
	
	// 用于封装随机产生的公钥与私钥
	private static Map<Integer, String> keyMap = new HashMap<Integer, String>();  
	
	static {
		String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDefDQOvm1MdubDmaYUzee1+uukQx2qjSv/KIYDUF4Lg0R8OzcaLUcOdWvjkhBUa0p1Uf9WBir81erZZnPM9TxnQjcl9xmEuzmu63Ykdf2Y+kUU8fibVmnlXxrm+7lEF+bCL3CIaTACa0i602zkJTdQvhPKrBNtNa/zoh/6OKfaCwIDAQAB";
		String privateKeyString = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAN58NA6+bUx25sOZphTN57X666RDHaqNK/8ohgNQXguDRHw7NxotRw51a+OSEFRrSnVR/1YGKvzV6tlmc8z1PGdCNyX3GYS7Oa7rdiR1/Zj6RRTx+JtWaeVfGub7uUQX5sIvcIhpMAJrSLrTbOQlN1C+E8qsE201r/OiH/o4p9oLAgMBAAECgYEAvDRXGS3PacKfXVGJo8m2vEOhuJep7c90K2mNTBpdI+IMOgchirxIgpukA6NYuwDzwKD3nT6keLNA8lzvkq7VQ3RJv7yQTjuc6foVZjI05M/QCz0qa3Ss2rwASkKu+FQFrN1eSrPIktkVVVeKUepdqE43ZVnFxB2hsFRFV85mIHkCQQD2xCgUebWV3OHViA9wCKpTcRpODt1KyTEHIFBQDrUVDV4EhuYYXQwOakbItBoLMtl2nue9uD1s11adwkqK90C3AkEA5s9zm221S+fZz+DBEibThiwjxOFbv431mfvmFe/IR5x08FmzTJorzgsEQen79KTTbid5ELArzWGxt+TZqLi1TQJAKsgDMtmO87ZhqMV397Jo5SJ8rv/AudB7oYKmqdqC5m102VAR4DNxfaZLM0yWH07niLsv7iJc89u5doxSpBkNrQJAe60bqGRf6h2BNAedzQkq/NE4FW4gSINb4Df/MS/8JIssigG2tsxBvrVegadMT+nmNTdHgu6zeejoXr5s9yCKXQJAVZ/I9pdYfobPs4HFbJBHMW0uzxyx1YAi4F46J9zKE027gs1mq5I4lRfxi9IfJtduM6OtWNqKdC1cLqt9aSqvsw==";
		
		// 将公钥和私钥保存到Map
		// 0表示公钥  1表示私钥
		keyMap.put(0,publicKeyString);  
		keyMap.put(1,privateKeyString); 
	}
	
	
	
	/** 
	 * 随机生成密钥对 
	 * @throws NoSuchAlgorithmException 
	 */   
	public static void genKeyPair() throws NoSuchAlgorithmException {  
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
		// 初始化密钥对生成器，密钥大小为96-1024位   
		keyPairGen.initialize(1024,new SecureRandom());  
		// 成一个密钥对，保存在keyPair中  
		KeyPair keyPair = keyPairGen.generateKeyPair();  
		// 得到私钥  
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();     
		// 得到公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();    
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));  
		// 得到私钥字符串 
		String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));  
		// 将公钥和私钥保存到Map
		// 0表示公钥  1表示私钥
		keyMap.put(0,publicKeyString);  
		keyMap.put(1,privateKeyString); 
	}  
	/** 
	 * RSA公钥加密 
	 *  
	 * @param str 
	 *            加密字符串
	 * @param publicKey 
	 *            公钥 
	 * @return 密文 
	 * @throws Exception 
	 *             加密过程中的异常信息 
	 */
	public static String encrypt( String str, String publicKey ) throws Exception{
		// base64编码的公钥
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
		// RSA加密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
		return outStr;
	}

	/** 
	 * RSA私钥解密
	 *  
	 * @param str 
	 *            加密字符串
	 * @param privateKey 
	 *            私钥 
	 * @return 铭文
	 * @throws Exception 
	 *             解密过程中的异常信息 
	 */  
	public static String decrypt(String str, String privateKey) throws Exception{
		// 64位解码加密后的字符串
		byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
		// base64编码的私钥
		byte[] decoded = Base64.decodeBase64(privateKey);  
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));  
		// RSA解密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		String outStr = new String(cipher.doFinal(inputByte));
		return outStr;
	}
	
	public static void main(String[] args) throws Exception {
		// 生成公钥和私钥
//		genKeyPair();
		// 加密字符串
		String message = "北京马士兵";
		System.out.println("随机生成的公钥为:" + keyMap.get(0));
		System.out.println("随机生成的私钥为::" + keyMap.get(1));
		String messageEn = encrypt(message,keyMap.get(0));
		System.out.println(message + "\t加密后的字符串为:" + messageEn);
		String messageDe = decrypt(messageEn,keyMap.get(1));
		System.out.println("还原后的字符串为:" + messageDe);
	}

}