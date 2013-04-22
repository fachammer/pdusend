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
	public final void testDecodeWithValidOneByteIdOnArpOperationDecoderWorks() {
		ArpOperationDecoder decoder = new ArpOperationDecoder();
		byte validOneByteId = 1;
		ArpOperation expected = ArpOperation.REQUEST;

		ArpOperation actual = decoder.decode(validOneByteId);

		assertEquals(expected, actual);
	}

	@Test
	public final void testDecodeWithValidTwoByteIdOnArpOperationDecoderWorks() {
		ArpOperationDecoder decoder = new ArpOperationDecoder();
		byte[] validTwoByteId = { 0, (byte) 1 };
		ArpOperation expected = ArpOperation.REQUEST;

		ArpOperation actual = decoder.decode(validTwoByteId);

		assertEquals(expected, actual);
	}
}
