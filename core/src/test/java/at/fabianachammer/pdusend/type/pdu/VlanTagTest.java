package at.fabianachammer.pdusend.type.pdu;

import static org.junit.Assert.*;
import org.junit.Test;

import at.fabianachammer.pdusend.type.TagProtocol;
import at.fabianachammer.pdusend.type.pdu.decoder.VlanTagDecoder;

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
	public final void testEncodeOnVlanTagWorks() {
		final VlanTag tag = new VlanTag();
		tag.setPriorityCodePoint((byte) 1);
		tag.setVlanIdentifier((short) 1);

		final byte[] expected =
				new byte[] { (byte) 0x81, 0, (byte) 0x20, (byte) 0x01 };
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

	@Test
	public final void testEqualsWithEqualVlanTagsOnVlanTagReturnsTrue() {
		VlanTag vlanTag = new VlanTag();
		VlanTag sameVlanTag = new VlanTag();

		assertTrue(vlanTag.equals(sameVlanTag));
	}

	@Test
	public final void testEqualsWithDifferentVlanTagsOnVlanTagReturnsFalse() {
		VlanTag vlanTag = new VlanTag();
		VlanTag differentVlanTag = new VlanTag();
		differentVlanTag.setPriorityCodePoint((byte) 1);

		assertFalse(vlanTag.equals(differentVlanTag));
	}

	@Test
	public final void testEqualsWithDifferentTypesOnVlanTagReturnsFalse() {
		assertFalse(new VlanTag().equals(new Object()));
	}

	@Test
	public final void testEqualsWithNullOnVlanTagReturnsFalse() {
		assertFalse(new VlanTag().equals(null));
	}

	@Test
	public final void testHashCodeWithEqualVlanTagsReturnsEqualHashCodes() {
		assertEquals(new VlanTag().hashCode(),
				new VlanTag().hashCode());
	}

	@Test
	public final void testHashCodeWithDifferentVlanTagsReturnsDifferentHashCodes() {
		VlanTag vlanTag = new VlanTag();
		VlanTag differentVlanTag = new VlanTag();
		differentVlanTag.setPriorityCodePoint((byte) 1);

		assertNotEquals(vlanTag, differentVlanTag);
	}

	@Test
	public final void testGetDecoderReturnsInstanceOfVlanTagDecoder() {
		VlanTag vlanTag = new VlanTag();

		assertTrue(vlanTag.getDecoder() instanceof VlanTagDecoder);
	}

	@Test
	public final void testSizeReturnsFour() {
		assertEquals(4, new VlanTag().size());
	}
}
