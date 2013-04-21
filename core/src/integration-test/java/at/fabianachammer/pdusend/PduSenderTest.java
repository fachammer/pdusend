package at.fabianachammer.pdusend;

import java.net.NetworkInterface;
import java.net.SocketException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fabianachammer.pdusend.PduSender;
import at.fabianachammer.pdusend.type.ArpOperation;
import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.HardwareAddressType;
import at.fabianachammer.pdusend.type.Ip4Address;
import at.fabianachammer.pdusend.type.MacAddress;
import at.fabianachammer.pdusend.type.pdu.ArpSegment;
import at.fabianachammer.pdusend.type.pdu.EthernetFrame;
import at.fabianachammer.pdusend.type.pdu.ProtocolDataUnit;
import at.fabianachammer.pdusend.type.pdu.RawDataUnit;

/**
 * This class tests the NSender class. Due to
 * 
 * @author fabian
 */
public class PduSenderTest {

	/**
	 * Loopback Interface for testing purposes.
	 */
	private NetworkInterface loopbackInterface = null;

	@Before
	public final void setUp() {
		try {
			loopbackInterface = NetworkInterface.getByName("lo");
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@After
	public final void tearDown() {
		loopbackInterface = null;
	}

	@Test
	public final void testSendRawTwoByteDataOnLoopbackInterface() {
		PduSender sender = new PduSender();
		final byte[] anyData =
				new byte[] { (byte) 0x10, (byte) 0x01 };
		DataUnit anyDataUnit = new RawDataUnit(anyData);

		sender.send(loopbackInterface, anyDataUnit);
	}

	@Test
	public final void testSendEthernetFrameOnLoopbackInterface() {
		PduSender sender = new PduSender();

		final byte[] anySMac =
				new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		final byte[] anyDMac =
				new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x01 };
		final EtherType anyEtherType = EtherType.Unknown;
		final byte[] anyData = new byte[] { 0x00, 0x11 };
		final DataUnit anyDataUnit = new RawDataUnit(anyData);

		EthernetFrame anyFrame = new EthernetFrame();
		anyFrame.setSourceMacAddress(new MacAddress(anySMac));
		anyFrame.setDestinationMacAddress(new MacAddress(anyDMac));
		anyFrame.setEtherType(anyEtherType);
		anyFrame.setData(anyDataUnit);

		sender.send(loopbackInterface, anyFrame);
	}

	@Test
	public final void testSendRawArpSegmentOnLoopbackInterface() {
		PduSender sender = new PduSender();

		ArpSegment arpSegment = new ArpSegment();
		arpSegment.setHardwareType(HardwareAddressType.Ethernet);
		arpSegment.setOperation(ArpOperation.Request);
		arpSegment.setProtocolType(EtherType.Unknown);
		arpSegment.setSenderHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 1 }));
		arpSegment.setSenderProtocolAddress(new Ip4Address(1));
		arpSegment.setTargetHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 2 }));
		arpSegment.setTargetProtocolAddress(new Ip4Address(2));

		sender.send(loopbackInterface, arpSegment);
	}

	@Test
	public final void testSendArpSegmentInEthernetFrameOnLoopbackInterface() {
		PduSender sender = new PduSender();

		ArpSegment arpSegment = new ArpSegment();
		arpSegment.setHardwareType(HardwareAddressType.Ethernet);
		arpSegment.setOperation(ArpOperation.Request);
		arpSegment.setProtocolType(EtherType.Unknown);
		arpSegment.setSenderHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 1 }));
		arpSegment.setSenderProtocolAddress(new Ip4Address(1));
		arpSegment.setTargetHardwareAddress(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 2 }));
		arpSegment.setTargetProtocolAddress(new Ip4Address(2));

		final byte[] anySMac =
				new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		final byte[] anyDMac =
				new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x01 };
		final EtherType anyEtherType = EtherType.ARP;

		EthernetFrame anyFrame = new EthernetFrame();
		anyFrame.setSourceMacAddress(new MacAddress(anySMac));
		anyFrame.setDestinationMacAddress(new MacAddress(anyDMac));
		anyFrame.setEtherType(anyEtherType);
		anyFrame.setData(arpSegment);
		
		sender.send(loopbackInterface, anyFrame);
	}
}
