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
	public final void testGetDecoderOnArpOperationReturnsInstanceOfArpOperationDecoder() {
		ArpOperation anyArpOperation = ArpOperation.REQUEST;

		DataUnitDecoder<ArpOperation> decoder =
				anyArpOperation.getDecoder();

		assertTrue(decoder instanceof ArpOperationDecoder);
	}

	@Test
	public final void testEncodeOnArpOperationWorks() {
		ArpOperation anyArpOperation = ArpOperation.REQUEST;
		byte[] expected = { 0, 1 };

		byte[] actual = anyArpOperation.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void isEqualsWithArpOperationWithEqualIdReturnsTrue() {
		ArpOperation arpOperation = new ArpOperation((short) 0);
		ArpOperation equalIdArpOperation =
				new ArpOperation((short) 0);

		assertTrue(arpOperation.isEquals(equalIdArpOperation));
	}

	@Test
	public final void isEqualsWithArpOperationWithDifferentIdReturnsFalse() {
		ArpOperation arpOperation = new ArpOperation((short) 0);
		ArpOperation differentIdArpOperation =
				new ArpOperation((short) 1);

		assertFalse(arpOperation.isEquals(differentIdArpOperation));
	}

}
