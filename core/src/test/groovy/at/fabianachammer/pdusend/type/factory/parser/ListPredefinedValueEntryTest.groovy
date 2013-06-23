package at.fabianachammer.pdusend.type.factory.parser

import static org.junit.Assert.*

import org.junit.Test

import at.fabianachammer.pdusend.util.validation.ValueNullException
import at.fabianachammer.pdusend.util.validation.ValueHasIllegalClassException
import at.fabianachammer.pdusend.util.validation.ValueTooGreatException;
import at.fabianachammer.pdusend.util.Extension

/**
 * @author fabian
 *
 */
class ListPredefinedValueEntryTest {

	@Test
	void validateWithValidElementPasses(){
		ListPredefinedValueEntry entry = new ListPredefinedValueEntry([1], "value", 1)

		entry.validate()
	}

	@Test(expected = ValueNullException.class)
	void validateWithNullElementThrowsValueNullException() {
		ListPredefinedValueEntry entry = new ListPredefinedValueEntry([null], "value", 1)

		entry.validate()
	}

	@Test(expected = ValueTooGreatException.class)
	void validateWithNumberNotCastableToByteElementThrowsValueHasIllegalClassException(){
		ListPredefinedValueEntry entry = new ListPredefinedValueEntry([256], "value", 9)

		entry.validate()
	}

	@Test(expected = ValueTooGreatException.class)
	void validateWithValuesCombinedTooGreatThanAllowedByteSizeInBitsThrowsValueTooGreatException(){
		ListPredefinedValueEntry entry = new ListPredefinedValueEntry([2], "value", 1)

		entry.validate()
	}

	@Test(expected = ValueHasIllegalClassException.class)
	void validateWithStringValuesThrowsValueHasIllegalClassException(){
		ListPredefinedValueEntry entry = new ListPredefinedValueEntry(["1"], "value", 1)

		entry.validate()
	}

	@Test
	void validateWithValidMultipleElementsListPasses(){
		ListPredefinedValueEntry entry = new ListPredefinedValueEntry([1, 1], "value", 9)

		entry.validate()
	}

	@Test
	void toByteArrayWithSizeInBitsOfOneAndListElementOfOneReturnsByteArrayWithByteElementOfOne(){
		ListPredefinedValueEntry entry = new ListPredefinedValueEntry([1], "value", 1)

		assertArrayEquals([1] as byte[], entry.toByteArray())
	}

	@Test
	void toByteArrayWithSizeInBitsGreaterThanNeededByRepresentingValueReturnsByteArrayWithLeadingZeros(){
		ListPredefinedValueEntry entry = new ListPredefinedValueEntry([1], "value", 9)

		assertArrayEquals([0, 1] as byte[], entry.toByteArray())
	}
}
