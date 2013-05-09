package at.fabianachammer.pdusend.type.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.HardwareAddressType;

/**
 * @author fabian
 * 
 */
public class HardwareAddressTypeDecoderTest {

	@Test
	public final void decodeWithZeroTwoByteIdReturnsUnknownHardwareAddressType() {
		HardwareAddressTypeDecoder decoder =
				new HardwareAddressTypeDecoder();
		byte[] validTwoByteId = { 0, 0 };
		HardwareAddressType expected = HardwareAddressType.UNKOWN;

		HardwareAddressType actual = decoder.decode(validTwoByteId);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithZeroOneByteIdReturnsUnknownHardwareAddressType() {
		HardwareAddressTypeDecoder decoder =
				new HardwareAddressTypeDecoder();
		byte validOneByteId = 0;
		HardwareAddressType expected = HardwareAddressType.UNKOWN;

		HardwareAddressType actual = decoder.decode(validOneByteId);

		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void decodeWithIllegalSizeByteArrayThrowsIllegalArgumentException() {
		HardwareAddressTypeDecoder decoder =
				new HardwareAddressTypeDecoder();
		byte[] illegalSizeByteArray = new byte[3];

		decoder.decode(illegalSizeByteArray);
	}

	@Test(expected = NullPointerException.class)
	public final void decodeWithNullThrowsNullPointerException() {
		HardwareAddressTypeDecoder decoder =
				new HardwareAddressTypeDecoder();

		decoder.decode(null);
	}
}
