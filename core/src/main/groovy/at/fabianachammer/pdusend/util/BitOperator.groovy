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
	
	static boolean isBitSetOnIndex(Number value, Number index){
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
	 * @param set
	 *            state to which the bit should be set (true: 1, false: 0)
	 * @return value that changed the bit on the specified index to the
	 *         specified state
	 */
	static int setBitOnIndex(Number value, Number index) {
		int setMask = (1 << index)
		value | setMask
	}

	static int clearBitOnIndex(Number value, Number index){
		int clearMask = (-1) ^ (1 << index)
		value & clearMask
	}
}
