package at.fabianachammer.pdusend.util;

/**
 * This class provides methods for operations on arrays of elements.
 * 
 * @author fabian
 * 
 */
public final class ArrayOperator {

	/**
	 * private constructor because this is a utility class.
	 */
	private ArrayOperator() {
	}

	/**
	 * Checks whether the elements of the both arrays are equal (in position and
	 * value).
	 * 
	 * @param arrayA
	 *            array A
	 * @param arrayB
	 *            array B
	 * @param <T>
	 *            type of the array elements (they have to be comparable)
	 * @return true, if all elements of arrayA are equal to those of arrayB (in
	 *         position and value), false otherwise
	 */
	public static <T extends Comparable<T>> boolean arrayEquals(
			final T[] arrayA, final T[] arrayB) {

		if (arrayA.length != arrayB.length) {
			return false;
		}

		for (int i = 0; i < arrayA.length; i++) {
			if (!arrayA[i].equals(arrayB[i])) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks whether two byte arrays are equal (size and position of each
	 * element equals).
	 * 
	 * @param arrayA
	 *            array A
	 * @param arrayB
	 *            array B
	 * @return true, if all elements of arrayA are equal to those of arrayB (in
	 *         position and value), false otherwise
	 */
	public static boolean arrayEquals(final byte[] arrayA,
			final byte[] arrayB) {
		Byte[] classByteArrayA = ByteConverter.toByteArray(arrayA);
		Byte[] classByteArrayB = ByteConverter.toByteArray(arrayB);

		return arrayEquals(classByteArrayA, classByteArrayB);
	}
}
