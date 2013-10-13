package at.fabianachammer.pdusend.sender;

import static org.mockito.Mockito.mock;

import java.net.NetworkInterface;
import java.net.SocketException;

import org.junit.Before;
import org.junit.Test;

import at.fabianachammer.pdusend.sender.NetworkSender;
import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.sender.NetworkSenderImpl;

/**
 * @author fabian
 * 
 */
public class NetworkSenderImplTest {

	private NetworkInterface loopback;

	@Before
	public void setUp() {
		try {
			loopback = NetworkInterface.getByName("lo");
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = NullPointerException.class)
	public final void sendWithNullInterfaceThrowsNullPointerException() {
		NetworkSender sender = new NetworkSenderImpl();

		sender.send(mock(DataUnit.class), null);
	}

	@Test(expected = NullPointerException.class)
	public final void sendWithNullDataUnitThrowsNullPointerException() {
		NetworkSender sender = new NetworkSenderImpl();

		sender.send(null, loopback);
	}
}
