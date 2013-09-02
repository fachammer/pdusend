package at.fabianachammer.pdusend.type

import at.fabianachammer.pdusend.common.validation.Validator
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.DataUnitGenerator


/**
 * @author fabian
 *
 */
class FunctionDataUnitGenerator implements DataUnitGenerator {

	private static final int MIN_DATA_UNIT_SIZE_IN_BITS = 1

	private Closure<byte[]> function
	private int dataUnitSizeInBits
	private boolean isDataUnitSizeInBitsSet

	static FunctionDataUnitGenerator makeFromClosureAndDataUnitSizeInBits(Closure<byte[]> function, int dataUnitSizeInBits){
		validateClosureAndDataUnitSizeInBits(function, dataUnitSizeInBits)

		return new FunctionDataUnitGenerator(function, dataUnitSizeInBits)
	}

	private static void validateClosureAndDataUnitSizeInBits(Closure<byte[]> function, int dataUnitSizeInBits){
		validateClosure(function)
		validateDataUnitSizeInBits(dataUnitSizeInBits)
	}
	
	private static void validateClosure(Closure<byte[]> function){
		Validator v = new Validator(function, "function")
		v.validateNotNull()
	}

	private static void validateDataUnitSizeInBits(int dataUnitSizeInBits){
		Validator v = new Validator(dataUnitSizeInBits, "data unit size in bits")
		v.validateGreaterThanOrEquals(MIN_DATA_UNIT_SIZE_IN_BITS)
	}
	
	static FunctionDataUnitGenerator makeFromClosure(Closure<byte[]> function){
		validateClosure(function)

		return new FunctionDataUnitGenerator(function)
	}
	
	private FunctionDataUnitGenerator(Closure<byte[]> function){
		this.function = function
		this.isDataUnitSizeInBitsSet = false
	}

	private FunctionDataUnitGenerator(Closure<byte[]> function, int dataUnitSizeInBits){
		this(function)
		this.dataUnitSizeInBits = dataUnitSizeInBits
		this.isDataUnitSizeInBitsSet = true
	}

	@Override
	DataUnit generateDataUnit() {
		if(isDataUnitSizeInBitsSet)
			return new FunctionDataUnit(function, dataUnitSizeInBits)
		else
			return new FunctionDataUnit(function)
	}
}
