/**
 * 
 */
package at.fabianachammer.nsend.aspect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import at.fabianachammer.nsend.pdu.EtherType;
import at.fabianachammer.nsend.pdu.EthernetFrame;
import at.fabianachammer.nsend.pdu.MacAddress;
import at.fabianachammer.nsend.pdu.VlanTag;

/**
 * This class tests the PreConditionValidation class.
 * 
 * @author fabian
 * 
 */
public class PreConditionValidationTest {

	@Test
	public final void testSetValidDestinationMacAddressOnEthernetFrameWorks() {
		final int arraySize = 6;
		byte[] mac = new byte[arraySize];
		EthernetFrame eh = new EthernetFrame();

		eh.setDestinationMacAddress(new MacAddress(mac));
	}

	@Test
	public final void testSetValidSourceMacAddressOnEthernetFrameWorks() {
		final int arraySize = 6;
		byte[] mac = new byte[arraySize];
		EthernetFrame eh = new EthernetFrame();

		eh.setSourceMacAddress(new MacAddress(mac));
	}

	@Test
	public final void testSetValidEtherTypeOnEthernetFrameWorks() {
		EthernetFrame eh = new EthernetFrame();

		eh.setEtherType(EtherType.ARP);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalDestinationMacAddressOnEthernetFrameThrowsIllegalArgumentException() {
		final int illegalArraySize = 7;
		byte[] illegalMac = new byte[illegalArraySize];
		EthernetFrame eh = new EthernetFrame();

		eh.setDestinationMacAddress(new MacAddress(illegalMac));
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalSourceMacAddressOnEthernetFrameThrowsIllegalArgumentException() {
		final int illegalArraySize = 7;
		byte[] illegalMac = new byte[illegalArraySize];
		EthernetFrame eh = new EthernetFrame();

		eh.setSourceMacAddress(new MacAddress(illegalMac));
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullDestinationMacAddressOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame eh = new EthernetFrame();

		eh.setDestinationMacAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullSourceMacAddressOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame eh = new EthernetFrame();

		eh.setSourceMacAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullEtherTypeOnEthernetFrameThrowsNullPointerException() {
		EthernetFrame eh = new EthernetFrame();

		eh.setEtherType(null);
	}

	@Test
	public final void testSetValidPriorityCodePointOnVlanTagWorks() {
		final byte validPriorityCodePoint = 0;
		VlanTag tag = new VlanTag();

		tag.setPriorityCodePoint(validPriorityCodePoint);

		assertEquals(0, tag.getPriorityCodePoint());
	}

	@Test
	public final void testSetValidVlanIdentifierOnVlanTagWorks() {
		final short validVlanIdentifier = 0;
		VlanTag tag = new VlanTag();

		tag.setVlanIdentifier(validVlanIdentifier);

		assertEquals(0, tag.getVlanIdentifier());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalPriorityCodePointOnVlanTagThrowsIllegalArgumentException() {
		final byte illegalPriorityCodePoint = 8;
		final VlanTag tag = new VlanTag();

		tag.setPriorityCodePoint(illegalPriorityCodePoint);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalVlanIdentifierThrowsIllegalArgumentException() {
		final short illegalVlanIdentifier = 4097;
		final VlanTag tag = new VlanTag();

		tag.setVlanIdentifier(illegalVlanIdentifier);
	}
}
