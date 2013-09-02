package at.fabianachammer.pdusend.type

import at.fabianachammer.pdusend.common.Extension
import at.fabianachammer.pdusend.common.validation.Validator
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.DataUnitGenerator

/**
 * @author fabian
 *
 */
class AtomicDataUnitGenerator implements DataUnitGenerator {

	private static int DATA_UNIT_SIZE_IN_BITS_MIN = 0
	
	private int dataUnitSizeInBits
	private Map<String, AtomicDataUnit> predefinedValues
	
	static{
		Extension.extend()
	}

	static AtomicDataUnitGenerator makeFromDataUnitSizeInBits(int dataUnitSizeInBits){
		validateDataUnitSizeInBits(dataUnitSizeInBits)

		return new AtomicDataUnitGenerator(dataUnitSizeInBits)
	}
	
	private static void validateDataUnitSizeInBits(int dataUnitSizeInBits){
		Validator v = new Validator(dataUnitSizeInBits, "data unit size in bits")
		
		v.validateGreaterThan(DATA_UNIT_SIZE_IN_BITS_MIN)
	}

	private AtomicDataUnitGenerator(int dataUnitsSizeInBits){
		this.dataUnitSizeInBits = dataUnitsSizeInBits
		predefinedValues = [:]
	}

	@Override
	DataUnit generateDataUnit() {
		return new AtomicDataUnit(dataUnitSizeInBits)
	}

	void addPredefinedValueByKey(String key, byte[] value){
		validateAddPredefinedValueByKeyArguments(key, value)

		predefinedValues[key] = new AtomicDataUnit(value)
	}
	
	private void validateAddPredefinedValueByKeyArguments(String key, byte[] value){
		validateKey(key)
		validatePredefinedValue(value)
	}
	
	private void validateKey(String key){
		Validator v = new Validator(key, "key")
		
		v.validateNotNull()
	}
	
	private void validatePredefinedValue(byte[] value){
		int valueBitCount = value*.bitCount().sum(0)
		Validator v = new Validator(valueBitCount, "value bit count")
		
		v.validateLowerThanOrEquals(dataUnitSizeInBits)
	}

	AtomicDataUnit propertyMissing(String name){
		if(predefinedValuesHasKey(name)){
			return predefinedValues[name]
		}
		
		throwMissingPropertyException(name)
	}

	private boolean predefinedValuesHasKey(String name){
		return predefinedValues.containsKey(name)
	}

	private void throwMissingPropertyException(String name){
		throw new MissingPropertyException("property " + name +" doesn't exist and isn't a predefined value of this factory")
	}
	
	@Override
	boolean equals(Object obj) {
		if(!(obj instanceof AtomicDataUnitGenerator)){
			return false
		}
		AtomicDataUnitGenerator rhs = (AtomicDataUnitGenerator) obj
		return predefinedValues == rhs.predefinedValues && dataUnitSizeInBits == rhs.dataUnitSizeInBits
	}
}
