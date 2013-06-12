package at.fabianachammer.pdusend.type

/**
 * data unit that can hold instances of data units. follows the composite pattern.
 * @author fabian
 *
 */
class CompositeDataUnit implements DataUnit {

	private List<DataUnit> childDataUnits
	
	CompositeDataUnit(){
		childDataUnits = new ArrayList<DataUnit>()
	}

	CompositeDataUnit(DataUnit... initialDataUnits){
		childDataUnits = initialDataUnits
	}

	@Override
	byte[] encode() {
		childDataUnits*.encode().flatten()
	}
	
	void addDataUnit(DataUnit dataUnitToAdd){
		childDataUnits.add(dataUnitToAdd)
	}
	
	void removeDataUnit(DataUnit dataUnitToRemove){
		childDataUnits.remove(dataUnitToRemove)
	}
}
