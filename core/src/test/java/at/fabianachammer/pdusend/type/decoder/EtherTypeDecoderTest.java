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
	public final void testDecodeWithValidTwoByteIdOnEtherTypeDecoderWorks() {
		EtherTypeDecoder decoder = new EtherTypeDecoder();
		final byte[] validTwoByteId = { 0x08, 0 };
		EtherType expected = EtherType.IPv4;

		EtherType actual = decoder.decode(validTwoByteId);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithNonExistentIdOnEtherTypeDecoderReturnsUnknown() {
		EtherTypeDecoder decoder = new EtherTypeDecoder();
		final byte[] nonExistentId = { 0, 1 };

		EtherType actual = decoder.decode(nonExistentId);

		assertEquals(EtherType.Unknown, actual);
		assertEquals(1, actual.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testDecodeWithIllegalSizeByteArrayOnEtherTypeThrowsIllegalArgumentException() {
		EtherTypeDecoder decoder = new EtherTypeDecoder();
		final byte[] illegalSizeByteArray = new byte[3];

		decoder.decode(illegalSizeByteArray);
	}

	@Test(expected = NullPointerException.class)
	public final void testDecodeWithNullOnEtherTypeThrowsNullPointerException() {
		EtherTypeDecoder decoder = new EtherTypeDecoder();

		decoder.decode(null);
	}
}