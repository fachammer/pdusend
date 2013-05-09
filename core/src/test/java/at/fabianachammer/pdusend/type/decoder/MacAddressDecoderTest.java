package at.fabianachammer.pdusend.type.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.MacAddress;

/**
 * @author fabian
 * 
 */
public class MacAddressDecoderTest {

	@Test
	public final void decodeWithZeroLengthByteArrayReturnsDefaultMacAddress() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected = new MacAddress();
		byte[] input = new byte[0];

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithOneZeroByteReturnsZeroMacAddress() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 0 });
		byte input = 0;

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithTwoZeroBytesReturnsZeroMacAddress() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 0 });
		byte[] input = { 0, 0 };

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithThreeZeroBytesReturnsZeroMacAddress() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 0 });
		byte[] input = { 0, 0, 0 };

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithFourZeroBytesReturnsZeroMacAddress() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 0 });
		byte[] input = { 0, 0, 0, 0 };

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithFiveZeroBytesReturnsZeroMacAddress() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 0 });
		byte[] input = { 0, 0, 0, 0, 0 };

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithSixZeroBytesReturnsZeroMacAddress() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 0 });
		byte[] input = { 0, 0, 0, 0, 0, 0 };

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public final void decodeWithNullThrowsNullPointerException() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		decoder.decode(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void decodeWithBiggerThanMacAddressSizeByteArrayThrowsIllegalArgumentException() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		byte[] biggerThanMacAddressSizeByteArray = new byte[7];

		decoder.decode(biggerThanMacAddressSizeByteArray);
	}
}
