package at.fabianachammer.pdusend.dsl;

import static org.junit.Assert.*;

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.junit.Test;
import at.fabianachammer.pdusend.Sender;
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.pdu.ArpPacket
import at.fabianachammer.pdusend.type.pdu.EthernetFrame
import at.fabianachammer.pdusend.type.pdu.RawDataUnit
import static org.mockito.Mockito.*;

/**
 * tests various example pdusend dsl scripts.
 * @author fabian
 *
 */
class InterpreterTest {

	Vocabulary vocabulary = new Vocabulary()
	Sender mockSender = mock(Sender.class)
	NetworkInterface loopback = NetworkInterface.getByName("lo")

	private final void testScript(String scriptName){
		Interpreter interpreter = new Interpreter()
		interpreter.vocabulary.sender = mockSender
		interpreter.interpret(new File("src/integrationTest/scripts/" + scriptName + ".pdusend"))
	}

	private final void verifySendOnMockSender(DataUnit expected, String scriptName){
		testScript(scriptName)
		verify(mockSender).send(loopback, expected)
	}

	@Test
	public void sendPositiveInteger(){
		verifySendOnMockSender(new RawDataUnit([1] as byte[]), "sendPositiveInteger")
	}

	@Test
	public void sendByteArray(){
		verifySendOnMockSender(new RawDataUnit([1] as byte[]), "sendByteArray")
	}

	@Test
	public void sendCastPositiveLong(){
		verifySendOnMockSender(new RawDataUnit([1] as byte[]), "sendCastPositiveLong")
	}

	@Test
	public void sendCastPositiveBigInteger(){
		verifySendOnMockSender(new RawDataUnit([1] as byte[]), "sendCastPositiveBigInteger")
	}

	@Test
	public void sendPositiveBigInteger(){
		verifySendOnMockSender(new RawDataUnit([1, 0, 0, 0, 0, 0, 0, 0, 0] as byte[]), "sendPositiveBigInteger")
	}

	@Test
	public void sendPositiveLong(){
		verifySendOnMockSender(new RawDataUnit([1, 0, 0, 0, 0] as byte[]), "sendPositiveLong")
	}
	
	@Test
	public void sendDefaultEthernetFrame(){
		verifySendOnMockSender(new EthernetFrame(), "sendDefaultEthernetFrame")
	}
	
	@Test
	public void sendDefaultArpPacket(){
		verifySendOnMockSender(new ArpPacket(), "sendDefaultArpPacket")
	}
	
	@Test
	public void sendDefaultArpPacketInDefaultEthernetFrame(){
		EthernetFrame ef = new EthernetFrame()
		ef.embeddedData = new ArpPacket()
		verifySendOnMockSender(ef, "sendDefaultArpPacketInDefaultEthernetFrame")
	}
}
