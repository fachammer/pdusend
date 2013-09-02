package at.fabianachammer.pdusend.type


interface DataUnitGeneratorFactory {
	
	DataUnitGenerator createDataUnitGenerator(Map generatorData)
}
