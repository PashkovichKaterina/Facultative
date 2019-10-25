package by.trjava.pashkovich.facultative.service.encrypting;

import java.math.BigInteger;

/**
 * Class is used to work with large numbers in the hexadecimal system as a string.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @since JDK 1.0
 */
public class HexadecimalSystem {
    /**
     * Return the string representation of the byte array in the hexadecimal system.
     *
     * @param array byte array.
     * @return the string representation of the byte array.
     */
    public static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    /**
     * Return the byte array representation of the string in the hexadecimal system.
     *
     * @param hex string with number in the hexadecimal system.
     * @return the byte array representation of the string.
     */
    public static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}