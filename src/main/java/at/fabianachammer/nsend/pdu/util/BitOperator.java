package at.fabianachammer.nsend.pdu.util;

/**
 * This class provides methods for operating with numbers on a bit basis.
 * 
 * @author fabian
 * 
 */
public final class BitOperator {

	/**
	 * Maximum value of a half-byte.
	 */
	public static final int MAX_HALF_BYTE = 0xF;

	/**
	 * Maximum value of a byte.
	 */
	public static final int MAX_BYTE = 0xFF;

	/**
	 * Private Constructor because this is an utility class.
	 */
	private BitOperator() {
	}

	/**
	 * Merges a low-byte number and a high-byte number to form one 16 bit
	 * number.
	 * 
	 * @param lowByte
	 *            low-byte to merge
	 * @param highByte
	 *            high-byte to merge
	 * @return merged 16 bit number
	 */
	public static short merge(final byte lowByte, final byte highByte) {
		return (short) (lowByte + (highByte << Byte.SIZE));

	}

	/**
	 * Splits a 16 bit value into an array of two bytes in Little Endian Order
	 * (first element represents the low-byte).
	 * 
	 * @param value
	 *            value to be split
	 * @return array with two bytes in Little Endian Order
	 */
	public static byte[] split(final short value) {

		byte[] bytes = new byte[2];
		bytes[0] = (byte) (value & MAX_BYTE);
		bytes[1] = (byte) (value >>> Byte.SIZE);

		return bytes;
	}

	/**
	 * Splits a byte value into an array of two half-bytes in Little Endian
	 * Order (first element represents the low-half-byte).
	 * 
	 * @param value
	 *            value to be split
	 * @return array with two half-bytes in Little Endian Order
	 */
	public static byte[] split(final byte value) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (value & MAX_HALF_BYTE);
		bytes[1] = (byte) ((value & MAX_BYTE) >>> (Byte.SIZE / 2));

		return bytes;
	}
}
