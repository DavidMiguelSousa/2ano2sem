package eapli.base.services;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String NON_ALPHA_CHARS = "!@#$%^&*()-_+=<>?{}[]|/\\";
    private static final String ALL_CHARS = UPPERCASE_LETTERS + LOWERCASE_LETTERS + DIGITS + NON_ALPHA_CHARS;

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Generates a random password.
     *
     * @return The randomly generated password.
     */
    public static String generatePassword(String email) {
        //for a user with email user@domain, the password will be userA1!, being A a random uppercase letter, 1 a random digit and ! a random non-alphanumeric character
        StringBuilder password = new StringBuilder();
        String username = email.split("@")[0];
        username = username.substring(0, Math.min(username.length(), 4));
        password.append(username);
        //append a random uppercase letter
        password.append(UPPERCASE_LETTERS.charAt(RANDOM.nextInt(UPPERCASE_LETTERS.length())));
        //append a random digit
        password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        //append a random non-alphanumeric character
        password.append(NON_ALPHA_CHARS.charAt(RANDOM.nextInt(NON_ALPHA_CHARS.length())));
        //append a random uppercase letter
        password.append(UPPERCASE_LETTERS.charAt(RANDOM.nextInt(UPPERCASE_LETTERS.length())));
        //append a random digit
        password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        //append a random non-alphanumeric character
        password.append(NON_ALPHA_CHARS.charAt(RANDOM.nextInt(NON_ALPHA_CHARS.length())));
        return password.toString();
    }
}