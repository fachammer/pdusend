/**
 * 
 */
package at.fabianachammer.nsend.pdu;

import static org.junit.Assert.*;

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
	public final void testToBytes() {
		final VlanTag tag = new VlanTag();
		tag.setTagProtocol(TagProtocol.IEEE_802_1Q);
		tag.setPriorityCodePoint((byte) 4);
		tag.setCanonicalFormat(true);
		tag.setVlanIdentifier((short) 4000);

		final byte[] expected =
				new byte[] { (byte) 0x81, 0, (byte) 0b10001111,
						(byte) 0b10100000 };
		byte[] actual = tag.toBytes();

		assertArrayEquals(expected, actual);
	}
}
