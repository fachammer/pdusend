package at.fabianachammer.pdusend.core.type.factory

import at.fabianachammer.pdusend.core.type.CompositeDataUnit;
import at.fabianachammer.pdusend.core.type.DataUnit
import at.fabianachammer.pdusend.core.util.validation.Validator

/**
 * @author fabian
 *
 */
class CompositeDataUnitFactory implements DataUnitFactory {
	
	private DataUnitFactory[] childDataUnitFactories

	static DataUnitFactory makeFromDataUnitFactories(DataUnitFactory... childDataUnitFactories){
		validateChildDataUnitFactories(childDataUnitFactories)

		return new CompositeDataUnitFactory(childDataUnitFactories)
	}

	private static void validateChildDataUnitFactories(DataUnitFactory[] childDataUnitFactories) {
		Validator v = new Validator(childDataUnitFactories, "child data unit factories")
		v.validateNotNull()
	}

	private CompositeDataUnitFactory(DataUnitFactory... childDataUnitFactories){
		this.childDataUnitFactories = childDataUnitFactories
	}

	@Override
	public DataUnit createDataUnit() {
		DataUnit[] childDataUnits = childDataUnitFactories*.createDataUnit()

		return new CompositeDataUnit(childDataUnits)
	}
}
