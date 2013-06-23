package at.fabianachammer.pdusend.type.factory.parser

import at.fabianachammer.pdusend.util.validation.Validator
import at.fabianachammer.pdusend.util.Extension

/**
 * @author fabian
 *
 */
class ByteArrayPredefinedValueEntry extends PredefinedValueEntry {
	
	PredefinedValueEntry listValidator

	static{
		Extension.extend()
	}
	
	ByteArrayPredefinedValueEntry(byte[] value, String valueName, int sizeInBits){
		super(value, valueName, sizeInBits)
		listValidator = new ListPredefinedValueEntry(value as List, valueName, sizeInBits)
	}
	
	@Override
	void validate() {
		listValidator.validate()
	}
	
	@Override
	byte[] toByteArray() {
		listValidator.toByteArray()
	}
}
