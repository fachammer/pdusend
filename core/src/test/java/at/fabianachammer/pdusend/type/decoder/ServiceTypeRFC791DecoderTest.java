package at.fabianachammer.pdusend.type.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.ServiceTypeRFC791;

/**
 * @author fabian
 * 
 */
public class ServiceTypeRFC791DecoderTest {

	@Test
	public void decodeWithAllZeroByteInputReturnsDefaultServiceType() {
		ServiceTypeRFC791Decoder decoder =
				new ServiceTypeRFC791Decoder();
		ServiceTypeRFC791 expected = new ServiceTypeRFC791();
		byte input = 0;

		ServiceTypeRFC791 actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void decodeWithZeroSizeByteArrayInputThrowsIllegalArgumentException() {
		new ServiceTypeRFC791Decoder().decode(new byte[0]);
	}

	@Test(expected = NullPointerException.class)
	public void decodeWithNullThrowsNullPointerException() {
		new ServiceTypeRFC791Decoder().decode(null);
	}

	@Test
	public void decodeWithGreaterThanOneSizeByteArrayPassesAndIgnoresAllBytesButTheFirst() {
		ServiceTypeRFC791 expected = new ServiceTypeRFC791();

		ServiceTypeRFC791 actual =
				new ServiceTypeRFC791Decoder().decode(new byte[2]);

		assertEquals(expected, actual);
	}

}
