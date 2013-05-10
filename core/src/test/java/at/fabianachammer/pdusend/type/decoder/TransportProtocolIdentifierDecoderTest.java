package at.fabianachammer.pdusend.type.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.TransportProtocolIdentifier;

/**
 * @author fabian
 * 
 */
public class TransportProtocolIdentifierDecoderTest {

	@Test
	public void decodeWithAllBitsSetByteReturnsUnknownTransportProtocolIdentifier() {
		TransportProtocolIdentifierDecoder decoder =
				new TransportProtocolIdentifierDecoder();
		byte input = (byte) 0xff;

		TransportProtocolIdentifier actual = decoder.decode(input);

		assertEquals(TransportProtocolIdentifier.UNKNOWN, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void decodeWithZeroSizeByteArrayThrowsIllegalArgumentException() {
		TransportProtocolIdentifierDecoder decoder =
				new TransportProtocolIdentifierDecoder();
		byte[] input = new byte[0];

		decoder.decode(input);
	}

	@Test(expected = NullPointerException.class)
	public void decodeWithNullThrowsNullPointerException() {
		new TransportProtocolIdentifierDecoder().decode(null);
	}

}
