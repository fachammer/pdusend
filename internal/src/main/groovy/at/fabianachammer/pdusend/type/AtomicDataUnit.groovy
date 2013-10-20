package at.fabianachammer.pdusend.type

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import org.gcontracts.annotations.Ensures
import org.gcontracts.annotations.Requires

import at.fabianachammer.pdusend.type.DataUnit

/**
 * data unit that cannot be divided into a smaller pieces of data (except bytes and bits).
 * @author fabian
 *
 */
@ToString(includeNames = true)
class AtomicDataUnit implements DataUnit {

	private final int sizeInBits
	private final byte[] data

	@Requires({ sizeInBits > 0 })
	AtomicDataUnit(int sizeInBits){
		this(sizeInBits, new byte[Math.ceil(sizeInBits / Byte.SIZE)])
	}

	@Requires({ data != null })
	AtomicDataUnit(byte... data){
		this(data.length * Byte.SIZE, data)
	}

	@Requires( { sizeInBits > 0 && data != null })
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
	public boolean equals(Object obj) {
		if(obj.is(this)) return true
		if(! (obj instanceof AtomicDataUnit)) return false
		AtomicDataUnit rhs = (AtomicDataUnit) obj
		return this.sizeInBits == rhs.sizeInBits && this.data == rhs.data
	}
	
	@Override
	public int hashCode() {
		return HashCodeHelper.with {
			int initialHash = initHash()
			int sizeInBitsHash = updateHash(initialHash, sizeInBits)
			updateHash(sizeInBitsHash, data)
		}
	}
}