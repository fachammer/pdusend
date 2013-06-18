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
	private int currentChildBitIndex
	private int currentChildArrayIndex

	private byte[] compositeData
	private int compositeBitIndex
	private int compositeArrayIndex

	CompositeDataUnit(){
		childDataUnits = new ArrayList<DataUnit>()
	}

	CompositeDataUnit(DataUnit... initialDataUnits){
		this()
		childDataUnits = initialDataUnits
	}

	@Override
	byte[] encode() {
		initializeComposite()

		populateComposite()
		
		return compositeData
	}
	
	private void initializeComposite(){
		int totalCompositeBitCount = sizeInBits()
		int compositeArraySize = getNecessaryByteArraySizeFromBitCount(totalCompositeBitCount)
		compositeData = new byte[compositeArraySize]
		compositeArrayIndex = compositeArraySize - 1
		compositeBitIndex = 0
	}
	
	@Override
	int sizeInBits() {
		childDataUnits*.sizeInBits().sum() ?: 0
	}

	private int getNecessaryByteArraySizeFromBitCount(int bitCount){
		def byteCount = bitCount / Byte.SIZE
		Math.ceil(byteCount)
	}
	
	private void populateComposite(){
		childDataUnits.reverse().each {DataUnit child ->
			copyChildToComposite(child)
		}
	}
	
	private void copyChildToComposite(DataUnit child){
		currentChildData = child.encode()
		currentChildArrayIndex = currentChildData.length - 1
		int childBitCount = child.sizeInBits()
		
		copyCurrentChildDataToComposite(childBitCount)
	}
	
	private void copyCurrentChildDataToComposite(int childBitCount){
		int childBitCounter = currentChildBitIndex = 0
		for(; childBitCounter < childBitCount; childBitCounter++){
			copyCurrentChildBitToComposite()
			incrementBitIndices()
		}
	}
	
	private void incrementBitIndices(){
		currentChildBitIndex++
		compositeBitIndex++
	}
	
	private void copyCurrentChildBitToComposite(){		
		updateIndices()
		setCompositeBitIfNecessary()
	}
	
	private void updateIndices(){
		updateCurrentChildIndices()
		updateCompositeIndices()
	}
	
	private void updateCurrentChildIndices(){
		if(doesBitIndexNeedToBeReset(currentChildBitIndex)){
			currentChildBitIndex = 0
			currentChildArrayIndex--
		}
	}
	
	private boolean doesBitIndexNeedToBeReset(int bitIndex){
		bitIndex / Byte.SIZE == 1
	}
	
	private void updateCompositeIndices(){
		if(doesBitIndexNeedToBeReset(compositeBitIndex)){
			compositeBitIndex = 0
			compositeArrayIndex--
		}
	}
	
	private void setCompositeBitIfNecessary(){
		if(isChildBitSetOnIndex(currentChildArrayIndex, currentChildBitIndex)){
			setCompositeBitOnIndex(compositeArrayIndex, compositeBitIndex)
		}
	}
	
	private boolean isChildBitSetOnIndex(int childArrayIndex, int childBitIndex){
		currentChildData[childArrayIndex].hasBitSetOnIndex(childBitIndex)
	}
	
	private void setCompositeBitOnIndex(int compositeArrayIndex, int compositeBitIndex){
		byte currentCompositeByte = compositeData[compositeArrayIndex]
		compositeData[compositeArrayIndex] = currentCompositeByte.orBitOnIndex(compositeBitIndex)
	}
}
