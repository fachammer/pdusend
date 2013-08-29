package at.fabianachammer.pdusend.core.type

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

	FunctionDataUnit(Closure<byte[]> function) {
		this(function, SIZE_IN_BITS_UNSET_INDICATOR)
	}
	
	FunctionDataUnit(Closure<byte[]> function, int sizeInBits){
		this.function = function
		this.sizeInBits = sizeInBits
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
