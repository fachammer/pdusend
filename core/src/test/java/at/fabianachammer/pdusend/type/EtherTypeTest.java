package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.EtherTypeDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.RawDataUnitDecoder;

/**
 * @author fabian
 * 
 */
public class EtherTypeTest {

	/**
	 * Test method for
	 * {@link at.fabianachammer.pdusend.type.EtherType#getDecoder()}.
	 */
	@Test
	public final void testGetDecoderOnEtherTypeReturnsInstanceOfEtherTypeDecoder() {
		EtherType anyEtherType = EtherType.UNKNOWN;

		DataUnitDecoder<EtherType> decoder =
				anyEtherType.getDecoder();

		assertTrue(decoder instanceof EtherTypeDecoder);
	}

	/**
	 * Test method for {@link at.fabianachammer.pdusend.type.EtherType#encode()}
	 * .
	 */
	@Test
	public final void testEncodeOnEtherTypeWorks() {
		EtherType anyEtherType = EtherType.UNKNOWN;
		byte[] expected = { 0, 0 };

		byte[] actual = anyEtherType.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void testEqualsWithEtherTypesWithEqualIdsOnEtherTypeReturnsTrue() {
		EtherType etherType = new EtherType((short) 0);
		EtherType equalEtherType = new EtherType((short) 0);

		assertTrue(etherType.equals(equalEtherType));
	}

	@Test
	public final void testEqualsWithEtherTypesWithDifferentIdsOnEtherTypeReturnsFalse() {
		EtherType etherType = new EtherType((short) 0);
		EtherType differentEtherType = new EtherType((short) 1);

		assertFalse(etherType.equals(differentEtherType));
	}

	@Test
	public final void testEqualsWithDifferentTypesOnEtherTypeReturnsFalse() {
		assertFalse(new EtherType((short) 0).equals(new Object()));
	}

	@Test
	public final void testEqualsWithNullOnEtherTypeReturnsFalse() {
		assertFalse(new EtherType((short) 0).equals(null));
	}

	@Test
	public final void testHashCodeWithEtherTypesWithEqualIdsOnEtherTypeReturnsEqualHashCodes() {
		assertEquals(new EtherType((short) 0).hashCode(),
				new EtherType((short) 0).hashCode());
	}

	@Test
	public final void testHashCodeWithEtherTypesWithDifferentIdsOnEtherTypeReturnsDifferentHashCodes() {
		assertNotEquals(new EtherType((short) 0).hashCode(), new EtherType(
				(short) 1).hashCode());
	}
}
