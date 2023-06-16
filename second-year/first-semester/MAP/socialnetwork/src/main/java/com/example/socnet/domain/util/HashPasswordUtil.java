package com.example.socnet.domain.util;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class HashPasswordUtil {

    public static @NotNull String hash(@NotNull final String password,
                                       @NotNull final String username)
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
