package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author fabian
 *
 */
class AtomicDataUnitTest {

	@Test
	public void encodeWithOneByteAsInitializedDataReturnsArrayWithOneByte() {
		byte oneByte = 1
		DataUnit dataUnit = new AtomicDataUnit(oneByte)
		byte[] expected = [oneByte]

		byte[] actual = dataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	public void encodeWith2OneBytesAsInitializedDataReturnsArrayWith2OneBytes(){
		byte[] input = [1,1]
		DataUnit dataUnit = new AtomicDataUnit(input)
		byte[] expected = input
		
		byte[] actual = dataUnit.encode()
		
		assertArrayEquals(expected, actual)
	}
}
