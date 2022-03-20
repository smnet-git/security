package com.smnet.security;

public enum EnumAlgorithm {

    AES256("AES", "PBKDF2WithHmacSHA256", "AES/CBC/PKCS5Padding"),
    DES("DES");

    private String name;
    private String passwordBaseAlgorithm;
    private String cipherAlgorithm;

    EnumAlgorithm(String name) {
        this.name = name;
        this.passwordBaseAlgorithm = name;
        this.cipherAlgorithm = name;
    }

    EnumAlgorithm(String name, String passwordBaseAlgorithm, String cipherAlgorithm) {
        this.name = name;
        this.passwordBaseAlgorithm = passwordBaseAlgorithm;
        this.cipherAlgorithm = cipherAlgorithm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordBaseAlgorithm() {
        return passwordBaseAlgorithm;
    }

    public void setPasswordBaseAlgorithm(String passwordBaseAlgorithm) {
        this.passwordBaseAlgorithm = passwordBaseAlgorithm;
    }

    public String getCipherAlgorithm() {
        return cipherAlgorithm;
    }

    public void setCipherAlgorithm(String cipherAlgorithm) {
        this.cipherAlgorithm = cipherAlgorithm;
    }
}
