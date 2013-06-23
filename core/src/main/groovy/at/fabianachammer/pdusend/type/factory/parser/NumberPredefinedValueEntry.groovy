package at.fabianachammer.pdusend.type.factory.parser

import at.fabianachammer.pdusend.util.validation.Validator
import at.fabianachammer.pdusend.util.Extension

/**
 * @author fabian
 *
 */
class NumberPredefinedValueEntry extends PredefinedValueEntry {

	static{
		Extension.extend()
	}
	
	private final int maxValue
	
	NumberPredefinedValueEntry(Number value, String valueName, int sizeInBits){
		super(value, valueName, sizeInBits)
		this.maxValue = 2 ** sizeInBits - 1
	}

	void validate(){
		Validator v = new Validator(value, valueName)
		v.validateLowerThanOrEquals(maxValue)
	}
	
	@Override
	public byte[] toByteArray() {
		value.toByteArray(sizeInBits)
	}
}
