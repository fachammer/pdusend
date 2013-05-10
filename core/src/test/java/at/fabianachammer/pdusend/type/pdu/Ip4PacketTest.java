package at.fabianachammer.pdusend.type.pdu;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.Ip4PacketDecoder;

/**
 * @author fabian
 * 
 */
public class Ip4PacketTest {

	@Test
	public void encodeWithDefaultIp4PacketReturnsAllZerosExceptVersion() {
		byte[] expected =
				{
						0x45, 0, 0, 0x14, 0, 0, 0, 0, 0, (byte) 0xff,
						(byte) 0xb9, (byte) 0xec, 0, 0, 0, 0, 0, 0,
						0, 0 };

		byte[] actual = new Ip4Packet().encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public void getDecoderReturnsInstanceOfIp4PacketDecoder() {
		DataUnitDecoder<Ip4Packet> decoder =
				new Ip4Packet().getDecoder();

		assertTrue(decoder instanceof Ip4PacketDecoder);
	}

	@Test
	public void sizeOfDefaultIp4PacketReturns20() {
		assertEquals(20, new Ip4Packet().size());
	}

	@Test
	public void equalsWithEqualDefaultIp4PacketsReturnsTrue() {
		assertTrue(new Ip4Packet().equals(new Ip4Packet()));
	}

	@Test
	public void equalsWithDifferentIp4PacketsReturnsFalse() {
		Ip4Packet packet = new Ip4Packet();
		Ip4Packet differentPacket = new Ip4Packet();
		packet.setFragmented(false);
		differentPacket.setFragmented(true);

		assertFalse(packet.equals(differentPacket));
	}

	@Test
	public void equalsWithDifferentTypeReturnsFalse() {
		assertFalse(new Ip4Packet().equals(new Object()));
	}

	@Test
	public void equalsWithNullReturnsFalse() {
		assertFalse(new Ip4Packet().equals(null));
	}

	@Test
	public void hashCodeWithEqualDefaultIp4PacketsReturnsEqualHashCodes() {
		assertEquals(new Ip4Packet().hashCode(),
				new Ip4Packet().hashCode());
	}

	@Test
	public void hashCodeWithDifferentIp4PacketsReturnsDifferentHashCodes() {
		Ip4Packet packet = new Ip4Packet();
		Ip4Packet differentPacket = new Ip4Packet();
		packet.setFragmented(false);
		differentPacket.setFragmented(true);

		assertNotEquals(packet.hashCode(), differentPacket.hashCode());
	}

	@Test(expected = NullPointerException.class)
	public void setNullServiceTypeThrowsNullPointerException() {
		new Ip4Packet().setServiceType(null);
	}

	@Test(expected = NullPointerException.class)
	public void setNullProtocolThrowsNullPointerException() {
		new Ip4Packet().setProtocol(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setBiggerThanMaximumFragmentOffsetThrowsIllegalArgumentException() {
		new Ip4Packet().setFragmentOffset((short) 8192);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setHeaderChecksumWithSizeOneByteArrayThrowsIllegalArgumentException() {
		new Ip4Packet().setHeaderChecksum(new byte[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setHeaderChecksumWithSizeThreeByteArrayThrowsIllegalArgumentException() {
		new Ip4Packet().setHeaderChecksum(new byte[3]);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setVersionWithGreaterThan15ThrowsIllegalArgumentException(){
		new Ip4Packet().setVersion((byte) 16);
	}
}
