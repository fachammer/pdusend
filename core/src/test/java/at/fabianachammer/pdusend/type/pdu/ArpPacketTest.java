package at.fabianachammer.pdusend.type.pdu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import at.fabianachammer.pdusend.type.ArpOperation;
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.HardwareAddressType;
import at.fabianachammer.pdusend.type.Ip4Address;
import at.fabianachammer.pdusend.type.MacAddress;
import at.fabianachammer.pdusend.type.pdu.decoder.ArpPacketDecoder;

/**
 * @author fabian
 * 
 */
public class ArpPacketTest {

	@Test
	public final void testEncodeWithUnknownAsProtocolTypeOnArpSegmentWithRequestWorks() {
		ArpPacket arpPacket = new ArpPacket();
		arpPacket.setHardwareType(HardwareAddressType.ETHERNET);
		arpPacket.setProtocolType(EtherType.UNKNOWN);
		arpPacket.setOperation(ArpOperation.REQUEST);
		arpPacket.setSenderHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 1 }));
		arpPacket.setSenderProtocolAddress(new Ip4Address(1));
		arpPacket.setTargetHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 2 }));
		arpPacket.setTargetProtocolAddress(new Ip4Address(2));

		byte[] expected =
				new byte[] {
						0, 1, 0, 0, 6, 4, 0, 1, 0, 0, 0, 0, 0, 1, 0,
						0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2 };

		byte[] actual = arpPacket.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void testEncodeWithUnknownAsProtocolTypeOnArpSegmentWithReplyWorks() {
		ArpPacket arpPacket = new ArpPacket();
		arpPacket.setHardwareType(HardwareAddressType.ETHERNET);
		arpPacket.setProtocolType(EtherType.UNKNOWN);
		arpPacket.setOperation(ArpOperation.REPLY);
		arpPacket.setSenderHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 1 }));
		arpPacket.setSenderProtocolAddress(new Ip4Address(1));
		arpPacket.setTargetHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 2 }));
		arpPacket.setTargetProtocolAddress(new Ip4Address(2));

		byte[] expected =
				new byte[] {
						0, 1, 0, 0, 6, 4, 0, 2, 0, 0, 0, 0, 0, 1, 0,
						0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2 };

		byte[] actual = arpPacket.encode();

		assertArrayEquals(expected, actual);
	}
	
	@Test(expected = NullPointerException.class)
	public final void testSetNullHardwareTypeOnArpSegmentThrowsNullPointerException() {
		ArpPacket arpPacket = new ArpPacket();

		arpPacket.setHardwareType(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullProtocolTypeOnArpSegmentThrowsNullPointerException() {
		ArpPacket arpPacket = new ArpPacket();

		arpPacket.setProtocolType(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullOperationOnArpSegmentThrowsNullPointerException() {
		ArpPacket arpPacket = new ArpPacket();

		arpPacket.setOperation(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullSenderHardwareAddressOnArpSegmentThrowsNullPointerException() {
		ArpPacket arpPacket = new ArpPacket();

		arpPacket.setSenderHardwareAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullSenderProtocolAddressOnArpSegmentThrowsNullPointerException() {
		ArpPacket arpPacket = new ArpPacket();

		arpPacket.setSenderProtocolAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullTargetHardwareAddressOnArpSegmentThrowsNullPointerException() {
		ArpPacket arpPacket = new ArpPacket();

		arpPacket.setTargetHardwareAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullTargetProtocolAddressOnArpSegmentThrowsNullPointerException() {
		ArpPacket arpPacket = new ArpPacket();

		arpPacket.setTargetProtocolAddress(null);
	}

	@Test
	public final void testEqualsWithEqualArpSegmentsOnArpSegmentReturnsTrue() {
		assertTrue(new ArpPacket().equals(new ArpPacket()));
	}

	@Test
	public final void testEqualsWithDifferentArpSegmentsOnArpSegmentReturnsFalse() {
		ArpPacket arpPacket = new ArpPacket();
		ArpPacket differentArpPacket = new ArpPacket();
		differentArpPacket.setProtocolType(EtherType.ARP);

		assertFalse(arpPacket.equals(differentArpPacket));
	}

	@Test
	public final void testEqualsWithNullOnArpSegmentReturnsFalse() {
		assertFalse(new ArpPacket().equals(null));
	}

	@Test
	public final void testEqualsWithDifferentTypesOnArpSegmentReturnsFalse() {
		assertFalse(new ArpPacket().equals(new Object()));
	}

	@Test
	public final void testHashCodeWithEqualArpSegmentsOnArpSegmentReturnsEqualHashCodes() {
		assertEquals(new ArpPacket().hashCode(),
				new ArpPacket().hashCode());
	}

	@Test
	public final void testHashCodeWithDifferentArpSegmentsOnArpSegmentReturnsDifferentHashCodes() {
		ArpPacket arpPacket = new ArpPacket();
		ArpPacket differentArpPacket = new ArpPacket();
		differentArpPacket.setProtocolType(EtherType.ARP);

		assertNotEquals(arpPacket.hashCode(),
				differentArpPacket.hashCode());
	}

	@Test
	public final void getDecoderReturnsInstanceOfArpPacketDecoder() {
		assertTrue(new ArpPacket().getDecoder() instanceof ArpPacketDecoder);
	}

	@Test
	public final void sizeReturns28() {
		assertEquals(28, new ArpPacket().size());
	}
}
