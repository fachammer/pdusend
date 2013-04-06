/**
 * 
 */
package at.fabianachammer.nsend.aspect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import at.fabianachammer.nsend.pdu.EtherType;
import at.fabianachammer.nsend.pdu.EthernetFrame;
import at.fabianachammer.nsend.pdu.RawDataUnit;
import at.fabianachammer.nsend.pdu.VlanTag;

/**
 * This class tests the PreConditionValidation class.
 * 
 * @author fabian
 * 
 */
public class PreConditionValidationTest {

	/**
	 * Tests, whether a valid destination MAC address can be set on an Ethernet
	 * frame.
	 */
	@Test
	public final void testSetValidDestinationMacAddressOnEthernetFrameWorks() {
		final int arraySize = 6;
		Byte[] mac = new Byte[arraySize];
		EthernetFrame ef = new EthernetFrame();

		ef.setDestinationMacAddress(mac);

		assertArrayEquals(mac, ef.getDestinationMacAddress());
	}

	/**
	 * Tests, whether a valid source MAC address can be set on an Ethernet
	 * frame.
	 */
	@Test
	public final void testSetValidSourceMacAddressOnEthernetFrameWorks() {
		final int arraySize = 6;
		Byte[] mac = new Byte[arraySize];
		EthernetFrame ef = new EthernetFrame();

		ef.setSourceMacAddress(mac);

		assertArrayEquals(mac, ef.getSourceMacAddress());
	}

	/**
	 * Tests, whether a valid PDU can be set as data for an Ethernet frame.
	 */
	@Test
	public final void testSetValidPduOnEthernetFrameWorks() {
		final RawDataUnit rdu = new RawDataUnit(new Byte[] { 0 });
		EthernetFrame ef = new EthernetFrame();

		ef.setData(rdu);

		assertEquals(rdu, ef.getData());
	}

	/**
	 * Tests, whether a null PDU can be set as data for an Ethernet frame.
	 */
	@Test
	public final void testSetNullPduOnEthernetFrameWorks() {
		EthernetFrame ef = new EthernetFrame();

		ef.setData(null);

		assertEquals(null, ef.getData());
	}

	/**
	 * Tests, whether a valid EtherType can be set on an Ethernet frame.
	 */
	@Test
	public final void testSetValidEtherTypeOnEthernetFrameWorks() {
		EthernetFrame ef = new EthernetFrame();

		ef.setEtherType(EtherType.ARP);

		assertEquals(EtherType.ARP, ef.getEtherType());
	}

	/**
	 * Tests, whether the setting of an illegal destination MAC address on an
	 * Ethernet frame throws an IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalDestinationMacAddressOnEthernetFrameThrowsIllegalArgumentException() {
		final int illegalArraySize = 7;
		Byte[] illegalMac = new Byte[illegalArraySize];
		EthernetFrame ef = new EthernetFrame();

		ef.setDestinationMacAddress(illegalMac);
	}

	/**
	 * Tests, whether the setting of an illegal source MAC address on an
	 * Ethernet frame throws an IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalSourceMacAddressOnEthernetFrameThrowsIllegalArgumentException() {
		final int illegalArraySize = 7;
		Byte[] illegalMac = new Byte[illegalArraySize];
		EthernetFrame ef = new EthernetFrame();

		ef.setSourceMacAddress(illegalMac);
	}

	/**
	 * Tests, whether the setting of an illegal PDU on an Ethernet frame throws
	 * an IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalPduOnEthernetFrameThrowsIllegalArgumentException() {
		EthernetFrame ef = new EthernetFrame();
		final int illegalArraySize = 1505;

		ef.setData(new RawDataUnit(new Byte[illegalArraySize]));
	}

	/**
	 * Tests, whether the setting of a null destination MAC address on an
	 * Ethernet frame throws a NullPointerException.
	 */
	@Test(expected = NullPointerException.class)
	public final void testSetNullDestinationMacAddressOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame ef = new EthernetFrame();

		ef.setDestinationMacAddress(null);
	}

	/**
	 * Tests, whether the setting of a null source MAC address on an Ethernet
	 * frame throws a NullPointerException.
	 */
	@Test(expected = NullPointerException.class)
	public final void testSetNullSourceMacAddressOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame ef = new EthernetFrame();

		ef.setSourceMacAddress(null);
	}

	/**
	 * Tests, whether the setting of a null EtherType on an Ethernet frame
	 * throws a NullPointerException.
	 */
	@Test(expected = NullPointerException.class)
	public final void testSetNullEtherTypeOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame ef = new EthernetFrame();

		ef.setEtherType(null);
	}

	/**
	 * Tests, whether a valid priority code point can be set on a vlan tag.
	 */
	@Test
	public final void testSetValidPriorityCodePointOnVlanTagWorks() {
		VlanTag tag = new VlanTag();
		final byte validPriorityCodePoint = 0;

		tag.setPriorityCodePoint(validPriorityCodePoint);

		assertEquals(0, tag.getPriorityCodePoint());
	}

	/**
	 * Tests, whether a valid vlan identifier can be set on a vlan tag.
	 */
	@Test
	public final void testSetValidVlanIdentifierOnVlanTagWorks() {
		VlanTag tag = new VlanTag();
		final short validVlanIdentifier = 0;

		tag.setVlanIdentifier(validVlanIdentifier);

		assertEquals(0, tag.getVlanIdentifier());
	}

	/**
	 * Tests, whether the setting of an illegal priority code point on a vlan
	 * tag throws an IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalPriorityCodePointOnVlanTagThrowsIllegalArgumentException() {
		final VlanTag tag = new VlanTag();
		final byte illegalPriorityCodePoint = 8;

		tag.setPriorityCodePoint(illegalPriorityCodePoint);
	}

	/**
	 * Tests, whether the setting of an illegal vlan identifier on a vlan tag
	 * throws an IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalVlanIdentifierThrowsIllegalArgumentException() {
		final VlanTag tag = new VlanTag();
		final short illegalVlanIdentifier = 4097;

		tag.setVlanIdentifier(illegalVlanIdentifier);
	}
}
