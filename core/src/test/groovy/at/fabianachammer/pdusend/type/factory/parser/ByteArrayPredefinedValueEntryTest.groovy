package at.fabianachammer.pdusend.type.factory.parser

import static org.junit.Assert.*

import org.junit.Test

import at.fabianachammer.pdusend.util.validation.ValueTooGreatException

/**
 * @author fabian
 *
 */
class ByteArrayPredefinedValueEntryTest {

	@Test
	void validateWitValidByteArrayPasses() {
		ByteArrayPredefinedValueEntry entry = new ByteArrayPredefinedValueEntry([1] as byte[], "value", 1)
		
		entry.validate()
	}
	
	@Test(expected = ValueTooGreatException.class)
	void validateWithValueGreaterThanAllowedBySizeInBitsThrowsValueTooGreatException(){
		ByteArrayPredefinedValueEntry entry = new ByteArrayPredefinedValueEntry([2] as byte[], "value", 1)
		
		entry.validate()
	}
	
	@Test
	void validateWithValidMultipleElementsByteArrayPasses(){
		ByteArrayPredefinedValueEntry entry = new ByteArrayPredefinedValueEntry([1, 1] as byte[], "value", 9)
		
		entry.validate()
	}
	
	@Test
	void toByteArrayWithSizeInBitsOfOneAndByteElementOfOneReturnsByteArrayWithByteElementOfOne(){
		ByteArrayPredefinedValueEntry entry = new ByteArrayPredefinedValueEntry([1] as byte[], "value", 1)
		
		assertArrayEquals([1] as byte[], entry.toByteArray())
	}
	
	@Test
	void toByteArrayWithSizeInBitsGreaterThanBitCountOfValueReturnsByteArrayWithLeadingZeros(){
		ByteArrayPredefinedValueEntry entry = new ByteArrayPredefinedValueEntry([1] as byte[], "value", 9)
		
		assertArrayEquals([0,1] as byte[], entry.toByteArray())
	}

}
