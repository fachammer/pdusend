package at.fabianachammer.pdusend.util;

import java.math.BigInteger;

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
	 * Splits a 16 bit value into an array of two bytes in Big Endian Order
	 * (first element represents the high-byte).
	 * 
	 * @param value
	 *            value to be split
	 * @return array with two bytes in Big Endian Order
	 */
	public static byte[] split(final short value) {

		byte[] bytes = new byte[2];
		bytes[0] = (byte) (value >>> Byte.SIZE);
		bytes[1] = (byte) (value & MAX_BYTE);

		return bytes;
	}

	/**
	 * Splits a byte value into an array of two half-bytes in Big Endian Order
	 * (first element represents the high-half-byte).
	 * 
	 * @param value
	 *            value to be split
	 * @return array with two half-bytes in Big Endian Order
	 */
	public static byte[] split(final byte value) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) ((value & MAX_BYTE) >>> (Byte.SIZE / 2));
		bytes[1] = (byte) (value & MAX_HALF_BYTE);

		return bytes;
	}

	/**
	 * Splits a 32 bit value into an array with four bytes in Big Endian Order
	 * (highest Byte first).
	 * 
	 * @param value
	 *            value to be split
	 * @return array with four byte values in Big Endian Order
	 */
	public static byte[] split(final int value) {

		byte[] bytes = new byte[Integer.SIZE
				/ Byte.SIZE];

		for (int i = 0; i < bytes.length; i++) {

			int bitsSet = (-1) >>> (i * Byte.SIZE);

			byte calcByte =
					(byte) ((value & bitsSet) >>> ((bytes.length
							- i - 1) * Byte.SIZE));

			bytes[i] = calcByte;
		}

		return bytes;
	}

	public static byte[] split(final BigInteger value, int arraySize) {
		ByteArrayBuilder bab = new ByteArrayBuilder();
		byte[] values = value.toByteArray();
		if (values.length < arraySize) {
			bab.append(new byte[arraySize
					- values.length]);
		}
		
		bab.append(values);
		return bab.toArray();
	}

	public static byte[] split(final BigInteger value) {
		return value.toByteArray();
	}

	/**
	 * Checks whether the n-th bit of a int value (starting from the least
	 * significant bit (LSB)) is set. n=0 checks whether LSB is set.
	 * 
	 * @param value
	 *            value on which the operation is performed
	 * @param n
	 *            position of the bit
	 * @return true, if the bit is set, false otherwise
	 */
	public static boolean isSet(final int value, final int n) {
		int bitShifted = 1 << n;
		return (bitShifted & value) == bitShifted;
	}
}
