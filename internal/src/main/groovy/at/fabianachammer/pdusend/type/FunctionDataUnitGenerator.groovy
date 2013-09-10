package at.fabianachammer.pdusend.type

import org.gcontracts.annotations.Requires;

import at.fabianachammer.pdusend.common.validation.Validator
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.DataUnitGenerator
import at.fabianachammer.pdusend.type.AbstractDataUnitGenerator


/**
 * DataUnitGenerator for generating FunctionDataUnits
 * @author fabian
 *
 */
class FunctionDataUnitGenerator extends AbstractDataUnitGenerator {
	
	private Closure<byte[]> function
	private int dataUnitSizeInBits
	private boolean isDataUnitSizeInBitsSet

	@Requires({ function != null })
	FunctionDataUnitGenerator(String id, Closure<byte[]> function){
		super(id)
		this.function = function
		this.isDataUnitSizeInBitsSet = false
	}

	@Requires({ function != null && dataUnitSizeInBits > 0 })
	FunctionDataUnitGenerator(String id, Closure<byte[]> function, int dataUnitSizeInBits){
		this(id, function)
		this.dataUnitSizeInBits = dataUnitSizeInBits
		this.isDataUnitSizeInBitsSet = true
	}

	@Override
	DataUnit generateDataUnit(value) {
		if(isDataUnitSizeInBitsSet)
			return new FunctionDataUnit(function, dataUnitSizeInBits)
		else
			return new FunctionDataUnit(function)
	}
}
