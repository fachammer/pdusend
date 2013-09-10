package at.fabianachammer.pdusend.type

import org.gcontracts.annotations.Ensures;
import org.gcontracts.annotations.Requires;

import at.fabianachammer.pdusend.type.DataUnit

/**
 * data unit that cannot be divided into a smaller pieces of data (except bytes and bits).
 * @author fabian
 *
 */
class AtomicDataUnit implements DataUnit {

	private final int sizeInBits
	private final byte[] data

	@Requires({ sizeInBits >= 0 })
	AtomicDataUnit(int sizeInBits){
		this(sizeInBits, new byte[Math.ceil(sizeInBits / Byte.SIZE)])
	}

	@Requires({ data != null })
	AtomicDataUnit(byte... data){
		this(data.length * Byte.SIZE, data)
	}

	@Requires( { sizeInBits >= 0 && data != null })
	AtomicDataUnit(int sizeInBits, byte... data){
		this.sizeInBits = sizeInBits
		this.data = data
	}

	@Ensures({ result == data})
	@Override
	byte[] encode() {
		return data
	}

	@Ensures({ result == sizeInBits })
	@Override
	public int sizeInBits() {
		return sizeInBits
	}
	
	@Override
	boolean equals(AtomicDataUnit rhs) {
		return sizeInBits == rhs.sizeInBits && data == rhs.data
	}
}
