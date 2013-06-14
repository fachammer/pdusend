package at.fabianachammer.pdusend.type.factory

import at.fabianachammer.pdusend.type.AtomicDataUnit
import at.fabianachammer.pdusend.type.DataUnit

/**
 * @author fabian
 *
 */
class AtomicDataUnitFactory implements DataUnitFactory {
	
	static DataUnitFactory make(int bitSize){
		return new AtomicDataUnitFactory(bitSize)
	}
	
	private AtomicDataUnitFactory(int bitSize){
		
	}

	@Override
	DataUnit createDataUnit() {
		return new AtomicDataUnit()
	}

}
