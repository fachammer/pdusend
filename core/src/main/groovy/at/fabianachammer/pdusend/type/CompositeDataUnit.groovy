package at.fabianachammer.pdusend.type

import at.fabianachammer.pdusend.util.Extension

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

	private byte[] currentChildData
	private int currentChildBitCounter

	private byte[] compositeData
	private int compositeBitCounter

	CompositeDataUnit(){
		compositeBitCounter = 0
		childDataUnits = new ArrayList<DataUnit>()
	}

	CompositeDataUnit(DataUnit... initialDataUnits){
		this()
		childDataUnits = initialDataUnits
	}

	@Override
	byte[] encode() {
		initializeCompositeToNecessarySize()
		populateComposite()
		return compositeData
	}

	private initializeCompositeToNecessarySize(){
		int totalBitCount = sizeInBits()
		int byteArraySize = getNecessaryByteArraySizeFromBitCount(totalBitCount)

		compositeData = new byte[byteArraySize]
	}

	@Override
	int sizeInBits() {
		childDataUnits*.sizeInBits().sum(0)
	}

	private int getNecessaryByteArraySizeFromBitCount(int bitCount){
		Math.ceil(bitCount / Byte.SIZE)
	}
	
	private void populateComposite(){
		childDataUnits.each {
			currentChildData = it.encode()
			copyCurrentChildDataToComposite(it.sizeInBits())
		}
	}

	private void copyCurrentChildDataToComposite(int childBitCount){
		for(currentChildBitCounter = 0; currentChildBitCounter < childBitCount; incrementBitCounters()){
			copyCurrentBitFromChildToComposite()
		}
	}

	private void incrementBitCounters(){
		currentChildBitCounter++
		compositeBitCounter++
	}

	private void copyCurrentBitFromChildToComposite(){
		if(isCurrentChildBitSet()){
			setCurrentCompositeBit()
		}
	}

	private boolean isCurrentChildBitSet(){
		int childArrayIndex = getByteArrayIndexFromBitCounter(currentChildBitCounter)
		int childBitIndex = getBitIndexFromBitCounter(currentChildBitCounter) 
		
		currentChildData[childArrayIndex].hasBitSetOnIndex(childBitIndex)
	}
	
	private int getByteArrayIndexFromBitCounter(int bitCounter){
		Math.floor(bitCounter / Byte.SIZE)
	}

	private int getBitIndexFromBitCounter(int bitCounter){
		bitCounter % Byte.SIZE
	}

	private void setCurrentCompositeBit(){
		int compositeArrayIndex = getByteArrayIndexFromBitCounter(compositeBitCounter)
		int compositeBitIndex = getBitIndexFromBitCounter(compositeBitCounter)

		compositeData[compositeArrayIndex] = compositeData[compositeArrayIndex].setBitOnIndex(compositeBitIndex)
	}

	void addDataUnit(DataUnit dataUnitToAdd){
		childDataUnits.add(dataUnitToAdd)
	}

	void removeDataUnit(DataUnit dataUnitToRemove){
		childDataUnits.remove(dataUnitToRemove)
	}
}
