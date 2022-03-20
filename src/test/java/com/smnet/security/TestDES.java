package com.smnet.security;

import com.smnet.security.des.DESUtils;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDES {

    @Test
    void encodeDecodeDES() {

        String key = "12345678910235";

        String text = "Test da criptare";

        try {

            SecretKey secretKey = DESUtils.generateSecretKey(key);

            String encode = DESUtils.encode(text, secretKey);

            System.out.println(encode);

            String decrypt = DESUtils.decode(encode, secretKey);

            System.out.println(decrypt);

            assertTrue(true);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
