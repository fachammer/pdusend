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
		short highShift = (short) (highByte << Byte.SIZE);
		return (short) (highShift | lowByte);
	}

	/**
	 * Merges four byte numbers into a 32-bit number.
	 * 
	 * @param lowlowByte
	 *            least significant byte of the 32-bit number
	 * @param lowHighByte
	 *            second least significant byte of the 32-bit number
	 * @param highLowByte
	 *            second most significant byte of the 32-bit number
	 * @param highHighByte
	 *            most significant byte of the 32-bit number
	 * @return merged 32-bit number
	 */
	public static int merge(final byte lowlowByte,
			final byte lowHighByte, final byte highLowByte,
			final byte highHighByte) {
		return (highHighByte << (Short.SIZE + Byte.SIZE))
				| (highLowByte << Short.SIZE)
				| (lowHighByte << Byte.SIZE) | lowlowByte;
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

	/**
	 * Splits an BitInteger value into chunks of bytes and puts them in an array
	 * with the specified size. If the value needs more space than specified,
	 * the give array size is ignored.
	 * 
	 * @param value
	 *            value to split
	 * @param arraySize
	 *            size of the resulting array (ignored, if value needs a bigger
	 *            array)
	 * @return byte array containing the split value (there are leading zero
	 *         bytes, if the given array size is bigger than the needed array
	 *         size of the value
	 */
	public static byte[] split(final BigInteger value,
			final int arraySize) {
		ByteArrayBuilder bab = new ByteArrayBuilder();
		byte[] values = value.toByteArray();
		if (values.length < arraySize) {
			bab.append(new byte[arraySize
					- values.length]);
		}

		bab.append(values);
		return bab.toArray();
	}

	/**
	 * Splits a BigInteger value into chunks of byte data.
	 * 
	 * @param value
	 *            value to split
	 * @return byte array containing the split BigInteger
	 */
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
