package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.HardwareAddressTypeDecoder;

/**
 * @author fabian
 * 
 */
public class HardwareAddressTypeTest {

	/**
	 * Test method for
	 * {@link at.fabianachammer.pdusend.type.HardwareAddressType#getDecoder()}.
	 */
	@Test
	public final void testGetDecoderOnHardwareAddressTypeReturnsInstanceOfHardwareAddressTypeDecoder() {
		HardwareAddressType anyHardwareAddressType =
				HardwareAddressType.Ethernet;

		DataUnitDecoder<HardwareAddressType> decoder =
				anyHardwareAddressType.getDecoder();

		assertTrue(decoder instanceof HardwareAddressTypeDecoder);
	}

	/**
	 * Test method for
	 * {@link at.fabianachammer.pdusend.type.HardwareAddressType#encode()}.
	 */
	@Test
	public final void testEncodeOnHardwareAddressTypeWorks() {
		HardwareAddressType anyHardwareAddressType =
				HardwareAddressType.Ethernet;
		byte[] expected = { 0, 1 };

		byte[] actual = anyHardwareAddressType.encode();

		assertArrayEquals(expected, actual);
	}

}
