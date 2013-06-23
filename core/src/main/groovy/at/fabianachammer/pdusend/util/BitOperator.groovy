package at.fabianachammer.pdusend.util;

import at.fabianachammer.pdusend.util.validation.Validator

/**
 * This class provides methods for operating with numbers on a bit basis.
 * 
 * @author fabian
 * 
 */
final class BitOperator {

	/**
	 * Private Constructor because this is an utility class.
	 */
	private BitOperator() {
	}

	static boolean isBitSetOnIndex(Number value, int index){
		setBitOnIndex(value, index) == value
	}

	/**
	 * Sets a bit in a value to a specified state (true for one, false for
	 * zero).
	 * 
	 * @param value
	 *            value to be changed
	 * @param index
	 *            index of the bit to manipulate
	 * @return value that changed the bit on the specified index to the
	 *         specified state
	 */
	static Number setBitOnIndex(Number value, int index) {
		int bitToSet = 1 << index

		value | bitToSet
	}

	static Number clearBitOnIndex(Number value, int index){
		int bitToClear = 1 << index
		int clearMask = ~bitToClear

		value & clearMask
	}

	static int bitCountFromNumber(Number value){
		if(value != 0)
			return calculateBitCountIfNotEqualsZero(value)

		return 1
	}

	private static int calculateBitCountIfNotEqualsZero(Number value){
		int bitCount = 0
		int sizeFromType = getSizeInBitsFromType(value.class)

		while(value != 0 && bitCount < sizeFromType){
			value = value >>> 1
			bitCount++
		}

		return bitCount
	}

	private static int getSizeInBitsFromType(Class numberClass){
		switch(numberClass){
			case Byte.class:
				return Byte.SIZE
				break
			case Short.class:
				return Short.SIZE
				break
			case Integer.class:
				return Integer.SIZE
				break
			case Long.class:
				return Long.SIZE
				break
			default: 
				throw new UnsupportedOperationException("cannot get size of $numberClass")
		}
	}

	static byte[] toByteArray(BigInteger value, int sizeInBits){
		validateSizeInBits(sizeInBits)
		int arraySize = Math.ceil(sizeInBits / Byte.SIZE)
		List result = []
		byte[] values = split(value);

		int dataLength = arraySize - values.length
		if(dataLength > 0)
			result << zeros(dataLength)
		
		result << values

		return result.flatten();
	}
	
	private static void validateSizeInBits(int sizeInBits){
		Validator v = new Validator(sizeInBits, "sizeInBits")
		v.validateGreaterThan(0)
	}

	private static byte[] zeros(int arrayLength){
		return new byte[arrayLength]
	}

	private static byte[] split(final BigInteger value) {
		byte[] returnValue = value.toByteArray()

		if (doesZeroSignBitNeedAnExtraByte(value)) {
			return returnValue[1..returnValue.length - 1];
		}
		return returnValue
	}

	private static boolean doesZeroSignBitNeedAnExtraByte(BigInteger value){
		int valueSizeInBits = getNecessaraySizeInBitsFromValue(value)
		value.signum() == 1 && value.testBit(valueSizeInBits - 1)
	}

	private static int getNecessaraySizeInBitsFromValue(BigInteger value){
		int realSizeInBits = value.bitLength()
		int lengthRemainder = realSizeInBits % Byte.SIZE

		if (lengthRemainder != 0) {
			realSizeInBits += Byte.SIZE- lengthRemainder
		}

		return realSizeInBits
	}
}
