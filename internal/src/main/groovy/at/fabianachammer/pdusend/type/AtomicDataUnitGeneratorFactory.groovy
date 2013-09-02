package at.fabianachammer.pdusend.type

class AtomicDataUnitGeneratorFactory implements DataUnitGeneratorFactory {

	@Override
	public DataUnitGenerator createDataUnitGenerator(Map generatorData) {
		AtomicDataUnitGenerator gen =  new AtomicDataUnitGenerator(generatorData['sizeInBits'])
		generatorData.each{
			gen.addPredefinedValueByKey(it.key, it.value)
		}
		
		return gen
	}

}
