package at.fabianachammer.pdusend.sender

import static org.junit.Assert.*

import at.fabianachammer.pdusend.type.AtomicDataUnit
import org.junit.Test

class NetworkSenderImplTest {

	@Test(expected = NullPointerException)
	void sendWithNullDataUnitThrowsNullPointerException() {
		NetworkSender s = new NetworkSenderImpl()
		NetworkInterface loopback = NetworkInterface.getByName('lo')
		
		s.send(null, loopback)
	}
	
	@Test(expected = NullPointerException)
	void sendWithNullNetworkInterfaceThrowsNullPointerException(){
		NetworkSender s = new NetworkSenderImpl()
		
		s.send(new AtomicDataUnit([1] as byte[]), null)
	}

	
}
