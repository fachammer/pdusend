package at.fabianachammer.pdusend.type.pdu.decoder;

import static org.junit.Assert.*;

import org.junit.Test;
import at.fabianachammer.pdusend.type.pdu.ArpPacket;

/**
 * @author fabian
 * 
 */
public class ArpPacketDecoderTest {

	@Test
	public final void decodeWithDefaultValuesOfArpPacketReturnsDefaultArpPacket() {
		ArpPacketDecoder decoder = new ArpPacketDecoder();
		byte[] input =
			{
					0, 0, 0, 0, 6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		ArpPacket expected = new ArpPacket();

		ArpPacket actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public final void decodeWithNullThrowsNullPointerException() {
		new ArpPacketDecoder().decode(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void decodeWithLowerThanMinimumSizeInputThrowsIllegalArgumentException() {
		new ArpPacketDecoder().decode(new byte[27]);
	}
}
