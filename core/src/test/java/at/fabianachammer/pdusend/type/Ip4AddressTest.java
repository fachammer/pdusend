package at.fabianachammer.pdusend.type;

/**
 * 
 */

import org.junit.Test;

import static org.junit.Assert.*;

import at.fabianachammer.pdusend.type.Ip4Address;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.Ip4AddressDecoder;

/**
 * @author fabian
 * 
 */
public class Ip4AddressTest {

	@Test
	public final void testEncodeOnIp4AddressWorks() {
		Ip4Address address = new Ip4Address(1);
		byte[] expected = { 0, 0, 0, 1 };

		byte[] actual = address.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void testGetDecoderOnIp4AddressReturnsInstanceOfIp4AddressDecoder() {
		Ip4Address anyIp4Address = new Ip4Address();

		DataUnitDecoder<Ip4Address> decoder =
				anyIp4Address.getDecoder();

		assertTrue(decoder instanceof Ip4AddressDecoder);
	}

	@Test
	public final void testEqualsWithDifferentIp4AddressesOnIp4AddressReturnsFalse() {
		assertFalse(new Ip4Address(1).equals(new Ip4Address(2)));
	}

	@Test
	public final void testEqualsWithEqualIp4AddressesOnIp4AddressReturnsTrue() {
		assertTrue(new Ip4Address(1).equals(new Ip4Address(1)));
	}

	@Test
	public final void testEqualsWithDifferentTypesOnIp4AddressReturnsFalse() {
		assertFalse(new Ip4Address().equals(new Object()));
	}

	@Test
	public final void testHashCodeWithEqualIp4AddressesReturnsEqualHashCodes() {
		assertEquals(new Ip4Address(1).hashCode(),
				new Ip4Address(1).hashCode());
	}

	@Test
	public final void testHashCodeWithDifferentIp4AddresssesReturnsDifferentHashCodes() {
		assertNotEquals(new Ip4Address(1).hashCode(),
				new Ip4Address(2).hashCode());
	}
}
