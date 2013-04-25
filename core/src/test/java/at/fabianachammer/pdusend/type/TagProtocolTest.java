package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.TagProtocolDecoder;

/**
 * @author fabian
 * 
 */
public class TagProtocolTest {

	@Test
	public final void testGetDecoderOnTagProtocolReturnsInstanceOfTagProtocolDecoder() {
		TagProtocol anyTagProtocol = TagProtocol.IEEE_802_1Q;

		DataUnitDecoder<TagProtocol> decoder =
				anyTagProtocol.getDecoder();

		assertTrue(decoder instanceof TagProtocolDecoder);
	}

	@Test
	public final void testEncodeOnTagProtocolWorks() {
		TagProtocol anyTagProtocol = TagProtocol.IEEE_802_1Q;
		byte[] expected = { (byte) 0x81, 0x00 };

		byte[] actual = anyTagProtocol.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void testEqualsWithEqualIdTagProtocolsOnTagProtocolReturnsTrue() {
		TagProtocol tp = new TagProtocol((short) 0);
		TagProtocol equalTp = new TagProtocol((short) 0);

		assertTrue(tp.equals(equalTp));
	}

	@Test
	public final void testEqualsWithDiffertenIdTagProtocolsOnTagProtocolReturnsFalse() {
		TagProtocol tp = new TagProtocol((short) 0);
		TagProtocol differentTp = new TagProtocol((short) 1);

		assertFalse(tp.equals(differentTp));
	}

	@Test
	public final void testEqualsWithDifferentTypesOnTagProtocolReturnsFalse() {
		assertFalse(new TagProtocol((short) 0).equals(new Object()));
	}

	@Test
	public final void testEqualsWithNullOnTagProtocolReturnsFalse() {
		assertFalse(new TagProtocol((short) 0).equals(null));
	}

	@Test
	public final void testHashCodeWithEqualIdTagProtocolsReturnsEqualHashCodes() {
		assertEquals(new TagProtocol((short) 0).hashCode(),
				new TagProtocol((short) 0).hashCode());
	}

	@Test
	public final void testHashCodeWithDifferentIdTagProtocolsReturnsDifferentHashCodes() {
		assertNotEquals(new TagProtocol((short) 0).hashCode(),
				new TagProtocol((short) 1).hashCode());
	}

	@Test
	public final void sizeOfUnknownTagProtocolReturnsTwo() {
		assertEquals(2, TagProtocol.UNKNOWN.size());
	}
}
