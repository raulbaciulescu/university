package com.example.socialapp.utils;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Locale;


public final class PasswordAuthentication
{
//    public static String hash(String password) throws NoSuchAlgorithmException {
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//        MessageDigest md = MessageDigest.getInstance("SHA-512");
//        md.update(salt);
//        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_16));
//        return new String(hashedPassword);
//    }
    public static String hash(final String password, final String username)
        throws NoSuchAlgorithmException {
    final String salt = username.toUpperCase(Locale.ROOT);
    final MessageDigest messageDigest =
            MessageDigest.getInstance("SHA-256");
    messageDigest.update((username + password + salt).getBytes());

    final byte[] digest = messageDigest.digest();
    final StringBuilder hashedPassword = new StringBuilder();
    for (final byte b : digest) {
        hashedPassword.append(String.format("%02x", b & 0xff));
    }
    return hashedPassword.toString();
}
}