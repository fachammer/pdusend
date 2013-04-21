package at.fabianachammer.pdusend.type.pdu.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.pdu.RawDataUnit;

/**
 * @author fabian
 * 
 */
public class RawDataUnitDecoderTest {

	@Test
	public final void testDecodeWithZeroBitsByteArrayOnRawDataUnitDecoderWorks() {
		RawDataUnitDecoder decoder = new RawDataUnitDecoder();
		RawDataUnit expected = new RawDataUnit((byte) 0);
		byte[] input = { 0 };

		RawDataUnit actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public final void testDecodeWithNullOnRawDataUnitDecoderThrowsNullPointerException() {
		new RawDataUnitDecoder().decode(null);
	}
}
