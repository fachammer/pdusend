package at.fabianachammer.pdusend.pdu;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

import at.fabianachammer.pdusend.pdu.TagProtocol;
import at.fabianachammer.pdusend.pdu.VlanTag;

/**
 * This class tests the VlanTag class.
 * 
 * @author fabian
 * 
 */
public class VlanTagTest {

	/**
	 * Test method for {@link at.fabianachammer.pdusend.pdu.VlanTag#toBytes()}.
	 */
	@Test
	public final void testToBytesWorks() {
		final VlanTag tag = new VlanTag();
		tag.setTagProtocol(TagProtocol.IEEE_802_1Q);
		tag.setPriorityCodePoint((byte) 4);
		tag.setCanonicalFormat(true);
		final short vlanId = 4000;
		tag.setVlanIdentifier(vlanId);

		final byte[] expected =
				new byte[] { (byte) 0x81, 0, (byte) 0x8f, (byte) 0xa0 };
		byte[] actual = tag.toBytes();

		assertArrayEquals(expected, actual);
	}

	/**
	 * Test method for {@link at.fabianachammer.pdusend.pdu.VlanTag#toBytes()}
	 * when the canonical format indicator is set to false.
	 */
	@Test
	public final void testToBytesWithFalseCanonicalFormatWorks() {
		final VlanTag tag = new VlanTag();
		tag.setTagProtocol(TagProtocol.IEEE_802_1Q);
		tag.setPriorityCodePoint((byte) 7);
		tag.setCanonicalFormat(false);
		final short vlanId = 4000;
		tag.setVlanIdentifier(vlanId);

		final byte[] expected = { (byte) 0x81, 0, (byte) 0xff, (byte) 0xa0 };

		assertArrayEquals(expected, tag.toBytes());
	}
}
