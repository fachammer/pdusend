/**
 * 
 */
package at.fabianachammer.pdusend.util;

import java.math.BigInteger;

/**
 * This class provides methods for converting byte data into a text
 * representation.
 * 
 * @author fabian
 * 
 */
public final class ByteConverter {

	/**
	 * prefix for byte arrays to make BigIntegers to keep leading zero bits.
	 */
	private static final byte PREFIX = (byte) 0b00000001;

	/**
	 * Private constructor because this is an utility class.
	 */
	private ByteConverter() {
	}

	/**
	 * Converts an array of bytes that represents a 2's complement (without the
	 * sign bit) into it's binary representation (as if their had been a one
	 * sign bit).
	 * 
	 * @param array
	 *            array that represents the 2's complement of a number
	 * @return byte array that contains the binary representation of a 2's
	 *         complement number
	 */
	public static byte[] toTwosComplement(final byte[] array) {
		BigInteger bi =
				new BigInteger(prefix(array))
						.subtract(BigInteger.ONE).not();

		byte[] twosComplementWithPrefix = bi.toByteArray();
		byte[] twosComplement = new byte[array.length];

		System.arraycopy(twosComplementWithPrefix,
				twosComplementWithPrefix.length
						- twosComplement.length, twosComplement, 0,
				twosComplement.length);

		return twosComplement;
	}

	/**
	 * Converts an array of byte-class elements into an array of byte-primitive
	 * elements.
	 * 
	 * @param array
	 *            byte-class array to be converted
	 * @return byte-primitive array with the corresponding values from the
	 *         byte-class array or null if the byte-class-array was null
	 */
	public static byte[] toByteArray(final Byte[] array) {

		if (array == null) {
			return null;
		}

		byte[] byteArray = new byte[array.length];
		for (int i = 0; i < array.length; i++) {
			byteArray[i] = array[i].byteValue();
		}

		return byteArray;
	}

	/**
	 * Converts an array of byte-primitive elements into an array of byte-class
	 * elements.
	 * 
	 * @param array
	 *            byte-primitive array to be converted
	 * @return byte-class array with the corresponding values from the
	 *         byte-primitive array
	 */
	public static Byte[] toByteArray(final byte[] array) {
		Byte[] byteArray = new Byte[array.length];
		for (int i = 0; i < array.length; i++) {
			byteArray[i] = array[i];
		}

		return byteArray;
	}

	/**
	 * Converts an array of raw byte data into a hexadecimal representation.
	 * 
	 * @param data
	 *            data to be converted
	 * @return hexadecimal representation of the data
	 */
	public static String toHexString(final byte[] data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			sb.append(convertByteToHexString(data[i]));
		}

		return sb.toString();
	}

	/**
	 * Converts a byte into its hexadecimal representation (with leading zeros).
	 * 
	 * @param fullByte
	 *            byte to be converted
	 * @return hexadecimal representation of the byte
	 */
	private static String convertByteToHexString(final byte fullByte) {
		byte[] halfBytes = BitOperator.split(fullByte);

		return Integer.toHexString(halfBytes[0])
				+ Integer.toHexString(halfBytes[1]);
	}

	/**
	 * Appends a prefix-byte (0000 0001 in binary) to a byte array for making a
	 * BigInteger to keep leading zero bits.
	 * 
	 * @param array
	 *            array to be prefixed
	 * @return prefixed array
	 */
	private static byte[] prefix(byte[] array) {
		ByteArrayBuilder bab = new ByteArrayBuilder();

		bab.append(PREFIX);
		bab.append(array);

		return bab.toArray();
	}
}
