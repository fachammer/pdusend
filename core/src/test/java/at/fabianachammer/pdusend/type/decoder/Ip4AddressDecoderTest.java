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

	@Test
	public final void decodeWithZeroLengthByteArrayInputReturnsDefaultIp4Address() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		Ip4Address expected = new Ip4Address();
		byte[] input = new byte[0];

		Ip4Address actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithZeroOneByteInputReturnsZeroIp4Address() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		Ip4Address expected = new Ip4Address(0);
		byte input = 0;

		Ip4Address actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithZeroTwoByteInputReturnsZeroIp4Address() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		Ip4Address expected = new Ip4Address(0);
		byte[] input = { 0, 0 };

		Ip4Address actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithZeroThreeByteInputReturnsZeroIp4Address() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		Ip4Address expected = new Ip4Address(0);
		byte[] input = { 0, 0, 0 };

		Ip4Address actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithZeroFourByteInputReturnsZeroIp4Address() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		Ip4Address expected = new Ip4Address(0);
		byte[] input = { 0, 0, 0, 0 };

		Ip4Address actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void decodeWithBiggerThanIp4AddressSizeByteArrayThrowsIllegalArgumentException() {
		Ip4AddressDecoder decoder = new Ip4AddressDecoder();
		byte[] biggerThanIp4AddressSizeByteArray = new byte[5];

		decoder.decode(biggerThanIp4AddressSizeByteArray);
	}

	@Test(expected = NullPointerException.class)
	public final void decodeWithNullThrowsNullPointerException() {
		new Ip4AddressDecoder().decode(null);
	}
}
