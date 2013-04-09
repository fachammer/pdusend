package at.fabianachammer.nsend;

import java.net.NetworkInterface;
import java.net.SocketException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public final void testSendRawDataOnLoopbackInterfaceWithVlanTag() {
		NSender sender = new NSender();
		final Byte[] anyData = new Byte[] { (byte) 0x10, (byte) 0x01 };
		ProtocolDataUnit anyDataUnit = new RawDataUnit(anyData);

		sender.send(loopbackInterface, anyDataUnit);
	}
}
