package at.fabianachammer.pdusend.type;

/**
 * 
 */

import org.junit.Test;

import static org.junit.Assert.*;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.Ip4AddressDecoder;

/**
 * @author fabian
 * 
 */
public class Ip4AddressTest {

	@Test
	public final void encodeWithZeroIp4AddressReturnsAllZeroByteArray() {
		Ip4Address address = new Ip4Address(0);
		byte[] expected = { 0, 0, 0, 0 };

		byte[] actual = address.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void getDecoderReturnsInstanceOfIp4AddressDecoder() {
		Ip4Address anyIp4Address = new Ip4Address();

		DataUnitDecoder<Ip4Address> decoder =
				anyIp4Address.getDecoder();

		assertTrue(decoder instanceof Ip4AddressDecoder);
	}

	@Test
	public final void equalsWithDifferentIp4AddressesReturnsFalse() {
		assertFalse(new Ip4Address(1).equals(new Ip4Address(2)));
	}

	@Test
	public final void equalsWithEqualIp4AddressesReturnsTrue() {
		assertTrue(new Ip4Address(1).equals(new Ip4Address(1)));
	}

	@Test
	public final void equalsWithDifferentTypesOnIp4AddressReturnsFalse() {
		assertFalse(new Ip4Address().equals(new Object()));
	}

	@Test
	public final void equalsWithNullReturnsFalse() {
		assertFalse(new Ip4Address().equals(null));
	}

	@Test
	public final void hashCodeWithEqualIp4AddressesReturnsEqualHashCodes() {
		assertEquals(new Ip4Address(1).hashCode(),
				new Ip4Address(1).hashCode());
	}

	@Test
	public final void hashCodeWithDifferentIp4AddresssesReturnsDifferentHashCodes() {
		assertNotEquals(new Ip4Address(1).hashCode(), new Ip4Address(
				2).hashCode());
	}

	@Test
	public final void toStringWithZeroIPAddressReturnsDecimalPointRepresentation() {
		Ip4Address address = new Ip4Address();
		String expected = "0.0.0.0";

		assertEquals(expected, address.toString());
	}
	
	@Test
	public final void sizeOfIp4AddressReturnsFour(){
		assertEquals(4, new Ip4Address().size());
	}
}
