package at.fabianachammer.pdusend.type;

/**
 * data unit that performs a defined functionality upon encoding to create a byte array representation.
 * @author fabian
 *
 */
class FunctionDataUnit implements DataUnit {

	private Closure<byte[]> function
	private byte[] result

	FunctionDataUnit(Closure<byte[]> function) {
		this.function = function
	}

	@Override
	byte[] encode() {
		if(result == null){
			result = assignValue()
		}
		result
	}

	private assignValue(){
		function() ?: []
	}
}
