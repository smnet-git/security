package com.smnet.security.des;

import com.smnet.security.base64.Base64Utils;
import com.smnet.security.EnumAlgorithm;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class DESUtils {

    public static SecretKey generateSecretKey(String key)
            throws NoSuchAlgorithmException, InvalidKeyException,
            InvalidKeySpecException, NullPointerException, IllegalStateException {

        if (key == null)
            throw new NullPointerException("Key is null");

        String keyTrim = key.trim();
        if (keyTrim.isEmpty())
            throw new IllegalStateException("Key is empty");

        byte[] keyBytes = keyTrim.getBytes(StandardCharsets.UTF_8);
        KeySpec keySpec = new DESKeySpec(keyBytes);

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(EnumAlgorithm.DES.getPasswordBaseAlgorithm());
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

        return secretKey;
    }

    public static String encode(String text, SecretKey secretKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(EnumAlgorithm.DES.getCipherAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        byte[] cipherFinalBytes = cipher.doFinal(textBytes);

        String encode = Base64Utils.base64Encoder(cipherFinalBytes);

        return encode;
    }

    public static String decode(String text, SecretKey secretKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(EnumAlgorithm.DES.getCipherAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] base64DecodeBytes = Base64Utils.base64Decoder(text);

        byte[] cipherFinalBytes = cipher.doFinal(base64DecodeBytes);

        String decode = new String(cipherFinalBytes, StandardCharsets.UTF_8);

        return decode;
    }
}
