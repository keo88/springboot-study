package com.keokim.ncphw.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class SignatureBuilder {

    public enum Method {
        GET,
        POST
    }

    private static final String space = " ";
    private static final String newLine = "\n";

    public static String makeSignature(Method method, String path, String timestamp, String accessKey, String secretKey) throws GeneralSecurityException {
        String message = method +
                space +
                path +
                newLine +
                timestamp +
                newLine +
                accessKey;
        System.out.printf("Signature: %s\n", message);

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(rawHmac);
    }
}
