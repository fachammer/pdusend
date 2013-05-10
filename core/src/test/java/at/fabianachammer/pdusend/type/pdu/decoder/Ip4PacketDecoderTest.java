package at.fabianachammer.pdusend.type.pdu.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.pdu.Ip4Packet;
import at.fabianachammer.pdusend.type.pdu.decoder.Ip4PacketDecoder;

/**
 * @author fabian
 * 
 */
public class Ip4PacketDecoderTest {

	@Test
	public final void decodeWithDefaultValueBytesReturnsDefaultIp4Packet() {
		Ip4PacketDecoder decoder = new Ip4PacketDecoder();
		byte[] input =
				{
						0x45, 0, 0, 0x14, 0, 0, 0, 0, 0, (byte) 0xff,
						(byte) 0xb9, (byte) 0xec, 0, 0, 0, 0, 0, 0,
						0, 0 };
		Ip4Packet expected = new Ip4Packet();

		Ip4Packet actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void decodeWithSmallerThanMinimumIp4PacketSizeInputThrowsIllegalArgumentException() {
		Ip4PacketDecoder decoder = new Ip4PacketDecoder();
		byte[] input = new byte[19];

		decoder.decode(input);
	}

	@Test(expected = NullPointerException.class)
	public final void decodeWithNullInputThrowsNullPointerException() {
		new Ip4PacketDecoder().decode(null);
	}
}
