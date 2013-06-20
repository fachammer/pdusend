package at.fabianachammer.pdusend.util

import at.fabianachammer.pdusend.util.validation.Validator

/**
 * @author fabian
 *
 */
class PerBitByteArrayBuilder {

	static{
		Extension.extend()
	}

	private List<Byte> data
	private int dataBitIndex
	private int dataArrayIndex
	
	public PerBitByteArrayBuilder(){
		data = []
		dataBitIndex = 0
		dataArrayIndex = 0
	}

	void addBits(byte[] bitsToAdd, int sizeInBits){
		validateAddBitsArguments(bitsToAdd, sizeInBits)
		
		addElementToDataIfEmpty()
		
		for(int bitCounter = 0; bitCounter < sizeInBits; bitCounter++){
			copyBitFromInputToData(bitsToAdd, bitCounter)
		}
	}
	
	private void validateAddBitsArguments(byte[] bitsToAdd, int sizeInBits){
		validateBitsToAdd(bitsToAdd)
		validateSizeInBits(sizeInBits, bitsToAdd.length)
	}
	
	private void validateBitsToAdd(byte[] bitsToAdd){
		Validator v = new Validator(bitsToAdd, "bits to add")
		v.validateNotNull()
	}
	
	private void validateSizeInBits(int sizeInBits, int dataArrayLength){
		Validator v = new Validator(sizeInBits, "size in bits")
		v.validateGreaterThan(0)
		v.validateLowerThanOrEquals(dataArrayLength * Byte.SIZE)
	}
	
	private void addElementToDataIfEmpty(){
		if(data.isEmpty()){
			data[0] = 0
		}
	}

	private void copyBitFromInputToData(byte[] input, int bitCounter){
		updateDataAndIndicesIfLastByteIsFull()
		int bitsToAddBitIndex = getBitIndexFromBitCounter(bitCounter)
		int bitsToAddArrayIndex = getArrayIndexFromBitCounter(bitCounter)

		setBitOnDataIfBitOnInputIsSet(input, bitsToAddArrayIndex, bitsToAddBitIndex)
	}

	private void updateDataAndIndicesIfLastByteIsFull(){
		if(isLastByteFull()){
			dataBitIndex = 0
			dataArrayIndex++
			data.add(0)
		}
	}

	private boolean isLastByteFull(){
		dataBitIndex / Byte.SIZE == 1
	}

	private int getBitIndexFromBitCounter(int bitCounter) {
		bitCounter % Byte.SIZE
	}

	private int getArrayIndexFromBitCounter(int bitCounter){
		Math.floor(bitCounter / Byte.SIZE)
	}

	private void setBitOnDataIfBitOnInputIsSet(byte[] input, int inputArrayIndex, int inputBitIndex){
		if(isBitOnInputSet(input, inputArrayIndex, inputBitIndex))
			setBitOnData()
			
		dataBitIndex++
	}

	private boolean isBitOnInputSet(byte[] input, int inputArrayIndex, int inputBitIndex){
		input[inputArrayIndex].hasBitSetOnIndex(inputBitIndex)
	}

	private void setBitOnData(){
		data[dataArrayIndex] = data[dataArrayIndex].orBitOnIndex(dataBitIndex)
	}
	
	byte[] toByteArray(){
		return data
	}
}
