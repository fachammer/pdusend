package at.fabianachammer.pdusend.type.pdu.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.pdu.VlanTag;

/**
 * @author fabian
 * 
 */
public class VlanTagDecoderTest {

	@Test
	public final void testDecodeWithZeroBitsInputOnVlanTagDecoderWorks() {
		VlanTagDecoder decoder = new VlanTagDecoder();
		VlanTag expected = new VlanTag();
		byte[] input = { (byte) 0x81, 0, 0, 0 };

		VlanTag actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testDecodeWithIllegalSizeInputOnVlanTagDecoderThrowsIllegalArgumentException() {
		VlanTagDecoder decoder = new VlanTagDecoder();
		byte[] illegalSizeInput = new byte[5];

		decoder.decode(illegalSizeInput);
	}

	@Test(expected = NullPointerException.class)
	public final void testDecodeWithNullInputOnVlanTagDecoderThrowsNullPointerException() {
		new VlanTagDecoder().decode(null);
	}

}
