package at.fabianachammer.pdusend.type

import org.gcontracts.annotations.Ensures;

import at.fabianachammer.pdusend.common.Extension
import at.fabianachammer.pdusend.common.PerBitByteArrayBuilder
import at.fabianachammer.pdusend.type.DataUnit

/**
 * data unit that can hold instances of data units. Follows the composite pattern.
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

	CompositeDataUnit(DataUnit... childDataUnits){
		this.childDataUnits = childDataUnits 
	}

	@Ensures({ result != null })
	@Override
	byte[] encode() {	
		PerBitByteArrayBuilder compositeData = new PerBitByteArrayBuilder()
		
		childDataUnits.each {
			compositeData.addBits(it.encode(), it.sizeInBits())
		}
		
		return compositeData.toByteArray()
	}
	
	@Ensures({ result >= 0 })
	@Override
	int sizeInBits() {
		childDataUnits*.sizeInBits().sum(0)
	}
}
