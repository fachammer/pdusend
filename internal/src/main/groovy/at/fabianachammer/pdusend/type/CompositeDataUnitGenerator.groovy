package at.fabianachammer.pdusend.type

import at.fabianachammer.pdusend.common.validation.Validator
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.DataUnitGenerator

/**
 * @author fabian
 *
 */
class CompositeDataUnitGenerator implements DataUnitGenerator {
	
	private DataUnitGenerator[] childDataUnitFactories

	static DataUnitGenerator makeFromDataUnitFactories(DataUnitGenerator... childDataUnitFactories){
		validateChildDataUnitFactories(childDataUnitFactories)

		return new CompositeDataUnitGenerator(childDataUnitFactories)
	}

	private static void validateChildDataUnitFactories(DataUnitGenerator[] childDataUnitFactories) {
		Validator v = new Validator(childDataUnitFactories, "child data unit factories")
		v.validateNotNull()
	}

	private CompositeDataUnitGenerator(DataUnitGenerator... childDataUnitFactories){
		this.childDataUnitFactories = childDataUnitFactories
	}

	@Override
	public DataUnit generateDataUnit() {
		DataUnit[] childDataUnits = childDataUnitFactories*.generateDataUnit()

		return new CompositeDataUnit(childDataUnits)
	}
}
