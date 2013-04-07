/**
 * 
 */
package at.fabianachammer.nsend.util;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

import at.fabianachammer.nsend.util.PrimitiveArrayList;

/**
 * @author fabian
 * 
 */
public class PrimitiveArrayListTest {

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.util.PrimitiveArrayList#addArray(T[])}
	 * .
	 */
	@Test
	public final void testAddArray() {
		ArrayList<Byte> expectedArrayList = new ArrayList<Byte>();

		expectedArrayList.add((byte) 0);
		expectedArrayList.add((byte) 1);
		expectedArrayList.add((byte) 2);

		final Byte[] array = { (byte) 0, (byte) 1, (byte) 2 };

		PrimitiveArrayList<Byte> actualArrayList =
				new PrimitiveArrayList<Byte>();

		actualArrayList.addArray(array);

		assertArrayEquals(
				expectedArrayList.toArray(new Byte[0]),
				actualArrayList.toArray(new Byte[0]));
	}

}
