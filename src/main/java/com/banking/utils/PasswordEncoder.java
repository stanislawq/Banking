package com.banking.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncoder {
    private static final int ITERATIONS = 65_536;
    private static final int KEY_LENGTH = 128;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    private PasswordEncoder() {
    }

    public static String encode(String password) {
        try {
            byte[] salt = generateSalt();
            byte[] hash = pbkdf2(password.toCharArray(), salt);
            String sSalt = Base64.getEncoder().encodeToString(salt);
            String sHash = Base64.getEncoder().encodeToString(hash);
            return sSalt + ":" + sHash;
        } catch (Exception e) {
            throw new IllegalStateException("Problem appeared", e);
        }
    }

    private static byte[] pbkdf2(char[] password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
        return f.generateSecret(spec).getEncoded();
    }

    private static byte[] generateSalt() {
        SecureRandom rnd = new SecureRandom();
        byte[] salt = new byte[16];
        rnd.nextBytes(salt);
        return salt;
    }
}
