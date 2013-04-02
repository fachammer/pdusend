/**
 * 
 */
package at.fabianachammer.nsend.pdu;

import org.junit.Assert;
import org.junit.Test;

import at.fabianachammer.nsend.pdu.util.BytePrimitiveConverter;

/**
 * @author fabian
 * 
 */
public class EthernetFrameTest {

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.pdu.EthernetFrame#toBytes()}.
	 */
	@Test
	public final void testToBytes() {
		final EthernetFrame ef = new EthernetFrame();
		final Byte[] destinationMac = { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55 };
		final Byte[] sourceMac =
				{ (byte) 0xff, (byte) 0xee, (byte) 0xdd, (byte) 0xcc,
						(byte) 0xbb, (byte) 0xaa };
		final EtherType etherType = EtherType.IPv4;
		final VlanTag vlanTag = new VlanTag();
		vlanTag.setTagProtocol(TagProtocol.IEEE_802_1Q);
		vlanTag.setPriorityCodePoint((byte) 4);
		vlanTag.setCanonicalFormat(true);
		final short vlanId = 4000;
		vlanTag.setVlanIdentifier(vlanId);

		final Byte[] data = new Byte[] { (byte) 0b10010010 };
		final RawDataUnit pdu = new RawDataUnit(data);

		ef.setSourceMacAddress(sourceMac);
		ef.setDestinationMacAddress(destinationMac);
		ef.setEtherType(etherType);
		ef.setVlanTag(vlanTag);
		ef.setPdu(pdu);

		final byte[] actual =
				BytePrimitiveConverter.convertByteArray(ef.toBytes());
		final byte[] expected =
				{ 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, (byte) 0xff, (byte) 0xee,
						(byte) 0xdd, (byte) 0xcc, (byte) 0xbb, (byte) 0xaa,
						(byte) 0x81, 0x00, (byte) 0b10001111,
						(byte) 0b10100000, 0x08, 0x00, (byte) 0b10010010,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0xd0, 0x40, (byte) 0xeb, (byte) 0x9a };

		Assert.assertArrayEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.pdu.EthernetFrame#toBytes()}.
	 */
	@Test
	public final void testToBytesWithoutVlanTag() {
		final EthernetFrame ef = new EthernetFrame();
		final Byte[] destinationMac = { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55 };
		final Byte[] sourceMac =
				{ (byte) 0xff, (byte) 0xee, (byte) 0xdd, (byte) 0xcc,
						(byte) 0xbb, (byte) 0xaa };
		final EtherType etherType = EtherType.IPv4;
		final Byte[] data = new Byte[] { (byte) 0b10010010 };
		final RawDataUnit pdu = new RawDataUnit(data);

		ef.setSourceMacAddress(sourceMac);
		ef.setDestinationMacAddress(destinationMac);
		ef.setEtherType(etherType);
		ef.setPdu(pdu);

		final byte[] actual =
				BytePrimitiveConverter.convertByteArray(ef.toBytes());
		final byte[] expected =
				{ 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, (byte) 0xff, (byte) 0xee,
						(byte) 0xdd, (byte) 0xcc, (byte) 0xbb, (byte) 0xaa,
						0x08, 0x00, (byte) 0b10010010, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0x7f, 0x5c,
						(byte) 0x50, (byte) 0xba };

		Assert.assertArrayEquals(expected, actual);
	}
}
