package at.fabianachammer.pdusend.type.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.ArpOperation;

/**
 * @author fabian
 * 
 */
public class ArpOperationDecoderTest {

	@Test
	public final void decodeWithZeroOneByteIdReturnsUnknownArpOperation() {
		ArpOperationDecoder decoder = new ArpOperationDecoder();
		byte validOneByteId = 0;
		ArpOperation expected = ArpOperation.UNKNOWN;

		ArpOperation actual = decoder.decode(validOneByteId);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithZeroTwoByteIdReturnsUnknownArpOperation() {
		ArpOperationDecoder decoder = new ArpOperationDecoder();
		byte[] validTwoByteId = { 0, 0 };
		ArpOperation expected = ArpOperation.UNKNOWN;

		ArpOperation actual = decoder.decode(validTwoByteId);

		assertEquals(expected, actual);
	}
}
