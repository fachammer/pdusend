package at.fabianachammer.pdusend.core.type

/**
 * data unit that cannot be divided into a smaller pieces of data (except bytes and bits).
 * @author fabian
 *
 */
class AtomicDataUnit implements DataUnit {

	private int sizeInBits
	private byte[] data

	AtomicDataUnit(int sizeInBits){
		this(sizeInBits, new byte[Math.ceil(sizeInBits / Byte.SIZE)])
	}

	AtomicDataUnit(byte... data){
		this(data.length * Byte.SIZE, data)
	}

	AtomicDataUnit(int sizeInBits, byte... data){
		this.sizeInBits = sizeInBits
		this.data = data
	}

	@Override
	byte[] encode() {
		return data
	}

	@Override
	public int sizeInBits() {
		return sizeInBits
	}
	
	@Override
	boolean equals(AtomicDataUnit rhs) {
		return sizeInBits == rhs.sizeInBits && data == rhs.data
	}
}
