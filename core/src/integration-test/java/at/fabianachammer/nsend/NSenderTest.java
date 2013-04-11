package at.fabianachammer.nsend;

import java.net.NetworkInterface;
import java.net.SocketException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fabianachammer.nsend.pdu.EtherType;
import at.fabianachammer.nsend.pdu.EthernetFrame;
import at.fabianachammer.nsend.pdu.ProtocolDataUnit;
import at.fabianachammer.nsend.pdu.RawDataUnit;

/**
 * This class tests the NSender class. Due to
 * 
 * @author fabian
 */
public class NSenderTest {

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
		NSender sender = new NSender();
		final Byte[] anyData = new Byte[] { (byte) 0x10, (byte) 0x01 };
		ProtocolDataUnit anyDataUnit = new RawDataUnit(anyData);

		sender.send(loopbackInterface, anyDataUnit);
	}

	@Test
	public final void testSendEthernetFrameOnLoopbackInterface() {
		NSender sender = new NSender();

		final Byte[] anySMac =
				new Byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		final Byte[] anyDMac =
				new Byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x01 };
		final EtherType anyEtherType = EtherType.IPv4;
		final Byte[] anyData = new Byte[] { 0x00, 0x11 };
		final ProtocolDataUnit anyDataUnit = new RawDataUnit(anyData);

		EthernetFrame anyFrame = new EthernetFrame();
		anyFrame.setSourceMacAddress(anySMac);
		anyFrame.setDestinationMacAddress(anyDMac);
		anyFrame.setEtherType(anyEtherType);
		anyFrame.setData(anyDataUnit);

		sender.send(loopbackInterface, anyFrame);
	}
}
