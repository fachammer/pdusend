package at.fabianachammer.pdusend.type.pdu;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.fabianachammer.pdusend.type.TagProtocol;

/**
 * This class tests the VlanTag class.
 * 
 * @author fabian
 * 
 */
public class VlanTagTest {

	/**
	 * Test method for {@link at.fabianachammer.pdusend.type.pdu.VlanTag#()}.
	 */
	@Test
	public final void testWorks() {
		final VlanTag tag = new VlanTag();
		tag.setTagProtocol(TagProtocol.IEEE_802_1Q);
		tag.setPriorityCodePoint((byte) 4);
		tag.setCanonicalFormat(true);
		final short vlanId = 4000;
		tag.setVlanIdentifier(vlanId);

		final byte[] expected =
				new byte[] { (byte) 0x81, 0, (byte) 0x8f, (byte) 0xa0 };
		byte[] actual = tag.encode();

		assertArrayEquals(expected, actual);
	}

	/**
	 * Test method for {@link at.fabianachammer.pdusend.type.pdu.VlanTag#()}
	 * when the canonical format indicator is set to false.
	 */
	@Test
	public final void testWithFalseCanonicalFormatWorks() {
		final VlanTag tag = new VlanTag();
		tag.setTagProtocol(TagProtocol.IEEE_802_1Q);
		tag.setPriorityCodePoint((byte) 7);
		tag.setCanonicalFormat(false);
		final short vlanId = 4000;
		tag.setVlanIdentifier(vlanId);

		final byte[] expected =
				{ (byte) 0x81, 0, (byte) 0xff, (byte) 0xa0 };

		assertArrayEquals(expected, tag.encode());
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
