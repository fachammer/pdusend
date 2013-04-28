package at.fabianachammer.pdusend.dsl

import static org.junit.Assert.*
import static org.mockito.Mockito.*

import java.awt.TexturePaintContext.Any;

import org.junit.Test
import at.fabianachammer.pdusend.Sender
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.pdu.RawDataUnit


/**
 * @author fabian
 *
 */
class VocabularyTest {
	
	def loopback = NetworkInterface.getByName("lo")
	def loopbackIndex = loopback.index
	def loopbackName = loopback.name

	@Test
	void onWithNetworkInterfaceAndDataUnitSetCallsSendOnSender() {
		def senderMock = mock(Sender.class)
		def dataUnitStub = mock(DataUnit.class)
		def vocabulary = new Vocabulary()
		vocabulary.sender = senderMock
		vocabulary.dataUnitToSend = dataUnitStub

		vocabulary.on(loopback)

		verify(senderMock).send(loopback, dataUnitStub)
	}

	@Test
	void onWithInterfaceNumberAndDataUnitSetCallsSendOnSender(){
		def senderMock = mock(Sender.class)
		def dataUnitStub = mock(DataUnit.class)
		def vocabulary = new Vocabulary()
		vocabulary.sender = senderMock
		vocabulary.dataUnitToSend = dataUnitStub

		vocabulary.on(loopbackIndex)

		verify(senderMock).send(loopback, dataUnitStub)
	}
	
	@Test
	void onWithNameAndDataUnitSetCallsSendOnSender(){
		def senderMock = mock(Sender.class)
		def dataUnitStub = mock(DataUnit.class)
		def vocabulary = new Vocabulary()
		vocabulary.sender = senderMock
		vocabulary.dataUnitToSend = dataUnitStub

		vocabulary.on(loopbackName)

		verify(senderMock).send(loopback, dataUnitStub)
	}

	@Test(expected = NullPointerException.class)
	void onWithNullDataUnitThrowsNullPointerException(){
		def vocabulary = new Vocabulary()
		def loopback = NetworkInterface.getByName("lo")

		vocabulary.on(loopback);
	}
	
	@Test
	void sendWithByteArraySetsRawDataUnitToSend(){
		def vocabulary = new Vocabulary()
		byte[] arrayToSet = [0]
		def expectedDataUnitToSend = new RawDataUnit(arrayToSet)
		
		vocabulary.send(arrayToSet)
		
		assertEquals(expectedDataUnitToSend, vocabulary.dataUnitToSend)
	}
	
	@Test
	void sendWithOneByteBigIntegerAllBitsSetSetsRawDataUnitWithOneByteAllBitsSet(){
		def vocabulary = new Vocabulary()
		def bigIntegerToSend = 255 as BigInteger
		def expectedDataUnitToSend = new RawDataUnit([0xff] as byte[])
		
		vocabulary.send(bigIntegerToSend)
		
		assertEquals(expectedDataUnitToSend, vocabulary.dataUnitToSend)
	}
	
	@Test(expected = IllegalArgumentException.class)
	void sendWithNegativeBigIntegerThrowsIllegalArgumentException(){
		new Vocabulary().send(-1)
	}
	
	@Test
	void sendWithDataUnitSetsDataUnitToSend(){
		def vocabulary = new Vocabulary()
		def dataUnitDummy = mock(DataUnit.class)
		
		vocabulary.send(dataUnitDummy)
		
		assertEquals(dataUnitDummy, vocabulary.dataUnitToSend)
	}
}
