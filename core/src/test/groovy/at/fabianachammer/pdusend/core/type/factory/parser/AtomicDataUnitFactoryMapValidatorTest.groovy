package at.fabianachammer.pdusend.core.type.factory.parser

import static org.junit.Assert.*

import org.junit.Test;

import at.fabianachammer.pdusend.core.type.factory.parser.AtomicDataUnitFactoryMapValidator;
import at.fabianachammer.pdusend.core.util.validation.ObjectMissesPropertyException;
import at.fabianachammer.pdusend.core.util.validation.ValueHasIllegalClassException;
import at.fabianachammer.pdusend.core.util.validation.ValueNullException;
import at.fabianachammer.pdusend.core.util.validation.ValueTooGreatException;
import at.fabianachammer.pdusend.core.util.validation.ValueTooLowException

/**
 * @author fabian
 *
 */
class AtomicDataUnitFactoryMapValidatorTest {

	@Test
	void validateWithMapOnlyContainingSizeInBitsPasses() {
		def input = [sizeInBits: 1]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ValueTooLowException.class)
	void validateWithMapContainingZeroSizeInBitsThrowsValueTooLowException(){
		def input = [sizeInBits: 0]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ObjectMissesPropertyException.class)
	void validateWithEmptyMapThrowsObjectMissesPropertyException(){
		def input = [:]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ObjectMissesPropertyException.class)
	void validateWithMapNotContainingSizeInBitsThrowsObjectMissesPropertyException(){
		def input = [sizeInBitts: 1]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ValueTooLowException.class)
	void validateWithMapContaingMinusOneSizeInBitsThrowsValueTooLowException(){
		def input = [sizeInBits: -1]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ValueHasIllegalClassException.class)
	void validateWithMapContainingSizeInBitsAsStringThrowsValueHasIllegalClassException(){
		def input = [sizeInBits: "1"]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ValueNullException.class)
	void validateWithMapContainingNullSizeInBitsThrowsValueNullException(){
		def input = [sizeInBits: null]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}

	@Test(expected = ValueNullException.class)
	void validateWithNullMapThrowsValueNullException(){
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(null)
		
		v.validate()
	}
	
	@Test(expected = ValueNullException.class)
	void validateWithMapContainingValidSizeInBitsAndNullPredefinedValuesThrowsValueNullException(){
		def input = [sizeInBits: 1, values: null]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test
	void validateWithMapContainingValidSizeInBitsAndValidValuesPasses(){
		def input = [sizeInBits: 1, values: [zero: 0]]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ValueHasIllegalClassException.class)
	void validateWithMapContainingValidSizeInBitsAndStringAsValuesThrowsValueHasIllegalClassException(){
		def input = [sizeInBits: 1, values: "values"]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ValueNullException.class)
	void validateWithMapContainingValidSizeInBitsAndMapAsPredefinedValuesWithNullAsValueThrowsValueNullException(){
		def input = [sizeInBits: 1, values: [zero: null]]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ValueHasIllegalClassException.class)
	void validateWithMapContainingValidSizeInBitsAndMapAsPredefinedValuesWithStringAsValueThrowsValueHasIllegalClassException(){
		def input = [sizeInBits: 1, values: [zero: "0"]]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ValueNullException.class)
	void validateWithMapContainingValidSizeInBitsAndMapAsPredefinedValuesWithListAsValueWithNullAsElementThrowsValueNullException(){
		def input = [sizeInBits: 1, values: [zero: [null]]]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ValueTooGreatException.class)
	void validateWithMapContainingValidSizeInBitsAndMapAsPredefinedValuesWithNumberGreaterThanSizeInBitsAllowsAsValueThrowsValueTooGreatException(){
		def input = [sizeInBits: 1, values: [two: 2]]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()	
	}
	
	@Test
	void validateWithMapContainingValidSizeInBitsAndMapAsPredefinedValuesWithValidByteArrayAsValuePasses(){
		def input = [sizeInBits: 1, values: [zero: [0] as byte[]]]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
	
	@Test(expected = ValueTooGreatException.class)
	void validateWithMapContainingValidSizeInBitsAndMapAspredefinedValuesWithByteArrayCarryingMoreDataThanSizeInBitsAllowsThrowsValueTooGreatException(){
		def input = [sizeInBits: 1, values: [two: [2] as byte[]]]
		AtomicDataUnitFactoryMapValidator v = new AtomicDataUnitFactoryMapValidator(input)
		
		v.validate()
	}
}
