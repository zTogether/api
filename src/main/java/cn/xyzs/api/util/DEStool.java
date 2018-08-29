package cn.xyzs.api.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 	加解密（通过密钥,加解密）
 * @author Administrator
 *
 */
public class DEStool {
	private String sKey;

    public DEStool() {
        //默认构造函数提供默认密钥
        sKey = "xyzs123456";
    }

    public DEStool(String securityKey) {
        if (securityKey.length() < 8) {
            throw new IllegalArgumentException("密钥长度至少8位");
        }
        this.sKey = securityKey;
    }

    private Cipher makeCipher() throws Exception{
        return Cipher.getInstance("DES");
    }

    private SecretKey makeKeyFactory() throws Exception{
        SecretKeyFactory des = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = des.generateSecret(new DESKeySpec(sKey.getBytes()));
        return secretKey;
    }
    
    /**
     * 	加密算法 Base64
     * @param text
     * @return
     * @throws Exception
     */
    public String encrypt(String text) throws Exception{
        Cipher cipher = makeCipher();
        SecretKey secretKey = makeKeyFactory();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return new String(Base64.encodeBase64(cipher.doFinal(text.getBytes())));
    }

    /**
     * 	解密算法
     * @param text
     * @return
     * @throws Exception
     */
    public String decrypt(String text) throws Exception{
        Cipher cipher = makeCipher();
        SecretKey secretKey = makeKeyFactory();
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.decodeBase64(text.getBytes())));
    }

    public static void main(String[] args) {
        DEStool tool = new DEStool("xyzs123456");
        String content = "xyzs123456";
        System.out.println("原文内容："+content);
        String encrpt = null;
        try {
            encrpt = tool.encrypt(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("加密后："+encrpt + ", 长度=" + encrpt.length());

        String descript =null;

        try {
            descript = tool.decrypt(encrpt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("解密后：" + descript);
    }

}
