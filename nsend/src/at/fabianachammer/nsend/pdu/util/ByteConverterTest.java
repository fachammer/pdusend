/**
 * 
 */
package at.fabianachammer.nsend.pdu.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class tests the ByteConverter class.
 * 
 * @author fabian
 * 
 */
public class ByteConverterTest {

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.pdu.util.ByteConverter#toHexString(byte[])}
	 * .
	 */
	@Test
	public final void testToHexString() {
		final byte[] data = { (byte) 0xFF, (byte) 0xBB, (byte) 0xAA, 0 };

		final String expected = "ffbbaa00";
		String actual = ByteConverter.toHexString(data);

		assertEquals(expected, actual);
	}

}
