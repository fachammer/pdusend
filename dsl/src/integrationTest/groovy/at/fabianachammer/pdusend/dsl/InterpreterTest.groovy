package at.fabianachammer.pdusend.dsl;

import static org.junit.Assert.*;

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.junit.Test;
import at.fabianachammer.pdusend.Sender;
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.Ip4Address;
import at.fabianachammer.pdusend.type.pdu.ArpPacket
import at.fabianachammer.pdusend.type.pdu.EthernetFrame
import at.fabianachammer.pdusend.type.pdu.Ip4Packet
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
		interpreter.interpret(new GroovyCodeSource(new File("src/integrationTest/scripts/" + scriptName)))
	}

	private final void verifySendOnMockSender(DataUnit expected, String scriptName){
		expected.encode()
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
	
	@Test
	public void sendDefaultIp4PacketInDefaultEthernetFrame(){
		EthernetFrame ef = new EthernetFrame()
		ef.embeddedData = new Ip4Packet()
		
		verifySendOnMockSender(ef, "sendDefaultIp4PacketInDefaultEthernetFrame")
	}
	
	@Test
	public void sendChangedIp4PacketInDefaultEthernetFrame(){
		EthernetFrame ef = new EthernetFrame()
		Ip4Packet ip = new Ip4Packet()
		Ip4Address ip4addr = new Ip4Address()
		ip4addr.setValue((int) 0xc0a80001)
		ip.setDestinationAddress(ip4addr)
		ef.embeddedData = ip
		
		verifySendOnMockSender(ef, "sendChangedIp4PacketInDefaultEthernetFrame")
	}
	
	@Test
	public void sendRawDataUnitInDefaultIp4PacketInDefaultEthernetFrame(){
		EthernetFrame ef = new EthernetFrame()
		Ip4Packet ip = new Ip4Packet()
		ip.embeddedData = new RawDataUnit(0x22 as byte, 0x11 as byte, 0x00 as byte)
		ef.embeddedData = ip
		
		verifySendOnMockSender(ef, "sendRawDataUnitInDefaultIp4PacketInDefaultEthernetFrame")
	}
}
