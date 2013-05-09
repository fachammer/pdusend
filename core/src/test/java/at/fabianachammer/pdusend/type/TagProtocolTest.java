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
	public final void getDecoderReturnsInstanceOfTagProtocolDecoder() {
		TagProtocol anyTagProtocol = TagProtocol.IEEE_802_1Q;

		DataUnitDecoder<TagProtocol> decoder =
				anyTagProtocol.getDecoder();

		assertTrue(decoder instanceof TagProtocolDecoder);
	}

	@Test
	public final void encodeWithUnknownTagProtocolReturnsAllZeroByteArray() {
		TagProtocol anyTagProtocol = TagProtocol.UNKNOWN;
		byte[] expected = { 0, 0 };

		byte[] actual = anyTagProtocol.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void equalsWithEqualIdTagProtocolsReturnsTrue() {
		TagProtocol tp = new TagProtocol((short) 0);
		TagProtocol equalTp = new TagProtocol((short) 0);

		assertTrue(tp.equals(equalTp));
	}

	@Test
	public final void equalsWithDiffertenIdTagProtocolsReturnsFalse() {
		TagProtocol tp = new TagProtocol((short) 0);
		TagProtocol differentTp = new TagProtocol((short) 1);

		assertFalse(tp.equals(differentTp));
	}

	@Test
	public final void equalsWithDifferentTypesReturnsFalse() {
		assertFalse(new TagProtocol((short) 0).equals(new Object()));
	}

	@Test
	public final void equalsWithNullReturnsFalse() {
		assertFalse(new TagProtocol((short) 0).equals(null));
	}

	@Test
	public final void hashCodeWithEqualIdTagProtocolsReturnsEqualHashCodes() {
		assertEquals(new TagProtocol((short) 0).hashCode(),
				new TagProtocol((short) 0).hashCode());
	}

	@Test
	public final void hashCodeWithDifferentIdTagProtocolsReturnsDifferentHashCodes() {
		assertNotEquals(new TagProtocol((short) 0).hashCode(),
				new TagProtocol((short) 1).hashCode());
	}

	@Test
	public final void sizeOfUnknownTagProtocolReturnsTwo() {
		assertEquals(2, TagProtocol.UNKNOWN.size());
	}
}
