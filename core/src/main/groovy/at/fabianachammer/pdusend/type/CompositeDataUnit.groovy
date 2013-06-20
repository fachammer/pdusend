package at.fabianachammer.pdusend.type

import at.fabianachammer.pdusend.util.Extension
import at.fabianachammer.pdusend.util.PerBitByteArrayBuilder

/**
 * data unit that can hold instances of data units. follows the composite pattern.
 * @author fabian
 *
 */
class CompositeDataUnit implements DataUnit {

	static{
		Extension.extend()
	}

	private List<DataUnit> childDataUnits

	CompositeDataUnit(){
		childDataUnits = []
	}

	CompositeDataUnit(DataUnit... initialDataUnits){
		this()
		childDataUnits = initialDataUnits
	}

	@Override
	byte[] encode() {	
		PerBitByteArrayBuilder compositeData = new PerBitByteArrayBuilder()
		
		childDataUnits.each {
			compositeData.addBits(it.encode(), it.sizeInBits())
		}
		
		return compositeData.toByteArray()
	}
	
	@Override
	int sizeInBits() {
		childDataUnits*.sizeInBits().sum(0)
	}
}
