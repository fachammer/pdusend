package at.fabianachammer.pdusend.type

import org.gcontracts.annotations.Requires

import at.fabianachammer.pdusend.common.BitOperator
import at.fabianachammer.pdusend.common.Extension
import at.fabianachammer.pdusend.type.DataUnit


/**
 * Class that generates AtomicDataUnits based on an input.
 * @author fabian
 *
 */
class AtomicDataUnitGenerator implements DataUnitGenerator {

	/**
	 * size of the data units that this generator will create in bits.
	 */
	private final int dataUnitSizeInBits

	// id check in sub class because GContract implicitly joins require 
	// statements of super and sub class  with an OR
	@Requires({ dataUnitSizeInBits > 0 })
	AtomicDataUnitGenerator(int dataUnitSizeInBits){
		this.dataUnitSizeInBits = dataUnitSizeInBits
	}
	
	@Requires({ value instanceof Number || value instanceof byte[] })
	@Override
	DataUnit generateDataUnit(value) {
		byte[] atomicDataUnitData = getByteRepresentationFromValue(value)

		return new AtomicDataUnit(dataUnitSizeInBits, atomicDataUnitData)
	}

	private byte[] getByteRepresentationFromValue(value){
		switch(value.class){
			case Number:
				return BitOperator.toByteArray(value, dataUnitSizeInBits)
			case byte[]:
				return value
			default:
				throw new AssertionError('should never execute as classes got validated before')
		}
	}
}
