/**
 * 
 */
package at.fabianachammer.nsend.util;

import java.util.ArrayList;

/**
 * The ByteArrayBuilder is a class that allows appending bytes to an array.
 * 
 * @author fabian
 * 
 */
public class ByteArrayBuilder {

	private ArrayList<Byte> arrayList;

	public ByteArrayBuilder() {
		this.arrayList = new ArrayList<Byte>();
	}

	/**
	 * This method adds an array of elements to the ByteArrayBuilder.
	 * 
	 * @param bytes
	 *            bytes to add to the ByteArrayBuilder
	 */
	public final void append(byte... bytes) {
		for (int i = 0; i < bytes.length; i++) {
			arrayList.add(bytes[i]);
		}
	}

	/**
	 * Puts all appended bytes into a new byte array.
	 * 
	 * @return byte array containing all appended bytes
	 */
	public final byte[] toArray() {
		byte[] array = new byte[arrayList.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = arrayList.get(i);
		}

		return array;
	}

	/**
	 * Returns the number of bytes appended to the ByteArrayBuilder.
	 * 
	 * @return number of bytes appended to the ByteArrayBuilder
	 */
	public final int size() {
		return arrayList.size();
	}
}
