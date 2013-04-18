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

	/**
	 * Test method for
	 * {@link at.fabianachammer.pdusend.type.ArpOperation#getDecoder()}.
	 */
	@Test
	public final void testGetDecoderOnArpOperationReturnsInstanceOfArpOperationDecoder() {
		ArpOperation anyArpOperation = ArpOperation.Request;

		DataUnitDecoder<ArpOperation> decoder =
				anyArpOperation.getDecoder();

		assertTrue(decoder instanceof ArpOperationDecoder);
	}

	/**
	 * Test method for
	 * {@link at.fabianachammer.pdusend.type.ArpOperation#encode()}.
	 */
	@Test
	public final void testEncodeOnArpOperationWorks() {
		ArpOperation anyArpOperation = ArpOperation.Request;
		byte[] expected = { 0, 1 };

		byte[] actual = anyArpOperation.encode();

		assertArrayEquals(expected, actual);
	}

}
