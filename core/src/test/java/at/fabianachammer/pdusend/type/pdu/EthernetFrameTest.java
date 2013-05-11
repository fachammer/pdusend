package at.fabianachammer.pdusend.type.pdu;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.MacAddress;
import at.fabianachammer.pdusend.type.TagProtocol;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.EthernetFrameDecoder;

/**
 * @author fabian
 * 
 */
public class EthernetFrameTest {

	@Test
	public final void encodeWithVlanTagReturnsProperByteRepresentation() {
		final EthernetFrame ef = new EthernetFrame();
		final byte[] destinationMac =
				{ 0x00, 0x11, 0x22, 0x33, 0x44, 0x55 };
		final byte[] sourceMac =
				{
						(byte) 0xff, (byte) 0xee, (byte) 0xdd,
						(byte) 0xcc, (byte) 0xbb, (byte) 0xaa };
		final EtherType etherType = EtherType.ARP;
		final VlanTag vlanTag = new VlanTag();
		vlanTag.setTagProtocol(TagProtocol.IEEE_802_1Q);
		vlanTag.setPriorityCodePoint((byte) 1);
		vlanTag.setCanonicalFormat(true);
		final short vlanId = 1;
		vlanTag.setVlanIdentifier(vlanId);

		final byte[] data = new byte[] { (byte) 0x01 };
		final RawDataUnit pdu = new RawDataUnit(data);

		ef.setSourceMacAddress(new MacAddress(sourceMac));
		ef.setDestinationMacAddress(new MacAddress(destinationMac));
		ef.setEtherType(etherType);
		ef.setVlanTag(vlanTag);
		ef.setEmbeddedData(pdu);

		final byte[] actual = ef.encode();
		final byte[] expected =
				{
						0x00, 0x11, 0x22, 0x33, 0x44, 0x55,
						(byte) 0xff, (byte) 0xee, (byte) 0xdd,
						(byte) 0xcc, (byte) 0xbb, (byte) 0xaa,
						(byte) 0x81, 0x00, (byte) 0x20, (byte) 1,
						0x08, 0x06, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, (byte) 0xe0, (byte) 0x46, (byte) 0x5e,
						(byte) 0xaa };

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void encodeWithoutVlanTagReturnsProperByteRepresentation() {
		final EthernetFrame ef = new EthernetFrame();
		final byte[] destinationMac =
				{ 0x00, 0x11, 0x22, 0x33, 0x44, 0x55 };
		final byte[] sourceMac =
				{
						(byte) 0xff, (byte) 0xee, (byte) 0xdd,
						(byte) 0xcc, (byte) 0xbb, (byte) 0xaa };
		final EtherType etherType = EtherType.ARP;

		ef.setSourceMacAddress(new MacAddress(sourceMac));
		ef.setDestinationMacAddress(new MacAddress(destinationMac));
		ef.setEtherType(etherType);

		final byte[] actual = ef.encode();
		final byte[] expected =
				{
						0x00, 0x11, 0x22, 0x33, 0x44, 0x55,
						(byte) 0xff, (byte) 0xee, (byte) 0xdd,
						(byte) 0xcc, (byte) 0xbb, (byte) 0xaa, 0x08,
						0x06, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, (byte) 0x91, (byte) 0xee,
						(byte) 0x3e, (byte) 0xec };

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void encodeWithEmbeddedArpPacketSetsEtherTypeOnEthernetFrame() {
		EthernetFrame ef = new EthernetFrame();
		ef.setEmbeddedData(new ArpPacket());
		ef.setChecksum(new byte[4]);
		byte[] expected =
				{
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0x08,
						0x06, 0, 0, 0, 0, 6, 4, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0 };

		byte[] actual = ef.encode();

		assertEquals(EtherType.ARP, ef.getEtherType());
	}

	@Test(expected = NullPointerException.class)
	public final void setNullDestinationMacAddressThrowsNullPointerException() {
		EthernetFrame ef = new EthernetFrame();

		ef.setDestinationMacAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void setNullSourceMacAddressThrowsNullPointerException() {
		EthernetFrame ef = new EthernetFrame();

		ef.setSourceMacAddress(null);
	}

	@Test
	public final void equalsWithEqualDefaultEthernetFramesReturnsTrue() {
		assertTrue(new EthernetFrame().equals(new EthernetFrame()));
	}

	@Test
	public final void equalsWithDifferentEthernetFramesReturnsFalse() {
		EthernetFrame ethernetFrame = new EthernetFrame();
		EthernetFrame differentEthernetFrame = new EthernetFrame();
		differentEthernetFrame.setChecksum(new byte[4]);

		assertFalse(ethernetFrame.equals(differentEthernetFrame));
	}

	@Test
	public final void equalsWithNullReturnsFalse() {
		assertFalse(new EthernetFrame().equals(null));
	}

	@Test
	public final void equalsWithDifferentTypesReturnsFalse() {
		assertFalse(new EthernetFrame().equals(new Object()));
	}

	@Test
	public final void hashCodeWithEqualDefaultEthernetFramesReturnsEqualHashCodes() {
		assertEquals(new EthernetFrame().hashCode(),
				new EthernetFrame().hashCode());
	}

	@Test
	public final void hashCodeWithDifferentEthernetFramesReturnsDifferentHashCodes() {
		EthernetFrame ethernetFrame = new EthernetFrame();
		EthernetFrame differentEthernetFrame = new EthernetFrame();
		differentEthernetFrame.setChecksum(new byte[4]);

		assertNotEquals(ethernetFrame, differentEthernetFrame);
	}

	@Test
	public final void sizeOfDefaultEthernetFrameReturns64() {
		assertEquals(64, new EthernetFrame().size());
	}

	@Test
	public final void sizeWithDefaultArgumentsAndVlanTagReturns64() {
		EthernetFrame ef = new EthernetFrame();
		ef.setVlanTag(new VlanTag());

		assertEquals(64, ef.size());
	}

	@Test
	public final void getDecoderReturnsInstanceOfEthernetFrameDecoder() {
		assertTrue(new EthernetFrame().getDecoder() instanceof EthernetFrameDecoder);
	}

	@Test
	public final void setEmbeddedDataWithNetworkProtocolInstanceSetsEtherType() {
		NetworkProtocolDataUnitFake fake =
				new NetworkProtocolDataUnitFake();
		EthernetFrame ef = new EthernetFrame();
		EtherType expected = fake.getEtherType();

		ef.setEmbeddedData(fake);

		assertEquals(expected, ef.getEtherType());
	}

	/**
	 * fake for having a class that implements both data unit and network
	 * protocol.
	 * 
	 * @author fabian
	 * 
	 */
	class NetworkProtocolDataUnitFake extends DataUnit implements
			NetworkProtocol {

		@Override
		public EtherType getEtherType() {
			return new EtherType((short) 1);
		}

		@Override
		public DataUnitDecoder<? extends DataUnit> getDecoder() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public byte[] encode() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

	}
}
