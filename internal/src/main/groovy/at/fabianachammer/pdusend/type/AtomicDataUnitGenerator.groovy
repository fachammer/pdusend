package at.fabianachammer.pdusend.type

import org.gcontracts.annotations.Requires;

import at.fabianachammer.pdusend.common.BitOperator
import at.fabianachammer.pdusend.common.Extension
import at.fabianachammer.pdusend.common.validation.Validator
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.AbstractDataUnitGenerator


/**
 * Class that generates AtomicDataUnits based on an input.
 * @author fabian
 *
 */
class AtomicDataUnitGenerator extends AbstractDataUnitGenerator {

	/**
	 * size of the data units that this generator will create in bits.
	 */
	private int dataUnitSizeInBits

	static{
		Extension.extend()
	}
	
	@Requires({ dataUnitSizeInBits > 0 })
	public AtomicDataUnitGenerator(String id, int dataUnitSizeInBits){
		super(id)
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
