package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.TransportProtocolIdentifierDecoder;

/**
 * @author fabian
 * 
 */
public class TransportProtocolIdentifierTest {

	@Test
	public final void equalsWithTransportProtocolIdentifiersWithEqualIdsReturnsTrue() {
		assertTrue(TransportProtocolIdentifier.UNKNOWN
				.equals(TransportProtocolIdentifier.UNKNOWN));
	}

	@Test
	public final void equalsWithTransportProtocolIdentifiersWithDifferentIdsReturnsFalse() {
		assertFalse(TransportProtocolIdentifier.create((byte) 0)
				.equals(TransportProtocolIdentifier.create((byte) 1)));
	}

	@Test
	public final void equalsWithDifferentTypesReturnsFalse() {
		assertFalse(TransportProtocolIdentifier.UNKNOWN
				.equals(new Object()));
	}

	@Test
	public final void equalsWithNullReturnsFalse() {
		assertFalse(TransportProtocolIdentifier.UNKNOWN.equals(null));
	}

	@Test
	public final void hashCodeWithEqualIdTransportProtocolIdentifiersReturnsEqualHashCodes() {
		assertEquals(TransportProtocolIdentifier.create((byte) 0)
				.hashCode(),
				TransportProtocolIdentifier.create((byte) 0).hashCode());
	}

	@Test
	public final void hashCodeWithDifferentIdTransportProtocolIdentifiersReturnsDifferentHashCodes() {
		assertNotEquals(TransportProtocolIdentifier.create((byte) 0).hashCode(),
				TransportProtocolIdentifier.create((byte) 1).hashCode());
	}
	
	@Test
	public void createWithIdOfUnknownTransportProtocolIdentifierReturnsUnknownProtocolIdentifier() {
		assertEquals(TransportProtocolIdentifier.UNKNOWN,
				TransportProtocolIdentifier.create((byte) 0xff));
	}

	@Test
	public void getDecoderReturnsInstanceOfTransportProtocolIdentifierDecoder() {
		DataUnitDecoder<TransportProtocolIdentifier> decoder =
				TransportProtocolIdentifier.create((byte) 0)
						.getDecoder();

		assertTrue(decoder instanceof TransportProtocolIdentifierDecoder);
	}

	@Test
	public final void encodeWithUnknownTransportProtocolIdentifierReturnsAllBitsSetSizeOneByteArray() {
		byte[] expected = { (byte) 0xff };

		byte[] actual = TransportProtocolIdentifier.UNKNOWN.encode();

		assertArrayEquals(expected, actual);
	}


	@Test
	public final void sizeReturns1() {
		assertEquals(1,
				TransportProtocolIdentifier.create((byte) 0).size());
	}
}
