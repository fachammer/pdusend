package at.fabianachammer.pdusend.sender

import static org.junit.Assert.*

import org.junit.Test

class NetworkSenderFactoryImplTest {

	@Test
	public void createNetworkSenderReturnsInstanceOfNetworkSenderImpl() {
		NetworkSenderFactory nsf = new NetworkSenderFactoryImpl()
		NetworkSender networkSender = nsf.createNetworkSender()
		
		assertTrue(networkSender instanceof NetworkSenderImpl)
	}

}
