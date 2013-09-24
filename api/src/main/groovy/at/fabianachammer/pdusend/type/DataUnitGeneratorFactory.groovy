package at.fabianachammer.pdusend.type

/**
 * Abstract Factory for DataUnitGenerators.
 * @author fabian
 *
 */
interface DataUnitGeneratorFactory {
	
	/**
	 * Creates a new DataUnitGenerator based on a map of configuration data.
	 * @param generatorData map of configuration data for the DataUnitGenerator
	 * @return implementation of a DataUnitGenerator
	 */
	DataUnitGenerator createDataUnitGenerator(Map generatorData)
	
}
