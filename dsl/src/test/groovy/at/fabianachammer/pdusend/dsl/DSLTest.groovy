package at.fabianachammer.pdusend.dsl

import static org.junit.Assert.*
import static org.mockito.Mockito.*

import org.junit.Test
import at.fabianachammer.pdusend.sender.NetworkSender
import at.fabianachammer.pdusend.type.AtomicDataUnit

class DSLTest {

	@Test
	public void sendWithAtomicDataUnitReturnsDSLObjectWithSpecifiedDataUnit() {
		DSL dsl = new DSL(mock(NetworkSender.class))
		AtomicDataUnit adu = new AtomicDataUnit(1)
		
		DSL actual = dsl.send(adu)
		
		assertTrue(adu.equals(actual.dataUnit))
	}

	@Test
	public void onOnDSLWithSetDataUnitAndNetworkInterfaceExecutesSendOnMockSender(){
		AtomicDataUnit adu = new AtomicDataUnit(1)
		NetworkInterface networkInterface = NetworkInterface.getByName("lo")
		NetworkSender mockSender = mock(NetworkSender.class)
		DSL dsl = new DSL(mockSender)
		
		dsl.send(adu).on(networkInterface)
		
		verify(mockSender).send(networkInterface, adu)
	}
}
