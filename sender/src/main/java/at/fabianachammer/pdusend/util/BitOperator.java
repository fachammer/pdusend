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
	private static final int MAX_HALF_BYTE = 0xf;

	/**
	 * Maximum value of a byte.
	 */
	private static final int MAX_BYTE = 0xff;

	/**
	 * Private Constructor because this is an utility class.
	 */
	private BitOperator() {
	}

	/**
	 * Merges a low-half-byte number and a high-half-byte number to form a byte
	 * number.
	 * 
	 * @param lowHalfByte
	 *            low-half-byte to merge
	 * @param highHalfByte
	 *            high-half-byte to merge
	 * @return merged byte number
	 */
	public static byte mergeByte(final byte lowHalfByte,
			final byte highHalfByte) {
		return (byte) ((lowHalfByte & MAX_HALF_BYTE) | ((highHalfByte & MAX_HALF_BYTE) << Byte.SIZE / 2));
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
		return (short) (highShift | (lowByte & MAX_BYTE));
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
		return ((highHighByte & MAX_BYTE) << (Short.SIZE + Byte.SIZE))
				| ((highLowByte & MAX_BYTE) << Short.SIZE)
				| ((lowHighByte & MAX_BYTE) << Byte.SIZE)
				| (lowlowByte & MAX_BYTE);
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
	 * splits a 32 bit value into an array with two shorts in Big Endian Order
	 * (highest short first).
	 * 
	 * @param value
	 *            value to be split
	 * @return array with two short values in Big Endian Order
	 */
	public static short[] splitToShort(final int value) {
		short[] values = new short[Integer.SIZE
				/ Short.SIZE];

		for (int i = 0; i < values.length; i++) {
			int bitsSet = (-1) >>> (i * Short.SIZE);

			short calcShort =
					(short) ((value & bitsSet) >>> ((values.length
							- i - 1) * Short.SIZE));
			values[i] = calcShort;
		}

		return values;
	}

	/**
	 * Splits an BitInteger value into chunks of bytes and puts them in an array
	 * with the specified size. If the value needs more space than specified,
	 * the give array size is ignored. The sign of the value is ignored and cut
	 * off during calculation
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
		byte[] values = split(value);
		if (arraySize > values.length) {
			bab.append(new byte[arraySize
					- values.length]);
		}

		bab.append(values);
		return bab.toArray();
	}

	/**
	 * Splits a BigInteger value into chunks of byte data. Ignores the sign bit
	 * and only calculates with the bits after it.
	 * 
	 * @param value
	 *            value to split
	 * @return byte array containing the split BigInteger
	 */
	public static byte[] split(final BigInteger value) {
		int length = value.bitLength();
		int lengthRemainder = length
				% Byte.SIZE;

		if (lengthRemainder != 0) {
			length += Byte.SIZE
					- (length % Byte.SIZE);
		}

		if (value.signum() == 1
				&& value.testBit(length - 1)) {
			byte[] returnValue =
					new byte[value.toByteArray().length - 1];
			System.arraycopy(value.toByteArray(), 1, returnValue, 0,
					returnValue.length);
			return returnValue;
		}
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

	/**
	 * Inverts every single bit of a given 16 bit value.
	 * 
	 * @param value
	 *            value to be inverted
	 * @return inverted 16 bit value
	 */
	public static short invert(final short value) {
		return (short) (value
				* ((short) -1) - 1);

	}

	/**
	 * Sets a bit in a value to a specified state (true for one, false for
	 * zero).
	 * 
	 * @param value
	 *            value to be changed
	 * @param index
	 *            index of the bit to manipulate
	 * @param set
	 *            state to which the bit should be set (true: 1, false: 0)
	 * @return value that changed the bit on the specified index to the
	 *         specified state
	 */
	public static int setBit(final int value, final int index,
			final boolean set) {
		int bitToSet = 1 << index;
		int sign = -1;

		if (set) {
			sign = 1;
		}

		if ((BitOperator.isSet(value, index) && set)
				|| (!BitOperator.isSet(value, index) && !set)) {
			return value;
		} else {
			return value
					+ sign * bitToSet;
		}
	}
}
