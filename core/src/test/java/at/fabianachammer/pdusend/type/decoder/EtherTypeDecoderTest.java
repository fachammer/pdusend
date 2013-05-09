package at.fabianachammer.pdusend.type.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.EtherType;

/**
 * @author fabian
 * 
 */
public class EtherTypeDecoderTest {

	@Test
	public final void decodeWithZeroTwoByteIdReturnsUnknownEtherType() {
		EtherTypeDecoder decoder = new EtherTypeDecoder();
		final byte[] validTwoByteId = { 0, 0 };
		EtherType expected = EtherType.UNKNOWN;

		EtherType actual = decoder.decode(validTwoByteId);

		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void decodeWithIllegalSizeByteArrayThrowsIllegalArgumentException() {
		EtherTypeDecoder decoder = new EtherTypeDecoder();
		final byte[] illegalSizeByteArray = new byte[3];

		decoder.decode(illegalSizeByteArray);
	}

	@Test(expected = NullPointerException.class)
	public final void decodeWithNullThrowsNullPointerException() {
		EtherTypeDecoder decoder = new EtherTypeDecoder();

		decoder.decode(null);
	}
}