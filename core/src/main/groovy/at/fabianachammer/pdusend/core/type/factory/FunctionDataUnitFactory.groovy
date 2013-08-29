package at.fabianachammer.pdusend.core.type.factory

import at.fabianachammer.pdusend.core.type.DataUnit
import at.fabianachammer.pdusend.core.type.FunctionDataUnit
import at.fabianachammer.pdusend.core.util.validation.Validator


/**
 * @author fabian
 *
 */
class FunctionDataUnitFactory implements DataUnitFactory {

	private static final int MIN_DATA_UNIT_SIZE_IN_BITS = 1

	private Closure<byte[]> function
	private int dataUnitSizeInBits
	private boolean isDataUnitSizeInBitsSet

	static FunctionDataUnitFactory makeFromClosureAndDataUnitSizeInBits(Closure<byte[]> function, int dataUnitSizeInBits){
		validateClosureAndDataUnitSizeInBits(function, dataUnitSizeInBits)

		return new FunctionDataUnitFactory(function, dataUnitSizeInBits)
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
	
	static FunctionDataUnitFactory makeFromClosure(Closure<byte[]> function){
		validateClosure(function)

		return new FunctionDataUnitFactory(function)
	}
	
	private FunctionDataUnitFactory(Closure<byte[]> function){
		this.function = function
		this.isDataUnitSizeInBitsSet = false
	}

	private FunctionDataUnitFactory(Closure<byte[]> function, int dataUnitSizeInBits){
		this(function)
		this.dataUnitSizeInBits = dataUnitSizeInBits
		this.isDataUnitSizeInBitsSet = true
	}

	@Override
	DataUnit createDataUnit() {
		if(isDataUnitSizeInBitsSet)
			return new FunctionDataUnit(function, dataUnitSizeInBits)
		else
			return new FunctionDataUnit(function)
	}
}
