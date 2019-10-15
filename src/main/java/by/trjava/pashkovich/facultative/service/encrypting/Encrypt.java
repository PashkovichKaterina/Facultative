package by.trjava.pashkovich.facultative.service.encrypting;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;

public class Encrypt {
    private static final int ITERATIONS = 1000;
    private static final String ENCRYPT_ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final String RNG_ALGORITHM = "SHA1PRNG";
    private static final int PASSWORD_LENGTH = 32;

    public static String generatePasswordHash(String password) {
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, 64 * 8);
        SecretKeyFactory skf = null;
        byte[] hash = null;
        try {
            skf = SecretKeyFactory.getInstance(ENCRYPT_ALGORITHM);
            hash = skf.generateSecret(spec).getEncoded();
        } catch (Exception e) {

        }
        return HexadecimalSystem.toHex(salt) + HexadecimalSystem.toHex(hash);
    }

    private static byte[] getSalt() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance(RNG_ALGORITHM);
        } catch (Exception e) {

        }
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static boolean validatePassword(String originalPassword, String storedPassword) {
        byte[] salt = HexadecimalSystem.fromHex(storedPassword.substring(0, PASSWORD_LENGTH));
        byte[] hash = HexadecimalSystem.fromHex(storedPassword.substring(PASSWORD_LENGTH));

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, ITERATIONS, hash.length * 8);
        SecretKeyFactory skf = null;
        byte[] testHash = null;
        try {
            skf = SecretKeyFactory.getInstance(ENCRYPT_ALGORITHM);
            testHash = skf.generateSecret(spec).getEncoded();
        } catch (Exception e) {

        }

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
}
