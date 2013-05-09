package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.ArpOperationDecoder;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;

/**
 * @author fabian
 * 
 */
public class ArpOperationTest {

	@Test
	public final void getDecoderOnArpOperationReturnsInstanceOfArpOperationDecoder() {
		ArpOperation anyArpOperation = ArpOperation.REQUEST;

		DataUnitDecoder<ArpOperation> decoder =
				anyArpOperation.getDecoder();

		assertTrue(decoder instanceof ArpOperationDecoder);
	}

	@Test
	public final void encodeWithUnknownArpOperationReturnsAllZeroByteArray() {
		ArpOperation anyArpOperation = ArpOperation.UNKNOWN;
		byte[] expected = { 0, 0 };

		byte[] actual = anyArpOperation.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void equalsWithArpOperationWithEqualIdReturnsTrue() {
		ArpOperation arpOperation = new ArpOperation((short) 0);
		ArpOperation equalIdArpOperation =
				new ArpOperation((short) 0);

		assertTrue(arpOperation.equals(equalIdArpOperation));
	}

	@Test
	public final void equalsWithArpOperationWithDifferentIdReturnsFalse() {
		ArpOperation arpOperation = new ArpOperation((short) 0);
		ArpOperation differentIdArpOperation =
				new ArpOperation((short) 1);

		assertFalse(arpOperation.equals(differentIdArpOperation));
	}

	@Test
	public final void equalsWithDifferentTypeReturnsFalse() {
		assertFalse(ArpOperation.UNKNOWN.equals(new Object()));
	}

	@Test
	public final void equalsWithNullReturnsFalse() {
		assertFalse(ArpOperation.UNKNOWN.equals(null));
	}

	@Test
	public final void hashCodeWithEqualIdArpOperationsReturnsEqualHashCodes() {
		assertEquals(new ArpOperation((short) 0).hashCode(),
				new ArpOperation((short) 0).hashCode());
	}

	@Test
	public final void hashCodeWithDifferentIdArpOperationsReturnsDifferentHashCodes() {
		assertNotEquals(new ArpOperation((short) 0).hashCode(),
				new ArpOperation((short) 1).hashCode());
	}

	@Test
	public final void sizeOfUnknownArpOperationReturnsTwo() {
		assertEquals(2, ArpOperation.UNKNOWN.size());
	}

}
