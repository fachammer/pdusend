package at.fabianachammer.pdusend.type;

import java.util.logging.LogManager;
import java.util.logging.Logger

/**
 * data unit that performs a defined functionality upon encoding to create a byte array representation.
 * @author fabian
 *
 */
class FunctionDataUnit implements DataUnit {

	private static final int SIZE_IN_BITS_UNSET_INDICATOR = -1
	
	private Closure<byte[]> function
	private byte[] encodedData
	private int sizeInBits

	private FunctionDataUnit(Closure<byte[]> function) {
		this(function, SIZE_IN_BITS_UNSET_INDICATOR)
	}
	
	private FunctionDataUnit(Closure<byte[]> function, int sizeInBits){
		this.function = function
		this.sizeInBits = sizeInBits
	}
	
	private int getEncodedDataLength(){
		return encode().length
	}

	@Override
	byte[] encode() {
		if(encodedData == null){
			encodedData = calculateEncodedData()
		}
		return encodedData
	}

	private byte[] calculateEncodedData(){
		function() ?: []
	}

	@Override
	public int sizeInBits() {
		if(isSizeInBitsUnset()){
			sizeInBits = encode().length * Byte.SIZE
		}
		
		return sizeInBits
	}
	
	private boolean isSizeInBitsUnset(){
		sizeInBits == SIZE_IN_BITS_UNSET_INDICATOR
	}
}
