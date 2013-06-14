package at.fabianachammer.pdusend.util;

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
		((1 << index) | value) == value
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
		int setMask = (1 << index)
		value | setMask
	}

	static Number clearBitOnIndex(Number value, int index){
		int clearMask = ~(1 << index)
		value & clearMask
	}
	
	static int bitCountFromNumber(Number value){
		if(value == 0)
			return 1
			
		int bitCount = 0
		while(value != 0){
			value = value >> 1
			bitCount++
		}
		
		return bitCount
	}
}
