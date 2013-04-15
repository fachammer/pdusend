package at.fabianachammer.pdusend.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import at.fabianachammer.pdusend.util.ByteConverter;

/**
 * This class tests the ByteConverter class.
 * 
 * @author fabian
 * 
 */
public class ByteConverterTest {

	@Test
	public final void testToHexStringWithAnyDataWorks() {
		final byte[] anyData =
				{ (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33 };
		final String expected = "00112233";
		
		String actual = ByteConverter.toHexString(anyData);

		assertEquals(expected, actual);
	}

	@Test
	public final void testToPrimitiveByteArrayWithAnyDataWorks() {
		Byte[] anyInput = { (byte) 0, (byte) 0, (byte) 0 };
		byte[] expected = { (byte) 0, (byte) 0, (byte) 0 };

		byte[] actual = ByteConverter.toByteArray(anyInput);

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void testToPrimitiveByteArrayWithNullInputReturnsNull() {
		Byte[] input = null;

		assertNull(ByteConverter.toByteArray(input));
	}

}
