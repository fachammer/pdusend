package at.fabianachammer.pdusend.core.type.factory.parser

import at.fabianachammer.pdusend.core.util.validation.Validator
import at.fabianachammer.pdusend.core.util.Extension

/**
 * @author fabian
 *
 */
class ListPredefinedValueEntry extends PredefinedValueEntry{
	
	static{
		Extension.extend()
	}
	
	ListPredefinedValueEntry(List value, String valueName, int sizeInBits){
		super(value, valueName, sizeInBits)
	}

	@Override
	void validate(){
		validateSingleValues()
		validateList()
	}
	
	void validateSingleValues(){
		value.eachWithIndex {it, i ->
			validateSingleObjectConstraints(it, i)
			validateSingleBitCount(it, i)	
		}
	}
	
	void validateSingleObjectConstraints(value, int index){
		Validator objectConstraintValidator = new Validator(value, "predefined value list element at position $index")
		objectConstraintValidator.validateNotNull()
		objectConstraintValidator.validateInstanceOf(Number.class)
	}
	
	void validateSingleBitCount(Number value, int index){
		Validator bitCountValidator = new Validator(value.bitCount(), "bit count of predefined value list element at position $index")
		bitCountValidator.validateLowerThanOrEquals(Byte.SIZE)
	}
	
	void validateList(){
		int totalBitCount = calculateTotalBitCount()
		Validator totalBitCountValidator = new Validator(totalBitCount, "total bit count of list value $valueName")
		totalBitCountValidator.validateLowerThanOrEquals(sizeInBits)
	}
	
	private int calculateTotalBitCount(){
		int lsbPartBitCount = (value.size() - 1) * Byte.SIZE
		int msbBitCount = value[0].bitCount()
		
		return lsbPartBitCount + msbBitCount
	}
	
	@Override
	public byte[] toByteArray() {
		int arraySize = Math.ceil(sizeInBits / Byte.SIZE)
		int additionalSize = arraySize - value.size()
		List leadingZeros = new byte[additionalSize] as List
		List zerosAndValue = leadingZeros << value
		return zerosAndValue.flatten()
	}
}
