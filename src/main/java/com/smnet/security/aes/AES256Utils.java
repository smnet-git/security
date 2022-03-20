package com.smnet.security.aes;

import com.smnet.security.EnumAlgorithm;
import com.smnet.security.base64.Base64Utils;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class AES256Utils {

    public static SecretKey generateSecretKey(AES256Key key) throws NoSuchAlgorithmException, InvalidKeySpecException {

        String keyTrim = key.getKey().trim();

        char[] keyChars = keyTrim.toCharArray();

        int iterations = 65536;
        int keyLength = 256;
        KeySpec keySpec = new PBEKeySpec(keyChars, key.getSaltBytes(), iterations, keyLength);

        String passwordBaseAlgorithm = EnumAlgorithm.AES256.getPasswordBaseAlgorithm();
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(passwordBaseAlgorithm);

        SecretKey secretKeyPasswordBaseAlgorithm = secretKeyFactory.generateSecret(keySpec);
        byte[] secretKeyPasswordBaseAlgorithmEncoded = secretKeyPasswordBaseAlgorithm.getEncoded();

        SecretKey secretKey = new SecretKeySpec(secretKeyPasswordBaseAlgorithmEncoded, EnumAlgorithm.AES256.getName());

        return secretKey;
    }

    public static String encode(String text, AES256Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        String cipherAlgorithm = EnumAlgorithm.AES256.getCipherAlgorithm();
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);

        cipher.init(Cipher.ENCRYPT_MODE, key.getSecretKey(), key.getIvParameterSpec());

        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        byte[] cipherFinalBytes = cipher.doFinal(textBytes);

        String encode = Base64Utils.base64Encoder(cipherFinalBytes);

        return encode;
    }

    public static String decode(String text, AES256Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        String cipherAlgorithm = EnumAlgorithm.AES256.getCipherAlgorithm();
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);

        cipher.init(Cipher.DECRYPT_MODE, key.getSecretKey(), key.getIvParameterSpec());

        byte[] base64DecodeBytes = Base64Utils.base64Decoder(text);

        byte[] cipherFinalBytes = cipher.doFinal(base64DecodeBytes);

        String decode = new String(cipherFinalBytes, StandardCharsets.UTF_8);

        return decode;
    }

    public static byte[] randomBytes16() {
        return randomBytes(16);
    }

    public static byte[] randomBytes(int size) {
        byte[] bytes = new byte[size];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }
}
