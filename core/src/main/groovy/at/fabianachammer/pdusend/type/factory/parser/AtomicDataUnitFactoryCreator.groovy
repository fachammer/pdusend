package at.fabianachammer.pdusend.type.factory.parser

import java.util.Map

import at.fabianachammer.pdusend.type.factory.AtomicDataUnitFactory
import at.fabianachammer.pdusend.type.factory.DataUnitFactory
import at.fabianachammer.pdusend.util.validation.Validator
import at.fabianachammer.pdusend.util.Extension

/**
 * @author fabian
 *
 */
class AtomicDataUnitFactoryCreator implements DataUnitFactoryCreator {
	
	private static final String SIZE_IN_BITS_KEY = "sizeInBits"
	private static final String VALUES_KEY = "values"
	
	static{
		Extension.extend()
	}
	
	@Override
	DataUnitFactory createDataUnitFactory(Map factoryData) {
		validateFactoryData(factoryData)
		int sizeInBits = getSizeInBitsIfValid(factoryData)
		Map predefinedValues = getPredefinedValuesIfExistAndValid(factoryData)
		
		return createAtomicDataUnitFactoryFromData(sizeInBits, predefinedValues)
	}
	
	private void validateFactoryData(Map factoryData){
		Validator v = new Validator(factoryData, "factory data")
		v.validateHasProperty(SIZE_IN_BITS_KEY)
	}
	
	private int getSizeInBitsIfValid(Map factoryData){
		def sizeInBits = factoryData[SIZE_IN_BITS_KEY]
		validateSizeInBits(sizeInBits)
		return sizeInBits
	}
	
	private void validateSizeInBits(sizeInBits){
		Validator v = new Validator(sizeInBits, "size in bits")
		v.validateInstanceOf(Number.class)
	}
	
	private Map getPredefinedValuesIfExistAndValid(Map factoryData){
		def predefinedValues = factoryData[VALUES_KEY]
		validatePredefinedValues(predefinedValues)
		return predefinedValues
	}
	
	private void validatePredefinedValues(predefinedValues){
		if(predefinedValues != null){
			Validator valuesValidator = new Validator(predefinedValues, "predefined values")
			valuesValidator.validateInstanceOf(Map.class)
		}
	}
	
	private AtomicDataUnitFactory createAtomicDataUnitFactoryFromData(int sizeInBits, Map predefinedValues){
		AtomicDataUnitFactory factory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(sizeInBits)
		
		predefinedValues?.eachWithIndex {it, i ->
			validateSinglePredefinedValue(it.value, i)
			addSinglePredefinedValueKeyPair(factory, it, sizeInBits)
			
		}
		
		return factory
	}
	
	private void validateSinglePredefinedValue(value, int i){
		Validator v = new Validator(value, "predefined value $i")
		v.validateInstanceOneOf(Number.class, byte[].class, ArrayList.class)
		
		if(value instanceof Number){
			validateAsNumber(v, value)
		}
	}
	
	private void validateAsNumber(Validator v, Number value){
		v.validateGreaterThanOrEquals(0)
	}
	
	private void addSinglePredefinedValueKeyPair(AtomicDataUnitFactory factory, Map.Entry predefinedValueKeyPair, int sizeInBits){
		byte[] valueAsByteArray
		def potentialValue = predefinedValueKeyPair.value
		
		if(potentialValue instanceof Number){
			valueAsByteArray = potentialValue.toByteArray(sizeInBits)
		}
		else if(potentialValue instanceof byte[]){
			valueAsByteArray = potentialValue
		}
		else if(potentialValue instanceof ArrayList){
			valueAsByteArray = potentialValue as byte[]
		}
		
		factory.addPredefinedValueByKey(predefinedValueKeyPair.key, valueAsByteArray)
	}
}
