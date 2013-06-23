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
		AtomicDataUnitFactoryMapValidator mapValidator = new AtomicDataUnitFactoryMapValidator(factoryData)
		mapValidator.validate()
		
		return createAtomicDataUnitFactoryFromData(factoryData[SIZE_IN_BITS_KEY], factoryData[VALUES_KEY])
	}
	
	private AtomicDataUnitFactory createAtomicDataUnitFactoryFromData(int sizeInBits, Map predefinedValues){
		AtomicDataUnitFactory factory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(sizeInBits)
		PredefinedValueEntryFactory predefinedValueFactory = new PredefinedValueEntryFactory(sizeInBits)
		
		predefinedValues?.each{
			PredefinedValueEntry e = predefinedValueFactory.createEntry(it)
			factory.addPredefinedValueByKey(it.key, e.toByteArray())
		}
		
		return factory
	}
}
