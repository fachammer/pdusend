package at.fabianachammer.pdusend.dsl

import static org.junit.Assert.*
import static org.mockito.Mockito.*

import org.junit.Test
import at.fabianachammer.pdusend.sender.NetworkSender
import at.fabianachammer.pdusend.type.AtomicDataUnit
import at.fabianachammer.pdusend.type.DataUnit

class DSLTest {

	@Test
	void sendWithAtomicDataUnitReturnsDSLObjectWithSpecifiedDataUnit() {
		DSL dsl = new DSL(mock(NetworkSender.class))
		
		AtomicDataUnit adu = new AtomicDataUnit(1)
		
		DSL actual = dsl.send(adu)
		
		assertTrue(adu.equals(actual.dataUnit))
	}

	@Test
	void onOnDSLWithSetDataUnitAndNetworkInterfaceExecutesSendOnMockSender(){
		AtomicDataUnit adu = new AtomicDataUnit(1)
		NetworkInterface loopback = NetworkInterface.getByName('lo')
		NetworkSender mockSender = mock(NetworkSender.class)
		DSL dsl = new DSL(mockSender)
		
		dsl.with{
			send adu on loopback
		}
		
		verify(mockSender).send(loopback, adu)
	}
}
