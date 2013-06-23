package at.fabianachammer.pdusend.type.factory.parser

import at.fabianachammer.pdusend.util.validation.Validator
import at.fabianachammer.pdusend.util.validation.ValueNullException
import at.fabianachammer.pdusend.util.validation.ValueTooGreatException
import at.fabianachammer.pdusend.util.validation.ValueTooLowException

/**
 * @author fabian
 *
 */
class AtomicDataUnitFactoryMapValidator {

	private static String SIZE_IN_BITS_KEY = "sizeInBits"
	private static String PREDEF_VALUES_KEY = "values"

	Map factoryData
	int sizeInBits

	AtomicDataUnitFactoryMapValidator(Map input){
		factoryData = input
	}

	void validate(){
		validateFactoryData()
		validateSizeInBits()
		validatePredefinedValuesIfExist()
	}

	private void validateFactoryData(){
		Validator factoryDataValidator = new Validator(factoryData, "factory data")
		factoryDataValidator.validateNotNull()
		factoryDataValidator.validateHasProperty(SIZE_IN_BITS_KEY)
	}

	private void validateSizeInBits(){
		def sizeInBits = factoryData[SIZE_IN_BITS_KEY]
		Validator sizeInBitsValidator = new Validator(sizeInBits, "size in bits")
		sizeInBitsValidator.validateNotNull()
		sizeInBitsValidator.validateInstanceOf(Number.class)
		sizeInBitsValidator.validateGreaterThan(0)

		this.sizeInBits = sizeInBits
	}

	private void validatePredefinedValuesIfExist(){
		if(factoryData.containsKey(PREDEF_VALUES_KEY)){
			validatePredefinedValues()
		}
	}

	private void validatePredefinedValues(){
		def predefinedValues = factoryData[PREDEF_VALUES_KEY]
		Validator predefinedValuesValidator = new Validator(predefinedValues, "predefined values")
		predefinedValuesValidator.validateNotNull()
		predefinedValuesValidator.validateInstanceOf(Map.class)
		predefinedValues.each { validateSinglePredefinedValue(it) }
	}

	private void validateSinglePredefinedValue(Map.Entry<String, Object> entry){
		Validator singlePredefinedValueValidator = new Validator(entry.value, "predefined value $entry.key")
		singlePredefinedValueValidator.validateNotNull()
		singlePredefinedValueValidator.validateInstanceOneOf(Number.class, List.class, byte[].class)

		validateSpecificValue(entry)
	}

	private void validateSpecificValue(Map.Entry<String, Object> entry){
		PredefinedValueEntryFactory factory =  new PredefinedValueEntryFactory(sizeInBits)
		PredefinedValueEntry specificValueValidator = factory.createEntry(entry)
		specificValueValidator.validate()
	}
}
