/**
 * 
 */
package at.fabianachammer.pdusend.type;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import at.fabianachammer.pdusend.type.decoder.ArpOperationDecoder;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Represents an operation of an ARP packet.
 * 
 * @author fabian
 * 
 */
public class ArpOperation extends DataUnit {

	/**
	 * Unknown ARP operation.
	 */
	public static final ArpOperation UNKNOWN = new ArpOperation(
			(short) 0);

	/**
	 * ARP request.
	 */
	public static final ArpOperation REQUEST = new ArpOperation(
			(short) 1);

	/**
	 * ARP reply.
	 */
	public static final ArpOperation REPLY = new ArpOperation(
			(short) 2);

	/**
	 * array that contains predefined ARP operations.
	 */
	public static final ArpOperation[] VALUES = {
			UNKNOWN, REQUEST, REPLY };

	/**
	 * size of an ARP operation in bytes.
	 */
	public static final int SIZE = 2;

	/**
	 * decoder for ARP operations.
	 */
	private static final DataUnitDecoder<ArpOperation> DECODER =
			new ArpOperationDecoder();

	/**
	 * id of the operation.
	 */
	private short id;

	/**
	 * Creates a new Operation with the specified id.
	 * 
	 * @param id
	 *            id of the operation
	 */
	public ArpOperation(final short id) {
		setId(id);
	}

	@Override
	public final DataUnitDecoder<ArpOperation> getDecoder() {
		return DECODER;
	}

	@Override
	public final byte[] encode() {
		return BitOperator.split(getId());
	}

	@Override
	public final int size() {
		return SIZE;
	}

	@Override
	protected final <T extends DataUnit> boolean isEquals(final T obj) {
		ArpOperation rhs = (ArpOperation) obj;
		return new EqualsBuilder().append(getId(), rhs.getId())
				.isEquals();
	}

	@Override
	public final int hashCode() {
		final int initial = 113;
		final int multiplier = 15;

		return new HashCodeBuilder(initial, multiplier).append(
				getId()).hashCode();
	}

	/**
	 * @return the id
	 */
	public final short getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	private void setId(final short id) {
		this.id = id;
	}
}
