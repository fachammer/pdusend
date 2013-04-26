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
	public final void testDecodeWithZeroLengthByteArrayOnMacAddressDecoderWorks() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 0 });
		byte[] input = new byte[0];

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithOneByteOnMacAddressDecoderWorks() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 1 });
		byte input = 1;

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithTwoBytesOnMacAddressDecoderWorks() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 1 });
		byte[] input = { 0, 1 };

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithThreeBytesOnMacAddressDecoderWorks() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 1 });
		byte[] input = { 0, 0, 1 };

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithFourBytesOnMacAddressDecoderWorks() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 1 });
		byte[] input = { 0, 0, 0, 1 };

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithFiveBytesOnMacAddressDecoderWorks() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 1 });
		byte[] input = { 0, 0, 0, 0, 1 };

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithSixBytesOnMacAddressDecoderWorks() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		MacAddress expected =
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 1 });
		byte[] input = { 0, 0, 0, 0, 0, 1 };

		MacAddress actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public final void testDecodeWithNullOnMacAddressDecoderWorks() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		decoder.decode(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testDecodeWithBiggerThanMacAddressSizeByteArrayOnMacAddressDecoderThrowsIllegalArgumentException() {
		MacAddressDecoder decoder = new MacAddressDecoder();
		byte[] biggerThanMacAddressSizeByteArray =
				{ 0, 0, 0, 0, 0, 0, 1 };

		decoder.decode(biggerThanMacAddressSizeByteArray);
	}
}
