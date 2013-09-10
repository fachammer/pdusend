@Contracted
package at.fabianachammer.pdusend.type

import org.gcontracts.annotations.*

import at.fabianachammer.pdusend.common.validation.Validator
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.DataUnitGenerator
import at.fabianachammer.pdusend.type.AbstractDataUnitGenerator

/**
 * @author fabian
 *
 */
class CompositeDataUnitGenerator extends AbstractDataUnitGenerator {
	
	// TODO: adapt to new AbstractDataUnitGenerator with ID...
	
	private Map<String, DataUnitGenerator> generatorMap

	@Requires({ generatorMap != null })
	private CompositeDataUnitGenerator(String id, Map<String, DataUnitGenerator> generatorMap){
		super(id)
		this.generatorMap = generatorMap
	}
	
	@Ensures({result != null})
	@Override
	public DataUnit generateDataUnit(value) {
		Map mapValue = value
        def childDataUnits = [:]
		
		generatorMap.each{attribute, generator ->
			if(mapValue.containsKey(attribute)){
				childDataUnits[attribute] = generator.generateDataUnit(value[attribute])
			}
			else{
				childDataUnits[attribute] = generator.getDefaultValue()
			}
		}

		return new CompositeDataUnit(childDataUnits.values() as DataUnit[])
	}
}
