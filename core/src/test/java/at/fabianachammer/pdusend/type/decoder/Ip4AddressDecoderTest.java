package at.fabianachammer.pdusend.type.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.Ip4Address;

/**
 * This test only tests whether the Ip4AddressDecoder creates a proper
 * Ip4Address data unit. It does not check validation of invalid inputs (that's
 * done in the Ip4AddressTest).
 * 
 * @author fabian
 * 
 */
public class Ip4AddressDecoderTest {

	public final void testDecodeWithZeroLengthByteArrayInputOnIp4AddressDecoderWorks() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		Ip4Address expected = new Ip4Address();
		byte[] input = new byte[0];

		Ip4Address actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithOneByteInputOnIp4AddressDecoderWorks() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		Ip4Address expected = new Ip4Address(1);
		byte input = 1;

		Ip4Address actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithTwoByteInputOnIp4AddressDecoderWorks() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		Ip4Address expected = new Ip4Address(1);
		byte[] input = { 0, 1 };

		Ip4Address actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithThreeByteInputOnIp4AddressDecoderWorks() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		Ip4Address expected = new Ip4Address(1);
		byte[] input = { 0, 0, 1 };

		Ip4Address actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithFourByteInputOnIp4AddressDecoderWorks() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		Ip4Address expected = new Ip4Address(1);
		byte[] input = { 0, 0, 0, 1 };

		Ip4Address actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithBiggerThanIp4AddressSizeByteArrayOnIp4AddressDecoderOnlyUsesLastBytes() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		byte[] biggerThanIp4AddressSizeByteArray = new byte[5];
		Ip4Address expected = new Ip4Address(0);

		Ip4Address actual =
				decoder.decode(biggerThanIp4AddressSizeByteArray);

		assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public final void testDecodeWithNullOnIp4AddressDecoderThrowsNullPointerException() {
		new Ip4AddressDecoder().decode(null);
	}
}
