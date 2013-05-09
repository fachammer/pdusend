package at.fabianachammer.pdusend.type.pdu.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.TagProtocol;
import at.fabianachammer.pdusend.type.pdu.VlanTag;

/**
 * @author fabian
 * 
 */
public class VlanTagDecoderTest {

	@Test
	public final void decodeWithZeroBitsInputReturnsVlanTagWithUnknownTagProtocol() {
		VlanTagDecoder decoder = new VlanTagDecoder();
		VlanTag expected = new VlanTag();
		expected.setTagProtocol(TagProtocol.UNKNOWN);
		byte[] input = { 0, 0, 0, 0 };

		VlanTag actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void decodeWithIllegalSizeInputThrowsIllegalArgumentException() {
		VlanTagDecoder decoder = new VlanTagDecoder();
		byte[] illegalSizeInput = new byte[5];

		decoder.decode(illegalSizeInput);
	}

	@Test(expected = NullPointerException.class)
	public final void decodeWithNullThrowsNullPointerException() {
		new VlanTagDecoder().decode(null);
	}

}
