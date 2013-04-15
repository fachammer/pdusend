package at.fabianachammer.pdusend.pdu;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.pdu.ArpOperation;
import at.fabianachammer.pdusend.pdu.ArpSegment;
import at.fabianachammer.pdusend.pdu.EtherType;
import at.fabianachammer.pdusend.pdu.HardwareAddressType;
import at.fabianachammer.pdusend.pdu.Ip4Address;
import at.fabianachammer.pdusend.pdu.MacAddress;

/**
 * @author fabian
 * 
 */
public class ARPSegmentTest {

	@Test
	public final void testToBytesOnArpSegmentWithRequestWorks() {
		ArpSegment arpSegment = new ArpSegment();
		arpSegment.setHardwareType(HardwareAddressType.Ethernet);
		arpSegment.setProtocolType(EtherType.IPv4);
		arpSegment.setOperation(ArpOperation.Request);
		arpSegment.setSenderHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 1 }));
		arpSegment.setSenderProtocolAddress(new Ip4Address(new byte[] {
				0, 0, 0, 1 }));
		arpSegment.setTargetHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 2 }));
		arpSegment.setTargetProtocolAddress(new Ip4Address(new byte[] {
				0, 0, 0, 2 }));

		byte[] expected =
				new byte[] {
						0, 1, 0x08, 0, 6, 4, 0, 1, 0, 0, 0, 0, 0,
						1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2 };
		
		byte[] actual = arpSegment.toBytes();
		
		assertArrayEquals(expected, actual);
	}

	@Test
	public final void testToBytesOnArpSegmentWithReplyWorks() {
		ArpSegment arpSegment = new ArpSegment();
		arpSegment.setHardwareType(HardwareAddressType.Ethernet);
		arpSegment.setProtocolType(EtherType.IPv4);
		arpSegment.setOperation(ArpOperation.Reply);
		arpSegment.setSenderHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 1 }));
		arpSegment.setSenderProtocolAddress(new Ip4Address(new byte[] {
				0, 0, 0, 1 }));
		arpSegment.setTargetHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 2 }));
		arpSegment.setTargetProtocolAddress(new Ip4Address(new byte[] {
				0, 0, 0, 2 }));

		byte[] expected =
				new byte[] {
						0, 1, 0x08, 0, 6, 4, 0, 2, 0, 0, 0, 0, 0,
						1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2 };
		
		byte[] actual = arpSegment.toBytes();
		
		assertArrayEquals(expected, actual);
	}
	
	
}
