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
		EthernetFrame eh = new EthernetFrame();

		eh.setDestinationMacAddress(mac);

		assertArrayEquals(mac, eh.getDestinationMacAddress());
	}

	/**
	 * Tests, whether a valid source MAC address can be set on an Ethernet
	 * frame.
	 */
	@Test
	public final void testSetValidSourceMacAddressOnEthernetFrameWorks() {
		final int arraySize = 6;
		Byte[] mac = new Byte[arraySize];
		EthernetFrame eh = new EthernetFrame();

		eh.setSourceMacAddress(mac);

		assertArrayEquals(mac, eh.getSourceMacAddress());
	}

	/**
	 * Tests, whether a valid EtherType can be set on an Ethernet frame.
	 */
	@Test
	public final void testSetValidEtherTypeOnEthernetFrameWorks() {
		EthernetFrame eh = new EthernetFrame();

		eh.setEtherType(EtherType.ARP);

		assertEquals(EtherType.ARP, eh.getEtherType());
	}

	/**
	 * Tests, whether the setting of an illegal destination MAC address on an
	 * Ethernet frame throws an IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalDestinationMacAddressOnEthernetFrameThrowsIllegalArgumentException() {
		final int illegalArraySize = 7;
		Byte[] illegalMac = new Byte[illegalArraySize];
		EthernetFrame eh = new EthernetFrame();

		eh.setDestinationMacAddress(illegalMac);
	}

	/**
	 * Tests, whether the setting of an illegal source MAC address on an
	 * Ethernet frame throws an IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalSourceMacAddressOnEthernetFrameThrowsIllegalArgumentException() {
		final int illegalArraySize = 7;
		Byte[] illegalMac = new Byte[illegalArraySize];
		EthernetFrame eh = new EthernetFrame();

		eh.setSourceMacAddress(illegalMac);
	}

	/**
	 * Tests, whether the setting of a null destination MAC address on an
	 * Ethernet frame throws a NullPointerException.
	 */
	@Test(expected = NullPointerException.class)
	public final void testSetNullDestinationMacAddressOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame eh = new EthernetFrame();

		eh.setDestinationMacAddress(null);
	}

	/**
	 * Tests, whether the setting of a null source MAC address on an Ethernet
	 * frame throws a NullPointerException.
	 */
	@Test(expected = NullPointerException.class)
	public final void testSetNullSourceMacAddressOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame eh = new EthernetFrame();

		eh.setSourceMacAddress(null);
	}

	/**
	 * Tests, whether the setting of a null EtherType on an Ethernet frame
	 * throws a NullPointerException.
	 */
	@Test(expected = NullPointerException.class)
	public final void testSetNullEtherTypeOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame eh = new EthernetFrame();

		eh.setEtherType(null);
	}

	/**
	 * Tests, whether a valid priority code point can be set on a vlan tag.
	 */
	@Test
	public final void testSetValidPriorityCodePointOnVlanTagWorks() {
		final byte validPriorityCodePoint = 0;
		VlanTag tag = new VlanTag();

		tag.setPriorityCodePoint(validPriorityCodePoint);

		assertEquals(0, tag.getPriorityCodePoint());
	}

	/**
	 * Tests, whether a valid vlan identifier can be set on a vlan tag.
	 */
	@Test
	public final void testSetValidVlanIdentifierOnVlanTagWorks() {
		final short validVlanIdentifier = 0;
		VlanTag tag = new VlanTag();

		tag.setVlanIdentifier(validVlanIdentifier);

		assertEquals(0, tag.getVlanIdentifier());
	}

	/**
	 * Tests, whether the setting of an illegal priority code point on a vlan
	 * tag throws an IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalPriorityCodePointOnVlanTagThrowsIllegalArgumentException() {
		final byte illegalPriorityCodePoint = 8;
		final VlanTag tag = new VlanTag();

		tag.setPriorityCodePoint(illegalPriorityCodePoint);
	}

	/**
	 * Tests, whether the setting of an illegal vlan identifier on a vlan tag
	 * throws an IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalVlanIdentifierThrowsIllegalArgumentException() {
		final short illegalVlanIdentifier = 4097;
		final VlanTag tag = new VlanTag();

		tag.setVlanIdentifier(illegalVlanIdentifier);
	}
}
