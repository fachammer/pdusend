package at.fabianachammer.pdusend.type.factory

import at.fabianachammer.pdusend.type.AtomicDataUnit
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.util.Extension
import at.fabianachammer.pdusend.util.validation.Validator

/**
 * @author fabian
 *
 */
class AtomicDataUnitFactory implements DataUnitFactory {

	private static int DATA_UNIT_SIZE_IN_BITS_MIN = 0
	
	private int dataUnitSizeInBits
	private Map<String, AtomicDataUnit> predefinedValues
	
	static{
		Extension.extend()
	}

	static AtomicDataUnitFactory makeFromDataUnitSizeInBits(int dataUnitSizeInBits){
		validateDataUnitSizeInBits(dataUnitSizeInBits)

		return new AtomicDataUnitFactory(dataUnitSizeInBits)
	}
	
	private static void validateDataUnitSizeInBits(int dataUnitSizeInBits){
		Validator v = new Validator(dataUnitSizeInBits, "data unit size in bits")
		
		v.validateGreaterThan(DATA_UNIT_SIZE_IN_BITS_MIN)
	}

	private AtomicDataUnitFactory(int dataUnitsSizeInBits){
		this.dataUnitSizeInBits = dataUnitsSizeInBits
		predefinedValues = [:]
	}

	@Override
	DataUnit createDataUnit() {
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
		if(!(obj instanceof AtomicDataUnitFactory)){
			return false
		}
		AtomicDataUnitFactory rhs = (AtomicDataUnitFactory) obj
		return predefinedValues == rhs.predefinedValues && dataUnitSizeInBits == rhs.dataUnitSizeInBits
	}
}
