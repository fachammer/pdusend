package at.fabianachammer.pdusend.type.factory

import at.fabianachammer.pdusend.type.CompositeDataUnit;
import at.fabianachammer.pdusend.type.DataUnit

/**
 * @author fabian
 *
 */
class CompositeDataUnitFactory implements DataUnitFactory {

	static DataUnitFactory makeFromDataUnitFactories(DataUnitFactory... childDataUnitFactories){
		return new CompositeDataUnitFactory(childDataUnitFactories)
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
