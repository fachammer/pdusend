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
	public final void testDecodeWithOneByteInputOnTagProtocolDecoderWorks() {
		TagProtocolDecoder decoder = new TagProtocolDecoder();
		byte input = 1;
		TagProtocol expected = new TagProtocol((short) 1);

		TagProtocol actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithTwoByteInputOnTagProtocolDecoderWorks() {
		TagProtocolDecoder decoder = new TagProtocolDecoder();
		TagProtocol expected = TagProtocol.IEEE_802_1Q;
		byte[] validByteArray = { (byte) 0x81, 0 };
		short id = (short) 0x8100;

		TagProtocol actual = decoder.decode(validByteArray);

		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testDecodeWithIllegalSizeByteArrayOnTagProtoclThrowsIllegalArgumentException() {
		TagProtocolDecoder decoder = new TagProtocolDecoder();
		byte[] illegalSizeArray = new byte[3];

		decoder.decode(illegalSizeArray);
	}

	@Test(expected = NullPointerException.class)
	public final void testDecodeWithNullOnTagProtocolThrowsNullPointerException() {
		new TagProtocolDecoder().decode(null);
	}

}
