package com.cgn.bbs.util.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
 
public class AesUtil {
    /*
     * @param content:
     * 
     * @param password:
     */
    public static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // 使用128 位
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] encodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(encodeFormat, "AES");
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("AES");
            // 加密内容进行编码
            byte[] byteContent = content.getBytes("utf-8");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 正式执行加密操作
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    /*
     * @param content:
     * 
     * @param password:
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // 使用128 位
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] encodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(encodeFormat, "AES");
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("AES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 正式执行解密操作
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
 
        return null;
    }
 
    /**
     * 二进制--》十六进制转化
     * 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
 
    /**
     * 十六进制--》二进制转化
     * 
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
 
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    /**
     * 生成随机密钥
     * @return
     */
    public static String getKeyRandom(){
    	return UUID.randomUUID().toString();
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
		//加密的内容
		String content = "000000";
		//密钥
		String password = getKeyRandom();
		System.out.println("密钥:"+password);
		System.out.println("加密前：" + content);
		
		// 加密方法
		byte[] encryptResult = encrypt(content, password);
		System.out.println("加密后byte[] ：" + encryptResult);
		
		String encryptResultStr = parseByte2HexStr(encryptResult);
		System.out.println("加密后：" + encryptResultStr);
		
		
		
		// 解密方法
		byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
		System.out.println("解密后decryptFrom byte[]：" + decryptFrom);
		//解密内容
		byte[] decryptResult = decrypt(decryptFrom, password);
		// 解密内容进行解码
		String result = new String(decryptResult, "utf-8");
		
		System.out.println("解密后：" + result);
    }
 
}