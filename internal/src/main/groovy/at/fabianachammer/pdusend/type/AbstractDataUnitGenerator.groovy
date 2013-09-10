package at.fabianachammer.pdusend.type

import org.gcontracts.annotations.Requires;

/**
 * Abstract factory which provides the name attribute for DataUnitGenerators which is used for identifying the generator.
 */
abstract class AbstractDataUnitGenerator implements DataUnitGenerator {
	
	/**
	* atrribute for identifying the generator.
	*/
	final String id

	@Requires({ id != null })
	AbstractDataUnitGenerator(String id){
		this.id = id
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AbstractDataUnitGenerator)){
			return false
		}

		AbstractDataUnitGenerator rhs = (AbstractDataUnitGenerator) obj
		return id == rhs.id
	}
}