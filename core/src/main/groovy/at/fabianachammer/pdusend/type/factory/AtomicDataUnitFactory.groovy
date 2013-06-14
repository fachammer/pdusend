package at.fabianachammer.pdusend.type.factory

import at.fabianachammer.pdusend.type.AtomicDataUnit
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.util.Extension

/**
 * @author fabian
 *
 */
class AtomicDataUnitFactory implements DataUnitFactory {

	static{
		Extension.extend()
	}

	static DataUnitFactory makeFromDataUnitSizeInBits(int dataUnitSizeInBits){
		if(dataUnitSizeInBits < 1){
			throwDataUnitSizeTooLowIllegalArgumentException()
		}
		return new AtomicDataUnitFactory(dataUnitSizeInBits)
	}

	private static void throwDataUnitSizeTooLowIllegalArgumentException(){
		throw new IllegalArgumentException("data unit size in bits must be greater than 0")
	}

	private int dataUnitSizeInBits
	private Map<String, AtomicDataUnit> predefinedValues

	private AtomicDataUnitFactory(int dataUnitsSizeInBits){
		this.dataUnitSizeInBits = dataUnitsSizeInBits
		predefinedValues = [:]
	}

	@Override
	DataUnit createDataUnit() {
		return new AtomicDataUnit(dataUnitSizeInBits)
	}

	void addPredefinedValueByKey(String key, byte... value){
		if(isValueBitCountHigherThanDataUnitSizeInBits(value)){
			throwValueTooBigIllegalArgumentException()
		}
		predefinedValues[key] = new AtomicDataUnit(value)
	}

	private boolean isValueBitCountHigherThanDataUnitSizeInBits(byte[] value){
		int valueBitCount = value*.bitCount().sum(0)
		return valueBitCount > dataUnitSizeInBits
	}

	private void throwValueTooBigIllegalArgumentException(){
		throw new IllegalArgumentException(
		"value must not be bigger than " +
		2 ** dataUnitSizeInBits - 1 +
		" (" + dataUnitSizeInBits +
		" bits )")
	}

	AtomicDataUnit propertyMissing(String name){
		if(predefinedValuesHasKey(name)){
			return predefinedValues[name]
		}
		throwPropertyMissingException(name)
	}

	private boolean predefinedValuesHasKey(String name){
		return predefinedValues.containsKey(name)
	}

	private void throwPropertyMissingException(String name){
		throw new MissingPropertyException("property " + name +" doesn't exist and isn't a predefined value of this factory")
	}
}
