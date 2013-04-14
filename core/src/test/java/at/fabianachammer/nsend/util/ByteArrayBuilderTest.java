/**
 * 
 */
package at.fabianachammer.nsend.util;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * This class tests the PrimitiveArrayList class.
 * 
 * @author fabian
 * 
 */
public class ByteArrayBuilderTest {

	@Test
	public final void testAppendWithAnyDataWorks() {
		byte[] expectedArray = new byte[3];

		expectedArray[0] = (byte) 0;
		expectedArray[1] = (byte) 0;
		expectedArray[2] = (byte) 0;

		ByteArrayBuilder arrayBuilder = new ByteArrayBuilder();

		arrayBuilder.append((byte) 0, (byte) 0, (byte) 0);

		assertArrayEquals(expectedArray, arrayBuilder.toArray());
	}

}
