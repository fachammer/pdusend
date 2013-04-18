package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.TagProtocolDecoder;

/**
 * @author fabian
 * 
 */
public class TagProtocolTest {

	/**
	 * Test method for
	 * {@link at.fabianachammer.pdusend.type.TagProtocol#getDecoder()}.
	 */
	@Test
	public final void testGetDecoderOnTagProtocolReturnsInstanceOfTagProtocolDecoder() {
		TagProtocol anyTagProtocol = TagProtocol.IEEE_802_1Q;

		DataUnitDecoder<TagProtocol> decoder =
				anyTagProtocol.getDecoder();

		assertTrue(decoder instanceof TagProtocolDecoder);
	}

	/**
	 * Test method for
	 * {@link at.fabianachammer.pdusend.type.TagProtocol#encode()}.
	 */
	@Test
	public final void testEncodeOnTagProtocolWorks() {
		TagProtocol anyTagProtocol = TagProtocol.IEEE_802_1Q;
		byte[] expected = { (byte) 0x81, 0x00 };

		byte[] actual = anyTagProtocol.encode();

		assertArrayEquals(expected, actual);
	}

}
