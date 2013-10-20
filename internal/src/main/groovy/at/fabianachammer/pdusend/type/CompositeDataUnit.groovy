package at.fabianachammer.pdusend.type

import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import org.gcontracts.annotations.Ensures
import org.gcontracts.annotations.Requires

import at.fabianachammer.pdusend.common.Extension
import at.fabianachammer.pdusend.common.PerBitByteArrayBuilder
import at.fabianachammer.pdusend.type.DataUnit

/**
 * data unit that can hold instances of data units. Follows the composite pattern.
 * @author fabian
 *
 */
@ToString(includeNames = true)
class CompositeDataUnit implements DataUnit {

	private final List<DataUnit> childDataUnits

	@Requires({ childDataUnits != null })
	CompositeDataUnit(DataUnit... childDataUnits){
		this.childDataUnits = childDataUnits
	}

	// Constructor has to be after the constructor with arguments. 
	// Otherwise the Requires closure won't work. Very weird...
	CompositeDataUnit(){
		childDataUnits = []
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

	@Override
	public boolean equals(Object obj) {
		if(obj.is(this)) return true
		if(! (obj instanceof CompositeDataUnit)) return false
		CompositeDataUnit rhs = (CompositeDataUnit) obj
		return this.childDataUnits == rhs.childDataUnits
	}

	@Override
	public int hashCode() {
		return HashCodeHelper.with{
			int initalHash = initHash()
			updateHash(initalHash, childDataUnits)
		}
	}
}
