package at.fabianachammer.pdusend.type.factory.parser;

import static org.junit.Assert.*;

import at.fabianachammer.pdusend.type.factory.AtomicDataUnitFactory
import at.fabianachammer.pdusend.type.factory.DataUnitFactory;
import at.fabianachammer.pdusend.util.validation.ObjectMissesPropertyException;
import at.fabianachammer.pdusend.util.validation.ValueHasIllegalClassException;
import at.fabianachammer.pdusend.util.validation.ValueTooLowException

import org.junit.Test;

/**
 * @author fabian
 *
 */
class AtomicDataUnitFactoryCreatorTest {

	@Test
	void createDataUnitFactoryWithMapContainingSizeInBitsReturnsAtomicDataUnitFactoryWithThatSizeInBits(){
		DataUnitFactoryCreator creator = new AtomicDataUnitFactoryCreator()
		Map factoryData = [sizeInBits: 1]
		AtomicDataUnitFactory expected = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		
		DataUnitFactory actual = creator.createDataUnitFactory(factoryData)
		
		assertEquals(expected, actual)
	}

	@Test
	void createDataUnitFactoryWithMapContainingSizeInBitsAndPredefinedValuesReturnsAtomicDataUnitFactoryWithThatSizeInBitsAndThosePredefinedValues(){
		DataUnitFactoryCreator creator = new AtomicDataUnitFactoryCreator()
		Map factoryData = [sizeInBits: 1, values: [zero: 0, one: 1]]
		AtomicDataUnitFactory expected = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		expected.addPredefinedValueByKey("zero", 0 as byte)
		expected.addPredefinedValueByKey("one", 1 as byte)
		
		AtomicDataUnitFactory actual = creator.createDataUnitFactory(factoryData)
		
		assertEquals(expected, actual)
	}
	
	@Test(expected = ObjectMissesPropertyException.class)
	void createDataUnitFactoryWithMapWithoutSizeInBitsThrowsMissingPropertyException(){
		DataUnitFactoryCreator creator = new AtomicDataUnitFactoryCreator()
		def mapNotContainingSizeInBits = [notSizeInBits: 1]
		
		creator.createDataUnitFactory(mapNotContainingSizeInBits)
	}
	
	@Test(expected = ValueHasIllegalClassException.class)
	void createDataUnitFactoryWithMapWithSizeInBitsNotBeingANumberThrowsValueHasIllegalClassException(){
		DataUnitFactoryCreator creator = new AtomicDataUnitFactoryCreator()
		def mapWithIllegalSizeInBitsClass = [sizeInBits: "illegal size in bits"]
		
		creator.createDataUnitFactory(mapWithIllegalSizeInBitsClass)
	}
	
	@Test(expected = ValueTooLowException.class)
	void createDataUnitFactoryWithMapContainingSizeInBitsWhichAreTooLow(){
		DataUnitFactoryCreator creator = new AtomicDataUnitFactoryCreator()
		def mapWithIllegalSizeInBits = [sizeInBits: 0]
		
		creator.createDataUnitFactory(mapWithIllegalSizeInBits)
	}
	
	@Test(expected = ValueHasIllegalClassException.class)
	void createDataUnitFactoryWithMapContainingValuesWithIllegalClassThrowsValueHasIllegalClassException(){
		DataUnitFactoryCreator creator = new AtomicDataUnitFactoryCreator()
		def mapWithIllegalValuesClass = [sizeInBits: 1, values: new Object()]
		
		creator.createDataUnitFactory(mapWithIllegalValuesClass)
	}
	
	@Test(expected = ValueHasIllegalClassException.class)
	void createDataUnitFactoryWithMapContainingValuesWhichAreNotNumbersThrowsValueHasIllegalClassException(){
		DataUnitFactoryCreator creator = new AtomicDataUnitFactoryCreator()
		def mapWithValuesHavingIllegalClasses = [sizeInBits: 1, values: [zero: "zero"]]
		
		creator.createDataUnitFactory(mapWithValuesHavingIllegalClasses)
	}
	
	@Test(expected = ValueTooLowException.class)
	void createDataUnitFactoryWithMapContainingNegativeValuesAsPredefinedValuesThrowsValueTooLowException(){
		DataUnitFactoryCreator creator = new AtomicDataUnitFactoryCreator()
		def mapWithValuesHavingNegativeValues = [sizeInBits: 1, values: [minusOne: -1]]
		
		creator.createDataUnitFactory(mapWithValuesHavingNegativeValues)
	}
	
	
	@Test
	void createDataUnitFactoryWithMapContainingPredefinedValuesWithSixteenAsSizeInBitsReturnsAtomicDataUnitFactoryWithThosePredefinedValues(){
		DataUnitFactoryCreator creator = new AtomicDataUnitFactoryCreator()
		def mapWithValuesWithSixteenBits = [sizeInBits: 16, values: [shortValue: 0xffff]]
		AtomicDataUnitFactory expected = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(16)
		byte[] value = [-1, -1]
		expected.addPredefinedValueByKey("shortValue", value)
		
		AtomicDataUnitFactory actual = creator.createDataUnitFactory(mapWithValuesWithSixteenBits)
		
		assertEquals(expected, actual)
	}
	
	@Test
	void createDataUnitFactoryWithMapContainingPredefinedValuesAsListsReturnsAtomicDataUnitFactoryWithThisListsAsPredefinedValuesByteArrays(){
		DataUnitFactoryCreator creator = new AtomicDataUnitFactoryCreator()
		def mapWithValuesAsList = [sizeInBits: 16, values: [first: [0,1]]]
		AtomicDataUnitFactory expected = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(16)
		expected.addPredefinedValueByKey("first", [0,1] as byte[])
		
		AtomicDataUnitFactory actual = creator.createDataUnitFactory(mapWithValuesAsList)
		
		assertEquals(expected, actual)
	}
	
	@Test
	void createDataUnitFactoryWithMapContainingPredefinedValuesAsByteArrayReturnsAtomicDataUnitFactoryWithThisByteArrayAsPredefinedValuesByteArrays(){
		DataUnitFactoryCreator creator = new AtomicDataUnitFactoryCreator()
		def mapWithValuesAsList = [sizeInBits: 16, values: [first: [0,1] as byte[]]]
		AtomicDataUnitFactory expected = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(16)
		expected.addPredefinedValueByKey("first", [0,1] as byte[])
		
		AtomicDataUnitFactory actual = creator.createDataUnitFactory(mapWithValuesAsList)
		
		assertEquals(expected, actual)
	}
}
