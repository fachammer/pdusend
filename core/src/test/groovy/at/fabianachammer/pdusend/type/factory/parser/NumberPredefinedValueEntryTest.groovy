package at.fabianachammer.pdusend.type.factory.parser

import static org.junit.Assert.*

import org.junit.Test

import at.fabianachammer.pdusend.util.validation.ValueNullException
import at.fabianachammer.pdusend.util.validation.ValueTooGreatException

/**
 * @author fabian
 *
 */
class NumberPredefinedValueEntryTest {

	@Test
	void validateWithValidNumberPasses(){
		NumberPredefinedValueEntry entry = new NumberPredefinedValueEntry(1, "value", 1)

		entry.validate()
	}

	@Test(expected = ValueTooGreatException.class)
	void validateWithNumberGreaterThanAllowedBySizeInBitsThrowsValueTooGreatException() {
		NumberPredefinedValueEntry entry = new NumberPredefinedValueEntry(2, "value", 1)

		entry.validate()
	}

	@Test
	void toByteArrayWithOneAsNumberAndOneAsSizeInBitsReturnsByteArrayWithOneAsByteElement(){
		NumberPredefinedValueEntry entry = new NumberPredefinedValueEntry(1, "value", 1)

		assertArrayEquals([1] as byte[], entry.toByteArray())
	}

	@Test
	void toByteArrayWithSizeInBitsGreaterThanNeededByValueReturnsByteArrayWithLeadingZeros(){
		NumberPredefinedValueEntry entry = new NumberPredefinedValueEntry(1, "value", 9)
		
		assertArrayEquals([0, 1] as byte[], entry.toByteArray())
	}
}
