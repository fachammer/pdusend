@Contracted
package at.fabianachammer.pdusend.type

import org.gcontracts.annotations.*

import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.DataUnitGenerator

/**
 * @author fabian
 *
 */
class CompositeDataUnitGenerator implements DataUnitGenerator {
	
	
	private Map<String, DataUnitGenerator> generatorMap

	@Requires({ generatorMap != null })
	private CompositeDataUnitGenerator(Map<String, DataUnitGenerator> generatorMap){
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
		}

		CompositeDataUnit resultDataUnit = new CompositeDataUnit(childDataUnits.values() as DataUnit[])
		
		childDataUnits.each{key, data ->
			resultDataUnit.metaClass."$key" = data
		}
		
		return resultDataUnit
	}
}
