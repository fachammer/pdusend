package at.fabianachammer.pdusend;

import java.net.NetworkInterface;
import java.net.SocketException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import at.fabianachammer.pdusend.NetworkSenderImpl;
import at.fabianachammer.pdusend.sender.NetworkSender;
import at.fabianachammer.pdusend.type.DataUnit;

/**
 * This class tests the NSender class. Due to
 * 
 * @author fabian
 */
public class NetworkSenderImplIntegrationTest {

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
	public final void sendRawTwoByteDataOnLoopbackInterface() {
		NetworkSender sender = new NetworkSenderImpl();
		DataUnit dataUnitStub = mock(DataUnit.class);
		when(dataUnitStub.encode()).thenReturn(
				new byte[] { 0x11, 0x11 });

		sender.send(loopbackInterface, dataUnitStub);
	}
}
