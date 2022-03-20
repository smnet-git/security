package com.smnet.security.base64;

import java.util.Base64;

public class Base64Utils {

    public static String base64Encoder(byte[] bytes) {

        Base64.Encoder encoder = Base64.getEncoder();

        String encode = encoder.encodeToString(bytes);

        return encode;
    }

    public static byte[] base64Decoder(String encode) {

        Base64.Decoder decoder = Base64.getDecoder();

        byte[] decode = decoder.decode(encode);

        return decode;
    }

    public static String base64UrlEncoder(byte[] encodeBytes, boolean padding) {

        Base64.Encoder urlEncoder = padding
                ? Base64.getUrlEncoder()
                : Base64.getUrlEncoder().withoutPadding();

        String encode = urlEncoder.encodeToString(encodeBytes);

        return encode;
    }

    public static byte[] base64UrlDecoder(String encode) {

        Base64.Decoder urlDecoder = Base64.getUrlDecoder();

        byte[] decode = urlDecoder.decode(encode);

        return decode;
    }
}
