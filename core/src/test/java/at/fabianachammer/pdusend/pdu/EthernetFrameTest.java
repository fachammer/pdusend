package at.fabianachammer.pdusend.pdu;

import org.junit.Assert;
import org.junit.Test;

import at.fabianachammer.pdusend.pdu.EtherType;
import at.fabianachammer.pdusend.pdu.EthernetFrame;
import at.fabianachammer.pdusend.pdu.MacAddress;
import at.fabianachammer.pdusend.pdu.RawDataUnit;
import at.fabianachammer.pdusend.pdu.TagProtocol;
import at.fabianachammer.pdusend.pdu.VlanTag;

/**
 * @author fabian
 * 
 */
public class EthernetFrameTest {

	@Test
	public final void testToBytesWithVlanTagWorks() {
		final EthernetFrame ef = new EthernetFrame();
		final byte[] destinationMac =
				{ 0x00, 0x11, 0x22, 0x33, 0x44, 0x55 };
		final byte[] sourceMac =
				{
						(byte) 0xff, (byte) 0xee, (byte) 0xdd,
						(byte) 0xcc, (byte) 0xbb, (byte) 0xaa };
		final EtherType etherType = EtherType.IPv4;
		final VlanTag vlanTag = new VlanTag();
		vlanTag.setTagProtocol(TagProtocol.IEEE_802_1Q);
		vlanTag.setPriorityCodePoint((byte) 4);
		vlanTag.setCanonicalFormat(true);
		final short vlanId = 4000;
		vlanTag.setVlanIdentifier(vlanId);

		final byte[] data = new byte[] { (byte) 0x92 };
		final RawDataUnit pdu = new RawDataUnit(data);

		ef.setSourceMacAddress(new MacAddress(sourceMac));
		ef.setDestinationMacAddress(new MacAddress(destinationMac));
		ef.setEtherType(etherType);
		ef.setVlanTag(vlanTag);
		ef.setData(pdu);

		final byte[] actual = ef.toBytes();
		final byte[] expected =
				{
						0x00, 0x11, 0x22, 0x33, 0x44, 0x55,
						(byte) 0xff, (byte) 0xee, (byte) 0xdd,
						(byte) 0xcc, (byte) 0xbb, (byte) 0xaa,
						(byte) 0x81, 0x00, (byte) 0x8f, (byte) 0xa0,
						0x08, 0x00, (byte) 0x92, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0x9a,
						(byte) 0xeb, 0x40, (byte) 0xd0 };

		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public final void testToBytesWithoutVlanTagWorks() {
		final EthernetFrame ef = new EthernetFrame();
		final byte[] destinationMac =
				{ 0x00, 0x11, 0x22, 0x33, 0x44, 0x55 };
		final byte[] sourceMac =
				{
						(byte) 0xff, (byte) 0xee, (byte) 0xdd,
						(byte) 0xcc, (byte) 0xbb, (byte) 0xaa };
		final EtherType etherType = EtherType.IPv4;

		ef.setSourceMacAddress(new MacAddress(sourceMac));
		ef.setDestinationMacAddress(new MacAddress(destinationMac));
		ef.setEtherType(etherType);

		final byte[] actual = ef.toBytes();
		final byte[] expected =
				{
						0x00, 0x11, 0x22, 0x33, 0x44, 0x55,
						(byte) 0xff, (byte) 0xee, (byte) 0xdd,
						(byte) 0xcc, (byte) 0xbb, (byte) 0xaa, 0x08,
						0x00, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0x8e, (byte) 0xd3,
						0x59, (byte) 0x40 };

		Assert.assertArrayEquals(expected, actual);
	}
}