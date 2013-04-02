/**
 * 
 */
package at.fabianachammer.nsend.pdu.util;

/**
 * This class provides methods for converting byte-class-objects to
 * byte-primitive-objects.
 * 
 * @author fabian
 * 
 */
public final class BytePrimitiveConverter {

	/**
	 * Private constructor because this is a utility class.
	 */
	private BytePrimitiveConverter() {

	}

	/**
	 * Converts an array of byte-class-elements into an array of
	 * byte-primitve-elements.
	 * 
	 * @param array
	 *            byte-class-array to be converted
	 * @return byte-primitve-array with the corresponding values from the
	 *         byte-class-array
	 */
	public static byte[] convertByteArray(final Byte[] array) {
		byte[] byteArray = new byte[array.length];
		for (int i = 0; i < array.length; i++) {
			byteArray[i] = array[i].byteValue();
		}

		return byteArray;
	}
}
