/**
 * 
 */
package at.fabianachammer.nsend.pdu.util;

/**
 * This class provides methods for converting byte data into a text
 * representation.
 * 
 * @author fabian
 * 
 */
public final class ByteConverter {
	
	/**
	 * Maximum value for a half-byte.
	 */
	private static final byte HEX_F = 0xF;

	/**
	 * Private constructor because this is an utility class.
	 */
	private ByteConverter() {
	}

	/**
	 * Converts an array of raw byte data into a hexadecimal representation.
	 * @param data data to be converted
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
	 * Converts a half-byte into a hexadecimal character.
	 * @param halfByte half-byte to be converted
	 * @return hexadecimal character which represents the half-byte
	 */
	private static String convertHalfByteToHexString(final byte halfByte) {
		if (halfByte < 0 || halfByte > HEX_F) {
			throw new IllegalArgumentException("not a half byte");
		}

		return Integer.toHexString(halfByte);
	}

	/**
	 * Converts a byte into its hexadecimal representation (with leading zeros).
	 * @param fullByte byte to be converted
	 * @return hexadecimal representation of the byte
	 */
	private static String convertByteToHexString(final byte fullByte) {
		byte[] halfBytes = BitOperator.split(fullByte);

		return convertHalfByteToHexString(halfBytes[0])
				+ convertHalfByteToHexString(halfBytes[1]);
	}
}
