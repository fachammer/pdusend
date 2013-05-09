package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.EtherTypeDecoder;

/**
 * @author fabian
 * 
 */
public class EtherTypeTest {

	@Test
	public final void getDecoderReturnsInstanceOfEtherTypeDecoder() {
		EtherType anyEtherType = EtherType.UNKNOWN;

		DataUnitDecoder<EtherType> decoder =
				anyEtherType.getDecoder();

		assertTrue(decoder instanceof EtherTypeDecoder);
	}

	@Test
	public final void encodeWithUnknownEtherTypeReturnsAllZeroByteArray() {
		EtherType anyEtherType = EtherType.UNKNOWN;
		byte[] expected = { 0, 0 };

		byte[] actual = anyEtherType.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void equalsWithEtherTypesWithEqualIdsReturnsTrue() {
		EtherType etherType = new EtherType((short) 0);
		EtherType equalEtherType = new EtherType((short) 0);

		assertTrue(etherType.equals(equalEtherType));
	}

	@Test
	public final void equalsWithEtherTypesWithDifferentIdsReturnsFalse() {
		EtherType etherType = new EtherType((short) 0);
		EtherType differentEtherType = new EtherType((short) 1);

		assertFalse(etherType.equals(differentEtherType));
	}

	@Test
	public final void equalsWithDifferentTypesOnEtherTypeReturnsFalse() {
		assertFalse(new EtherType((short) 0).equals(new Object()));
	}

	@Test
	public final void equalsWithNullOnEtherTypeReturnsFalse() {
		assertFalse(new EtherType((short) 0).equals(null));
	}

	@Test
	public final void hashCodeWithEtherTypesWithEqualIdsReturnsEqualHashCodes() {
		assertEquals(new EtherType((short) 0).hashCode(),
				new EtherType((short) 0).hashCode());
	}

	@Test
	public final void hashCodeWithEtherTypesWithDifferentIdsReturnsDifferentHashCodes() {
		assertNotEquals(new EtherType((short) 0).hashCode(),
				new EtherType((short) 1).hashCode());
	}
	
	@Test
	public final void sizeOfUnknownEtherTypeReturnsTwo(){
		assertEquals(2, EtherType.UNKNOWN.size());
	}
}
