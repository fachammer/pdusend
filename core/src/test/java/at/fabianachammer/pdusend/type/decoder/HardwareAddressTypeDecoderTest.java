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
	public final void testDecodeWithValidTwoByteIdOnHardwareAddressTypeDecoderWorks() {
		HardwareAddressTypeDecoder decoder =
				new HardwareAddressTypeDecoder();
		byte[] validTwoByteId = { 0, 1 };
		HardwareAddressType expected = HardwareAddressType.Ethernet;

		HardwareAddressType actual = decoder.decode(validTwoByteId);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithValidOneByteIdOnHardwareAddressTypeDecoderWorks() {
		HardwareAddressTypeDecoder decoder =
				new HardwareAddressTypeDecoder();
		byte validOneByteId = 1;
		HardwareAddressType expected = HardwareAddressType.Ethernet;

		HardwareAddressType actual = decoder.decode(validOneByteId);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithNonExistentIdOnHardwareAddressTypeDecoderReturnsUnknown() {
		HardwareAddressTypeDecoder decoder =
				new HardwareAddressTypeDecoder();
		byte nonExistentId = -1;

		HardwareAddressType actual = decoder.decode(nonExistentId);

		assertEquals(HardwareAddressType.Unknown, actual);
		assertEquals(((byte) -1), actual.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testDecodeWithIllegalSizeByteArrayOnHardwareAddressTypeDecoderThrowsIllegalArgumentException() {
		HardwareAddressTypeDecoder decoder =
				new HardwareAddressTypeDecoder();
		byte[] illegalSizeByteArray = new byte[3];

		decoder.decode(illegalSizeByteArray);
	}

	@Test(expected = NullPointerException.class)
	public final void testDecodeWithNullOnHardwareAddressTypeDecoderThrowsNullPointerException() {
		HardwareAddressTypeDecoder decoder =
				new HardwareAddressTypeDecoder();

		decoder.decode(null);
	}
}
