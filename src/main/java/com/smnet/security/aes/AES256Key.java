package com.smnet.security.aes;

import com.smnet.security.base64.Base64Utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class AES256Key {

    private final String key;

    private byte[] ivBytes;
    private String ivBase64;
    private IvParameterSpec ivParameterSpec;

    private byte[] saltBytes;
    private String saltBase64;

    private SecretKey secretKey;

    public AES256Key(String key) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {

        this.key = key;

        this.ivBytes = AES256Utils.randomBytes16();
        this.ivBase64 = Base64Utils.base64UrlEncoder(this.ivBytes, false);
        this.ivParameterSpec = new IvParameterSpec(this.ivBytes);

        this.saltBytes = AES256Utils.randomBytes16();
        this.saltBase64 = Base64Utils.base64UrlEncoder(saltBytes, false);

        generate();
    }

    public AES256Key(String key, String ivBase64, String saltBase64) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {

        this.key = key;

        this.ivBase64 = ivBase64;
        this.ivBytes = Base64Utils.base64UrlDecoder(this.ivBase64);
        this.ivParameterSpec = new IvParameterSpec(this.ivBytes);

        this.saltBase64 = saltBase64;
        this.saltBytes = Base64Utils.base64UrlDecoder(this.saltBase64);

        generate();
    }

    private void generate() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        this.secretKey = AES256Utils.generateSecretKey(this);
    }

    public String getKey() {
        return key;
    }

    public byte[] getIvBytes() {
        return ivBytes;
    }

    public String getIvBase64() {
        return ivBase64;
    }

    public IvParameterSpec getIvParameterSpec() {
        return ivParameterSpec;
    }

    public byte[] getSaltBytes() {
        return saltBytes;
    }

    public String getSaltBase64() {
        return saltBase64;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}
