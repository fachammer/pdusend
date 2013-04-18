package at.fabianachammer.pdusend.type.pdu;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.ArpOperation;
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.HardwareAddressType;
import at.fabianachammer.pdusend.type.Ip4Address;
import at.fabianachammer.pdusend.type.MacAddress;

/**
 * @author fabian
 * 
 */
public class ArpSegmentTest {

	@Test
	public final void testEncodeOnArpSegmentWithRequestWorks() {
		ArpSegment arpSegment = new ArpSegment();
		arpSegment.setHardwareType(HardwareAddressType.Ethernet);
		arpSegment.setProtocolType(EtherType.IPv4);
		arpSegment.setOperation(ArpOperation.Request);
		arpSegment.setSenderHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 1 }));
		arpSegment.setSenderProtocolAddress(new Ip4Address(1));
		arpSegment.setTargetHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 2 }));
		arpSegment.setTargetProtocolAddress(new Ip4Address(2));

		byte[] expected =
				new byte[] {
						0, 1, 0x08, 0, 6, 4, 0, 1, 0, 0, 0, 0, 0, 1,
						0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2 };

		byte[] actual = arpSegment.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void testEncodeOnArpSegmentWithReplyWorks() {
		ArpSegment arpSegment = new ArpSegment();
		arpSegment.setHardwareType(HardwareAddressType.Ethernet);
		arpSegment.setProtocolType(EtherType.IPv4);
		arpSegment.setOperation(ArpOperation.Reply);
		arpSegment.setSenderHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 1 }));
		arpSegment.setSenderProtocolAddress(new Ip4Address(1));
		arpSegment.setTargetHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 2 }));
		arpSegment.setTargetProtocolAddress(new Ip4Address(2));

		byte[] expected =
				new byte[] {
						0, 1, 0x08, 0, 6, 4, 0, 2, 0, 0, 0, 0, 0, 1,
						0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2 };

		byte[] actual = arpSegment.encode();

		assertArrayEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullHardwareTypeOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setHardwareType(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullProtocolTypeOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setProtocolType(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullOperationOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setOperation(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullSenderHardwareAddressOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setSenderHardwareAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullSenderProtocolAddressOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setSenderProtocolAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullTargetHardwareAddressOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setTargetHardwareAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullTargetProtocolAddressOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setTargetProtocolAddress(null);
	}
}
