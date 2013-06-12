package at.fabianachammer.pdusend.type

/**
 * data unit that cannot be divided into a smaller pieces of data (except bytes and bits).
 * @author fabian
 *
 */
class AtomicDataUnit implements DataUnit {

	private byte[] data
	
	AtomicDataUnit(byte... data){
		this.data = data
	}

	@Override
	byte[] encode() {
		return data
	}
}
