package at.fabianachammer.pdusend.type.factory.parser

/**
 * @author fabian
 *
 */
abstract class PredefinedValueEntry {

	protected final def value
	protected final String valueName
	protected final int sizeInBits
	
	PredefinedValueEntry(value, String valueName, int sizeInBits){
		this.value = value
		this.valueName = valueName
		this.sizeInBits = sizeInBits
	}
	
	abstract void validate()
	
	abstract byte[] toByteArray()
}
