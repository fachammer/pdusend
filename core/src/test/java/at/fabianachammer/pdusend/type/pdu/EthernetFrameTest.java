package at.fabianachammer.pdusend.type.pdu;

import static org.junit.Assert.*;
import org.junit.Test;

import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.MacAddress;
import at.fabianachammer.pdusend.type.TagProtocol;
import at.fabianachammer.pdusend.type.pdu.decoder.EthernetFrameDecoder;

/**
 * @author fabian
 * 
 */
public class EthernetFrameTest {

	@Test
	public final void testEncodeOnEthernetFrameWithVlanTagWorks() {
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
						0, (byte) 0xaa, (byte) 0x5e, (byte) 0x46,
						(byte) 0xe0 };

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void testEncodeOnEthernetFrameWithoutVlanTagWorks() {
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
						0, 0, 0, (byte) 0xec, (byte) 0x3e,
						(byte) 0xee, (byte) 0x91 };

		assertArrayEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullDestinationMacAddressOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame ef = new EthernetFrame();

		ef.setDestinationMacAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullSourceMacAddressOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame ef = new EthernetFrame();

		ef.setSourceMacAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullEtherTypeOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame ef = new EthernetFrame();

		ef.setEtherType(null);
	}

	@Test
	public final void testEqualsWithEqualEthernetFramesOnEthernetFrameReturnsTrue() {
		assertTrue(new EthernetFrame().equals(new EthernetFrame()));
	}

	@Test
	public final void testEqualsWithDifferentEthernetFramesOnEthernetFrameReturnsFalse() {
		EthernetFrame ethernetFrame = new EthernetFrame();
		EthernetFrame differentEthernetFrame = new EthernetFrame();
		differentEthernetFrame.setEmbeddedData(new RawDataUnit());

		assertFalse(ethernetFrame.equals(differentEthernetFrame));
	}

	@Test
	public final void testEqualsWithNullOnEthernetFrameReturnsFalse() {
		assertFalse(new EthernetFrame().equals(null));
	}

	@Test
	public final void testEqualsWithDifferentTypesOnEthernetFrameReturnsFalse() {
		assertFalse(new EthernetFrame().equals(new Object()));
	}

	@Test
	public final void testHashCodeWithEqualEthernetFramesOnEthernetFrameReturnsEqualHashCodes() {
		assertEquals(new EthernetFrame().hashCode(),
				new EthernetFrame().hashCode());
	}

	@Test
	public final void testHashCodeWithDifferentEthernetFramesOnEthernetFrameReturnsDifferentHashCodes() {
		EthernetFrame ethernetFrame = new EthernetFrame();
		EthernetFrame differentEthernetFrame = new EthernetFrame();
		differentEthernetFrame.setEmbeddedData(new RawDataUnit());

		assertNotEquals(ethernetFrame, differentEthernetFrame);
	}

	@Test
	public final void sizeWithDefaultArgumentsReturns18() {
		assertEquals(18, new EthernetFrame().size());
	}

	@Test
	public final void sizeWithDefaultArgumentsAndVlanTagReturns22() {
		EthernetFrame ef = new EthernetFrame();
		ef.setVlanTag(new VlanTag());

		assertEquals(22, ef.size());
	}

	@Test
	public final void getDecoderReturnsInstanceOfEthernetFrameDecoder() {
		assertTrue(new EthernetFrame().getDecoder() instanceof EthernetFrameDecoder);
	}

	@Test
	public final void getEmbeddedDataCallsGetData() {
		EthernetFrame ethernetFrame = new EthernetFrame();
		ethernetFrame.setEmbeddedData(new RawDataUnit());

		assertEquals(ethernetFrame.getEmbeddedData(),
				ethernetFrame.getEmbeddedData());
	}
}