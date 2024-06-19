package com.example.demo.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class EncDecUtils {
	
	private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String KEY = "qwertyuiopkjjhgfdsazxcvdzdbnwery"; // 16-byte key
    
    private Key getKey() {
    	byte[] keyBytes = KEY.getBytes(StandardCharsets.UTF_8);
    	return new SecretKeySpec(keyBytes, ALGORITHM);
    }
    

    public String encrypt(String data) throws Exception {
        Key key = getKey();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        Key key = getKey();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        System.out.println(Cipher.DECRYPT_MODE);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
