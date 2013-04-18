package at.fabianachammer.pdusend.type;

/**
 * 
 */

import org.junit.Test;
import static org.junit.Assert.*;

import at.fabianachammer.pdusend.type.MacAddress;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.MacAddressDecoder;

/**
 * @author fabian
 * 
 */
public class MacAddressTest {

	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalSizeByteArrayOnMacAddressThrowsIllegalArgumentException() {
		MacAddress macAddress = new MacAddress();
		byte[] illegalSizeByteArray = new byte[7];

		macAddress.setBytes(illegalSizeByteArray);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testConstructMacAddressWithIllegalSizeByteArrayThrowsIllegalArgumentException() {
		byte[] illegalSizeByteArray = new byte[7];

		new MacAddress(illegalSizeByteArray);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullByteArrayOnMacAddressThrowsNullPointerException() {
		MacAddress macAddress = new MacAddress();

		macAddress.setBytes(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testConstructMacAddressWithNullByteArrayThrowsNullPointerException() {
		new MacAddress(null);
	}

	@Test
	public final void testSetValidByteArrayOnMacAddressWorks() {
		MacAddress macAddress = new MacAddress();
		byte[] validMacByteArray = new byte[6];

		macAddress.setBytes(validMacByteArray);
	}

	@Test
	public final void testConstructMacAddressWithValidByteArrayWorks() {
		byte[] validMacByteArray = new byte[6];

		new MacAddress(validMacByteArray);
	}

	public final void testGetDecoderOnMacAddressReturnsInstanceOfMacAddressDecoder() {
		MacAddress anyMacAddress = new MacAddress();

		DataUnitDecoder<MacAddress> decoder =
				anyMacAddress.getDecoder();

		assertTrue(decoder instanceof MacAddressDecoder);
	}

}
