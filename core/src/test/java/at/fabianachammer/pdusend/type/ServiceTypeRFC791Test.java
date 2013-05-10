package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.ServiceTypeRFC791Decoder;

/**
 * @author fabian
 * 
 */
public class ServiceTypeRFC791Test {

	@Test
	public void encodeWithDefaultValuesReturnsProperByteRepresentation() {
		byte[] expected = { 0 };

		byte[] actual = new ServiceTypeRFC791().encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public void getDecoderReturnsInstanceOfServiceTypeDecoder() {
		DataUnitDecoder<ServiceTypeRFC791> decoder =
				new ServiceTypeRFC791().getDecoder();

		assertTrue(decoder instanceof ServiceTypeRFC791Decoder);
	}

	@Test(expected = NullPointerException.class)
	public void setNullPrecedenceThrowsNullPointerException() {
		new ServiceTypeRFC791().setPrecedence(null);
	}

	@Test
	public void equalsWithEqualDefaultServiceTypesReturnsTrue() {
		assertTrue(new ServiceTypeRFC791()
				.equals(new ServiceTypeRFC791()));
	}

	@Test
	public void equalsWithDifferentLowDelayServiceTypesReturnsFalse() {
		ServiceTypeRFC791 serviceType = new ServiceTypeRFC791();
		ServiceTypeRFC791 differentServiceType =
				new ServiceTypeRFC791();
		differentServiceType.setLowDelay(true);

		assertFalse(serviceType.equals(differentServiceType));
	}

	@Test
	public void equalsWithDifferentTypesReturnsFalse() {
		assertFalse(new ServiceTypeRFC791().equals(new Object()));
	}

	@Test
	public void equalsWithNullReturnsFalse() {
		assertFalse(new ServiceTypeRFC791().equals(null));
	}

	@Test
	public void hashCodeWithEqualDefaultServiceTypesReturnsEqualHashCodes() {
		assertEquals(new ServiceTypeRFC791().hashCode(),
				new ServiceTypeRFC791().hashCode());
	}

	@Test
	public void hashCodeWithDifferentLowDelayServiceTypesReturnsDifferentHashCodes() {
		ServiceTypeRFC791 serviceType = new ServiceTypeRFC791();
		ServiceTypeRFC791 differentServiceType =
				new ServiceTypeRFC791();
		differentServiceType.setLowDelay(true);

		assertNotEquals(serviceType.hashCode(),
				differentServiceType.hashCode());
	}

}
