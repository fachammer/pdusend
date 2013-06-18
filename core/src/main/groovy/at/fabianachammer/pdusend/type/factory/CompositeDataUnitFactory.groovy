package at.fabianachammer.pdusend.type.factory

import at.fabianachammer.pdusend.type.CompositeDataUnit;
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.util.validation.Validator

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
	
	private CompositeDataUnitFactory(){
		childDataUnitFactories = []
	}

	private CompositeDataUnitFactory(DataUnitFactory... childDataUnitFactories){
		this.childDataUnitFactories = childDataUnitFactories
	}

	@Override
	public DataUnit createDataUnit() {
		DataUnit[] childDataUnits = childDataUnitFactories*.createDataUnit()

		CompositeDataUnit compositeDataUnit = new CompositeDataUnit(childDataUnits)

		return compositeDataUnit
	}
}
