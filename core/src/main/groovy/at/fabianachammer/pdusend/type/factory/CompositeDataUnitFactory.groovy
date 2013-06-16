package at.fabianachammer.pdusend.type.factory

import at.fabianachammer.pdusend.type.CompositeDataUnit;
import at.fabianachammer.pdusend.type.DataUnit

/**
 * @author fabian
 *
 */
class CompositeDataUnitFactory implements DataUnitFactory {

	static DataUnitFactory makeFromDataUnitFactories(DataUnitFactory... childDataUnitFactories){
		if(childDataUnitFactories == null){
			throwChildDataUnitFactoriesMustNotBeNullException()
		}
		return new CompositeDataUnitFactory(childDataUnitFactories)
	}
	
	private static void throwChildDataUnitFactoriesMustNotBeNullException(){
		throw new NullPointerException("child data unit factories must not be null")
	}
	
	private List<DataUnitFactory> childDataUnitFactories
	
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
