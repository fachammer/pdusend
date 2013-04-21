package at.fabianachammer.pdusend.type.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.pdu.RawDataUnit;
import at.fabianachammer.pdusend.type.pdu.decoder.RawDataUnitDecoder;

/**
 * @author fabian
 * 
 */
public class RawDataUnitDecoderTest {

	@Test
	public final void testDecodeWithAnyByteArrayOnRawDataUnitDecoderWorks() {
		RawDataUnitDecoder decoder = new RawDataUnitDecoder();
		RawDataUnit expected = new RawDataUnit((byte) 0);

		RawDataUnit actual = decoder.decode((byte) 0);
		
		assertEquals(expected, actual);
	}

}
