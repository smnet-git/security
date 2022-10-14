package com.smnet.security;

import com.smnet.security.aes.AES256Key;
import com.smnet.security.aes.AES256Utils;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAES256 {

    @Test
    void encodeDecodeAES256() {

        String text = "Test da criptare";

        try {

            AES256Key key = new AES256Key("MyPassword");

            String encode = AES256Utils.encode(text, key);

            System.out.println(encode);

            String decrypt = AES256Utils.decode(encode, key);

            System.out.println(decrypt);

            assertTrue(true);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createOwnKey() {

        try {

            AES256Key key = new AES256Key("Questa è la mia chiave");

            System.out.println("getIvBase64 : " + key.getIvBase64());
            System.out.println("getSaltBase64 : " + key.getSaltBase64());

            // Prendi nota dell'IvBase64 e del SaltBase64

            // Poi puoi creare direttamente la tua key

            AES256Key ownKey = new AES256Key("Questa è la mia chiave", "sSR8UkDHEAk31QTYeiThhw", "pEiFy9oMa6ALj31ku6UBdA");

            System.out.println("getIvBase64 : " + key.getIvBase64());
            System.out.println("getSaltBase64 : " + key.getSaltBase64());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
