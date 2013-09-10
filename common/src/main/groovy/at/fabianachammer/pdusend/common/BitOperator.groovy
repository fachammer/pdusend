package at.fabianachammer.pdusend.common

import at.fabianachammer.pdusend.common.validation.Validator

/**
 * This class provides methods for operating with numbers on a bit basis.
 * 
 * @author fabian
 * 
 */
final class BitOperator {

	/**
	 * private constructor as it's an utitlity class
	 */
	private BitOperator() {
	}

	/**
	 * @param value value to check on, if a bit is set
	 * @param index index of the bit to be checked
	 * @return true if the bit was 1, false otherwise
	 */
	static boolean isBitSetOnIndex(Number value, int index){
		newNumberWithBitSetOnIndex(value, index) == value
	}

	/**
	 * @param template bit mask on which to operate the setting of the bit (doesn't change the template)
	 * @param index index to set the bit on
	 * @return new number with same value as the template, but with the bit set on the given index
	 */
	static Number newNumberWithBitSetOnIndex(Number template, int index) {
		int bitToSet = 1 << index

		template | bitToSet
	}

	/**
	 * @param template bit mask on which to operate the clearing of the bit (doesn't change the template)
	 * @param index index to clear the bit on
	 * @return new number with same value as the template, but with the bit cleared on the given index
	 */
	static Number newNumberWithBitClearedOnIndex(Number template, int index){
		int bitToClear = 1 << index
		int clearMask = ~bitToClear

		template & clearMask
	}

	/**
	 * @param value number to get the bit count of
	 * @return number of bits needed to represent the given value, or 1 if the value is non-zero
	 */
	static int bitCountFromNumber(Number value){
		if(value != 0)
			return calculateBitCountIfNotEqualsZero(value)

		return 1
	}

	/**
	 * @param value non-zero number to get the bit count of
	 * @return number of bits needed for representing the non-zero number
	 */
	private static int calculateBitCountIfNotEqualsZero(Number value){
		int bitCount = 0
		int sizeFromType = getSizeInBitsFromType(value.class)

		while(value != 0 && bitCount < sizeFromType){
			value = value >>> 1
			bitCount++
		}

		return bitCount
	}

	/**
	 * @param numberClass class of subtype of number from which to get the size in bits
	 * @return amounts of bits used to store the given number type
	 */
	private static int getSizeInBitsFromType(Class numberClass){
		switch(numberClass){
			case Byte:
				return Byte.SIZE
				break
			case Short:
				return Short.SIZE
				break
			case Integer:
				return Integer.SIZE
				break
			case Long:
				return Long.SIZE
				break
			default:
				throw new UnsupportedOperationException("cannot get size of $numberClass")
		}
	}

	/**
	 * @param value number to get the byte representation of
	 * @param minSizeInBits positive minimum size in bits the whole array should have
	 * @return byte representation of the number in Big Endian order
	 */
	static byte[] toByteArray(BigInteger value, int minSizeInBits){
		validateSizeInBits(minSizeInBits)
		int arraySize = Math.ceil(minSizeInBits / Byte.SIZE)
		List result = []
		byte[] values = toByteArray(value)

		int dataLength = arraySize - values.length
		if(dataLength > 0)
			result << zeros(dataLength)

		result << values

		return result.flatten()
	}

	/**
	 * validates the given size in bits for the toByteArray method.
	 * @param sizeInBits size in bits to validate
	 */
	private static void validateSizeInBits(int sizeInBits){
		Validator v = new Validator(sizeInBits, "sizeInBits")
		v.validateGreaterThan(0)
	}

	/**
	 * @param arrayLength length of the zeros byte array
	 * @return byte array filled with zeros of the given array length
	 */
	private static byte[] zeros(int arrayLength){
		return new byte[arrayLength]
	}

	/**
	 * @param value number to get the byte representation of.
	 * @return byte array representation of the given value in Big Endian order
	 */
	static byte[] toByteArray(BigInteger value) {
		byte[] returnValue = value.toByteArray()

		if (doesZeroSignBitNeedAnExtraByte(value)) {
			return returnValue[1..returnValue.length - 1]
		}
		return returnValue
	}

	/**
	 * Returns whether the storage of the sign bit of this value would start a next byte block.
	 * @param value number to check
	 * @return true if the value needs an extra byte, false otherwise
	 */
	private static boolean doesZeroSignBitNeedAnExtraByte(BigInteger value){
		int valueSizeInBits = getNecessarySizeInBitsFromValue(value)
		value.signum() == 1 && value.testBit(valueSizeInBits - 1)
	}

	/**
	 * @param value number to get the necessary size in bits from
	 * @return amount of bytes needed to store the given value times 8
	 */
	private static int getNecessarySizeInBitsFromValue(BigInteger value){
		int realSizeInBits = value.bitLength()
		int lengthRemainder = realSizeInBits % Byte.SIZE

		if (lengthRemainder != 0) {
			realSizeInBits += Byte.SIZE - lengthRemainder
		}

		return realSizeInBits
	}
}
