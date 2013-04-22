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
	public final void testDecodeWithDefaultValuesOfArpSegmentOnArpSegmentDecoderReturnsDefaultArpSegment() {
		ArpPacketDecoder decoder = new ArpPacketDecoder();
		ArpPacket expected = new ArpPacket();
		byte[] input =
				{
						0, 0, 0, 0, 6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		ArpPacket actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public final void testDecodeWithNullOnArpSegmentDecoderThrowsNullPointerException() {
		new ArpPacketDecoder().decode(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testDecodeWithLowerThanMinimumSizeInputThrowsIllegalArgumentException() {
		new ArpPacketDecoder().decode(new byte[27]);
	}
}
