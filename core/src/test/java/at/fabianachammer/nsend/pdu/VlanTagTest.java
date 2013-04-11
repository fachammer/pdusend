package at.fabianachammer.nsend.pdu;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

/**
 * This class tests the VlanTag class.
 * 
 * @author fabian
 * 
 */
public class VlanTagTest {

	/**
	 * Test method for {@link at.fabianachammer.nsend.pdu.VlanTag#toBytes()}.
	 */
	@Test
	public final void testToBytesWorks() {
		final VlanTag tag = new VlanTag();
		tag.setTagProtocol(TagProtocol.IEEE_802_1Q);
		tag.setPriorityCodePoint((byte) 4);
		tag.setCanonicalFormat(true);
		final short vlanId = 4000;
		tag.setVlanIdentifier(vlanId);

		final Byte[] expected =
				new Byte[] { (byte) 0x81, 0, (byte) 0x8f, (byte) 0xa0 };
		Byte[] actual = tag.toBytes();

		assertArrayEquals(expected, actual);
	}

	/**
	 * Test method for {@link at.fabianachammer.nsend.pdu.VlanTag#toBytes()}
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

		final Byte[] expected = { (byte) 0x81, 0, (byte) 0xff, (byte) 0xa0 };

		assertArrayEquals(expected, tag.toBytes());
	}
}