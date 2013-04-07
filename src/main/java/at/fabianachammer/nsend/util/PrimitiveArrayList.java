/**
 * 
 */
package at.fabianachammer.nsend.util;

import java.util.ArrayList;

/**
 * This utility class provides a method for adding a whole array of elements to
 * an ArrayList.
 * 
 * @author fabian
 * @param <T>
 *            specifies the type of elements the PrimitiveArrayList contains
 * 
 */
public class PrimitiveArrayList<T> extends ArrayList<T> {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method adds an array of elements to the ArrayList.
	 * 
	 * @param array
	 *            array to add to the ArrayList
	 */
	public final void addArray(final T[] array) {
		for (int i = 0; i < array.length; i++) {
			add(array[i]);
		}
	}

}
