package at.fabianachammer.pdusend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.NetworkInterface;
import java.net.SocketException;

import org.junit.Before;
import org.junit.Test;

import at.fabianachammer.pdusend.sender.NetworkSender;
import at.fabianachammer.pdusend.type.DataUnit;

/**
 * @author fabian
 * 
 */
public class SenderTest {

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

		sender.send(null, mock(DataUnit.class));
	}

	@Test(expected = NullPointerException.class)
	public final void sendWithNullDataUnitThrowsNullPointerException() {
		NetworkSender sender = new NetworkSenderImpl();

		sender.send(loopback, null);
	}
}
