package at.fabianachammer.pdusend.type.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.TagProtocol;

/**
 * @author fabian
 * 
 */
public class TagProtocolDecoderTest {

	@Test
	public final void decodeWithOneZeroByteReturnsUnknownTagProtocol() {
		TagProtocolDecoder decoder = new TagProtocolDecoder();
		byte input = 0;
		TagProtocol expected = TagProtocol.UNKNOWN;

		TagProtocol actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithTwoZeroBytesReturnsUnkownTagProtocol() {
		TagProtocolDecoder decoder = new TagProtocolDecoder();
		byte[] input = { 0, 0 };
		TagProtocol expected = TagProtocol.UNKNOWN;

		TagProtocol actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void decodeWithIllegalSizeByteArrayThrowsIllegalArgumentException() {
		TagProtocolDecoder decoder = new TagProtocolDecoder();
		byte[] illegalSizeArray = new byte[3];

		decoder.decode(illegalSizeArray);
	}

	@Test(expected = NullPointerException.class)
	public final void decodeWithNullThrowsNullPointerException() {
		new TagProtocolDecoder().decode(null);
	}

}
