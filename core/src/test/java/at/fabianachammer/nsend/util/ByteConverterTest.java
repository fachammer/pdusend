package at.fabianachammer.nsend.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class tests the ByteConverter class.
 * 
 * @author fabian
 * 
 */
public class ByteConverterTest {

	@Test
	public final void testToHexStringWithAnyData() {
		final byte[] data =
				{ (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33 };

		final String expected = "00112233";
		String actual = ByteConverter.toHexString(data);

		assertEquals(expected, actual);
	}

	@Test
	public final void testToByteArrayWithAnyData() {
		Byte[] input = { (byte) 0, (byte) 0, (byte) 0 };
		byte[] expected = { (byte) 0, (byte) 0, (byte) 0 };

		byte[] actual = ByteConverter.toByteArray(input);

		assertArrayEquals(expected, actual);
	}

}
