package at.fabianachammer.pdusend.type.factory.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.util.validation.ValueHasIllegalClassException;

/**
 * @author fabian
 *
 */
class PredefinedValueValidatorFactoryTest {

	@Test
	void createValidatorWithInstanceOfNumberReturnsNumberPredefinedValueValidator() {
		PredefinedValueEntryFactory factory = new PredefinedValueEntryFactory(1)
		
		PredefinedValueEntry actual = factory.createEntry(["value": 1].entrySet()[0])
		
		assertTrue(actual instanceof NumberPredefinedValueEntry) 
	}
	
	@Test
	void createValidatorWithInstanceOfListReturnsListPredefinedValueValidator() {
		PredefinedValueEntryFactory factory = new PredefinedValueEntryFactory(1)
		
		PredefinedValueEntry actual = factory.createEntry(["value": [1]].entrySet()[0])
		
		assertTrue(actual instanceof ListPredefinedValueEntry)
	}
	
	@Test
	void createValidatorWithInstanceOfByteArrayReturnsByteArrayPredefinedValueValidator() {
		PredefinedValueEntryFactory factory = new PredefinedValueEntryFactory(1)
		
		PredefinedValueEntry actual = factory.createEntry(["value": [1] as byte[]].entrySet()[0])
		
		assertTrue(actual instanceof ByteArrayPredefinedValueEntry)
	}

	@Test(expected = ValueHasIllegalClassException.class)
	void createWithIllegalInstanceThrowsValueHasIllegalClassException(){
		new PredefinedValueEntryFactory(1).createEntry(["value": new Object()].entrySet()[0])
	}
}
