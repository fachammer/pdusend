/**
 * 
 */
package at.fabianachammer.nsend.pdu.util;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * Test class for the BytePrimitiveConverter.
 * 
 * @author fabian
 * 
 */
public class BytePrimitiveConverterTest {

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.pdu.util.BytePrimitiveConverter#convertByteArray(java.lang.Byte[])}
	 * .
	 */
	@Test
	public final void testConvertByteArray() {
		Byte[] input = { (byte) 0, (byte) 1, (byte) 2 };
		byte[] expected = { (byte) 0, (byte) 1, (byte) 2 };

		byte[] actual = BytePrimitiveConverter.convertByteArray(input);

		assertArrayEquals(expected, actual);
	}

}
