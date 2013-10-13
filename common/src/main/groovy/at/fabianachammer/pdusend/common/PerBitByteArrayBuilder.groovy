package at.fabianachammer.pdusend.common

import org.gcontracts.annotations.Requires

/**
 * Class for building byte arrays on a bit basis.
 * @author fabian
 *
 */
class PerBitByteArrayBuilder {

	// Use Extension class for simpler expressions of bit operations
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

	/**
	 * Adds bits to the already added bits.
	 * @param bitsToAdd byte array that holds the bits to be added
	 * @param sizeInBits positive value that defines how many bits to take from bitsToAdd 
	 */
	@Requires({bitsToAdd != null && sizeInBits > 0 && sizeInBits <= bitsToAdd.length * Byte.SIZE})
	void addBits(byte[] bitsToAdd, int sizeInBits){
		addElementToDataIfEmpty()

		for(int bitCounter = 0; bitCounter < sizeInBits; bitCounter++){
			copyBitFromInputToData(bitsToAdd, bitCounter)
		}
	}

	/**
	 * Adds an byte to the data if it's empty to allow processing on it
	 */
	private void addElementToDataIfEmpty(){
		if(data.isEmpty()){
			data.add(0)
		}
	}

	/**
	 * Copies the bit with the given index from the input byte array to the PerBitByteArrayBuilder data
	 * @param input data to copy from
	 * @param bitCounter index of the bit to copy in the whole array
	 */
	private void copyBitFromInputToData(byte[] input, int bitCounter){
		updateDataAndIndicesIfCurrentByteIsFull()
		int bitsToAddBitIndex = getBitIndexFromBitCounter(bitCounter)
		int bitsToAddArrayIndex = getArrayIndexFromBitCounter(bitCounter)

		setBitOnDataIfBitOnInputIsSet(input, bitsToAddArrayIndex, bitsToAddBitIndex)
	}

	/**
	 * resets data indices if the current byte was filled.
	 */
	private void updateDataAndIndicesIfCurrentByteIsFull(){
		if(isCurrentByteFull()){
			dataBitIndex = 0
			dataArrayIndex++
			data.add(0)
		}
	}

	/**
	 * @return true, if the current byte is full (all 8 bits processed), false otherwise 
	 */
	private boolean isCurrentByteFull(){
		dataBitIndex / Byte.SIZE == 1
	}

	/**
	 * @param bitCounter index of the current bit across all bits in the byte array
	 * @return the position of the bit inside the byte
	 */
	private int getBitIndexFromBitCounter(int bitCounter) {
		bitCounter % Byte.SIZE
	}

	/**
	 * @param bitCounter index of the current bit across all bits in the byte array
	 * @return the position of the byte inside the byte array
	 */
	private int getArrayIndexFromBitCounter(int bitCounter){
		Math.floor(bitCounter / Byte.SIZE)
	}

	/**
	 * Sets the current bit in the data if it's set in the input 
	 * @param input byte array to check if bit is set
	 * @param inputArrayIndex index of byte in the array where the bit which is to check resides
	 * @param inputBitIndex index of bit inside the current byte
	 */
	private void setBitOnDataIfBitOnInputIsSet(byte[] input, int inputArrayIndex, int inputBitIndex){
		if(input[inputArrayIndex].hasBitSetOnIndex(inputBitIndex))
			data[dataArrayIndex] = data[dataArrayIndex].orBitOnIndex(dataBitIndex)

		dataBitIndex++
	}

	/**
	 * @return data that was added to the PerBitByteArrayBuilder
	 */
	byte[] toByteArray(){
		return data
	}
}
