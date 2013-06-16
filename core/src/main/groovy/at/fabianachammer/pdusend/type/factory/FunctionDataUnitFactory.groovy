package at.fabianachammer.pdusend.type.factory

import groovy.util.logging.Log4j;
import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.type.FunctionDataUnit

/**
 * @author fabian
 *
 */
class FunctionDataUnitFactory implements DataUnitFactory {

	private static final int DATA_UNIT_SIZE_IN_BITS_UNSET_INDICATOR = -1
	private static final int MIN_DATA_UNIT_SIZE_IN_BITS = 1

	private Closure<byte[]> function
	private int dataUnitSizeInBits

	static FunctionDataUnitFactory makeFromClosure(Closure<byte[]> function){
		return makeFromClosureAndDataUnitSizeInBits(function, DATA_UNIT_SIZE_IN_BITS_UNSET_INDICATOR)
	}

	static FunctionDataUnitFactory makeFromClosureAndDataUnitSizeInBits(Closure<byte[]> function, int dataUnitSizeInBits){
		validateConstructionArguments(function, dataUnitSizeInBits)

		return new FunctionDataUnitFactory(function, dataUnitSizeInBits)
	}

	private static void validateConstructionArguments(Closure<byte[]> function, int dataUnitSizeInBits){
		validateClosure(function)
		validateDataUnitSizeInBits(dataUnitSizeInBits)
	}

	private static void validateClosure(Closure<byte[]> function){
		if(function == null){
			throwFunctionMustNotBeNullException()
		}
	}

	private static void throwFunctionMustNotBeNullException(){
		throw new NullPointerException("function must not be null")
	}

	private static void validateDataUnitSizeInBits(int dataUnitSizeInBits){
		if(hasDataUnitSizeInBitsAnIllegalValue(dataUnitSizeInBits)){
			throwDataUnitSizeInBitsMustNotBeSmallerThanOneException(dataUnitSizeInBits)
		}
	}

	private static boolean hasDataUnitSizeInBitsAnIllegalValue(int dataUnitSizeInBits){
		isDataUnitSizeInBitsSmallerThanMinimum(dataUnitSizeInBits) && 
		isDataUnitSizeInBitsSet(dataUnitSizeInBits)
	}

	private static boolean isDataUnitSizeInBitsSmallerThanMinimum(int dataUnitSizeInBits){
		dataUnitSizeInBits < MIN_DATA_UNIT_SIZE_IN_BITS
	}

	private static boolean isDataUnitSizeInBitsSet(int dataUnitSizeInBits){
		dataUnitSizeInBits != DATA_UNIT_SIZE_IN_BITS_UNSET_INDICATOR
	}

	private static void throwDataUnitSizeInBitsMustNotBeSmallerThanOneException(int dataUnitSizeInBits){
		throw new IllegalArgumentException("data unit size in bits must be greater than 0 (actual: "+dataUnitSizeInBits+")")
	}

	private FunctionDataUnitFactory(Closure<byte[]> function){
		this(function, DATA_UNIT_SIZE_IN_BITS_UNSET_INDICATOR)
	}

	private FunctionDataUnitFactory(Closure<byte[]> function, int dataUnitSizeInBits){
		this.function = function
		this.dataUnitSizeInBits = dataUnitSizeInBits
	}

	@Override
	DataUnit createDataUnit() {
		return new FunctionDataUnit(function, dataUnitSizeInBits)
	}
}
