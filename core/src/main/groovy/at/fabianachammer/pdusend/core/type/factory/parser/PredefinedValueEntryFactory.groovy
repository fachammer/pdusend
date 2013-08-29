package at.fabianachammer.pdusend.core.type.factory.parser

import at.fabianachammer.pdusend.core.util.validation.Validator

/**
 * @author fabian
 *
 */
class PredefinedValueEntryFactory {
	
	private int sizeInBitsOfPredefinedValues
	
	PredefinedValueEntryFactory(int sizeInBitsOfPredefinedValues){
		this.sizeInBitsOfPredefinedValues = sizeInBitsOfPredefinedValues
	}

	PredefinedValueEntry createEntry(Map.Entry<String, Object> entry){
		Validator v = new Validator(entry.value, entry.key)
		v.validateInstanceOneOf(Number.class, List.class, byte[].class)
		
		if(entry.value instanceof Number){
			return new NumberPredefinedValueEntry(entry.value, entry.key, sizeInBitsOfPredefinedValues)
		}
		else if(entry.value instanceof List){
			return new ListPredefinedValueEntry(entry.value, entry.key, sizeInBitsOfPredefinedValues)
		}
		else{
			return new ByteArrayPredefinedValueEntry(entry.value, entry.key, sizeInBitsOfPredefinedValues)
		}
	}
}
