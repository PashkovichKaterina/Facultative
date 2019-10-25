package by.trjava.pashkovich.facultative.service.encrypting;

import by.trjava.pashkovich.facultative.service.exception.EncryptException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Class is used password hashing.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @since JDK1.0
 */
public class Encrypt {
    private static final int ITERATIONS = 1000;

    /**
     * Password hashing algorithm.
     */
    private static final String HASHING_ALGORITHM = "PBKDF2WithHmacSHA512";

    /**
     * Pseudo random number generator for salt hash.
     */
    private static final String RNG_ALGORITHM = "SHA1PRNG";

    /**
     * Real password hash length.
     */
    private static final int PASSWORD_LENGTH = 32;


    /**
     * Password hash length.
     */
    private static final int PASSWORD_HASH_LENGTH = 64 * 8;

    /**
     * Salt length.
     */
    private static final int SALT_LENGTH = 16;

    /**
     * Returns the string representation of password hash with salt.
     *
     * @param password password to hash.
     * @return password hash with salt.
     * @see SecretKeyFactory
     * @see PBEKeySpec
     * @see HexadecimalSystem
     */
    public static String generatePasswordHash(String password) throws EncryptException {
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, PASSWORD_HASH_LENGTH);
        SecretKeyFactory skf;
        byte[] hash;
        try {
            skf = SecretKeyFactory.getInstance(HASHING_ALGORITHM);
            hash = skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new EncryptException(e.getMessage(), e);
        }
        return HexadecimalSystem.toHex(salt) + HexadecimalSystem.toHex(hash);
    }

    /**
     * Returns the salt for the password generate by {@value RNG_ALGORITHM} algorithm.
     *
     * @return the salt for the password.
     * @throws EncryptException
     * @see SecureRandom
     */
    private static byte[] getSalt() throws EncryptException {
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance(RNG_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException(e.getMessage(), e);
        }
        byte[] salt = new byte[SALT_LENGTH];
        sr.nextBytes(salt);
        return salt;
    }

    /**
     * Checks if password matches hash.
     *
     * @param originalPassword original user password.
     * @param passwordHash     password hash.
     * @return {@code true} if password matches hash, {@code false} otherwise.
     * @see HexadecimalSystem
     * @see PBEKeySpec
     * @see SecretKeyFactory
     */
    public static boolean validatePassword(String originalPassword, String passwordHash) throws EncryptException {
        byte[] salt = HexadecimalSystem.fromHex(passwordHash.substring(0, PASSWORD_LENGTH));
        byte[] hash = HexadecimalSystem.fromHex(passwordHash.substring(PASSWORD_LENGTH));

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, ITERATIONS, hash.length * 8);
        SecretKeyFactory skf;
        byte[] testHash;
        try {
            skf = SecretKeyFactory.getInstance(HASHING_ALGORITHM);
            testHash = skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new EncryptException(e.getMessage(), e);
        }
        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
}
