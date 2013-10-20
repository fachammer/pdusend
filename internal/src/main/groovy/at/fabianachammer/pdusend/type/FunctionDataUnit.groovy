package at.fabianachammer.pdusend.type

import org.gcontracts.annotations.Ensures;
import org.gcontracts.annotations.Requires;

import at.fabianachammer.pdusend.type.DataUnit

/**
 * data unit that performs a defined functionality upon encoding to create a byte array representation.
 * @author fabian
 *
 */
class FunctionDataUnit implements DataUnit {

	private static final int SIZE_IN_BITS_UNSET_INDICATOR = -1
	
	private final Closure<byte[]> function
	private byte[] encodedData
	private int sizeInBits

	FunctionDataUnit(Closure<byte[]> function) {
		this(function, SIZE_IN_BITS_UNSET_INDICATOR)
	}
	
	@Requires({ sizeInBits >= -1 })
	FunctionDataUnit(Closure<byte[]> function, int sizeInBits){
		this.function = function
		this.sizeInBits = sizeInBits
	}
	
	@Ensures({ result != null })
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

	@Ensures({ result >= 0 })
	@Override
	public int sizeInBits() {
		if(isSizeInBitsUnset()){
			setSizeInBitsAutomatically()
		}
		
		return sizeInBits
	}
	
	private void setSizeInBitsAutomatically(){
		sizeInBits = encode().length * Byte.SIZE
	}
	
	private boolean isSizeInBitsUnset(){
		sizeInBits == SIZE_IN_BITS_UNSET_INDICATOR
	}
}
