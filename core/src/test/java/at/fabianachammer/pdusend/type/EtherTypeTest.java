package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.EtherTypeDecoder;

/**
 * @author fabian
 * 
 */
public class EtherTypeTest {

	/**
	 * Test method for
	 * {@link at.fabianachammer.pdusend.type.EtherType#getDecoder()}.
	 */
	@Test
	public final void testGetDecoderOnEtherTypeReturnsInstanceOfEtherTypeDecoder() {
		EtherType anyEtherType = EtherType.Unknown;

		DataUnitDecoder<EtherType> decoder =
				anyEtherType.getDecoder();

		assertTrue(decoder instanceof EtherTypeDecoder);
	}

	/**
	 * Test method for {@link at.fabianachammer.pdusend.type.EtherType#encode()}
	 * .
	 */
	@Test
	public final void testEncodeOnEtherTypeWorks() {
		EtherType anyEtherType = EtherType.Unknown;
		byte[] expected = { 0, 0 };

		byte[] actual = anyEtherType.encode();

		assertArrayEquals(expected, actual);
	}

}
