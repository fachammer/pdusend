/**
 * 
 */
package at.fabianachammer.nsend.util;

/**
 * This class provides methods for converting byte data into a text
 * representation.
 * 
 * @author fabian
 * 
 */
public final class ByteConverter {

	/**
	 * Private constructor because this is an utility class.
	 */
	private ByteConverter() {
	}

	/**
	 * Converts an array of byte-class elements into an array of byte-primitive
	 * elements.
	 * 
	 * @param array
	 *            byte-class array to be converted
	 * @return byte-primitive array with the corresponding values from the
	 *         byte-class array
	 */
	public static byte[] toByteArray(final Byte[] array) {
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
		Byte[] halfBytes = BitOperator.split(fullByte);

		return Integer.toHexString(halfBytes[0])
				+ Integer.toHexString(halfBytes[1]);
	}
}
