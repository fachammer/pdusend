/**
 * 
 */
package at.fabianachammer.pdusend.type;

import at.fabianachammer.pdusend.type.decoder.ArpOperationDecoder;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Represents an operation of an ARP segment.
 * 
 * @author fabian
 * 
 */
public enum ArpOperation implements DataUnit {
	/**
	 * Unknown ARP operation.
	 */
	Unknown((short) 0),
	/**
	 * ARP request.
	 */
	Request((short) 1),

	/**
	 * ARP reply.
	 */
	Reply((short) 2),

	/**
	 * RARP request.
	 */
	RequestReverse((short) 3),

	/**
	 * RARP reply.
	 */
	ReplyReverse((short) 4);
	
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
	private ArpOperation(final short id) {
		setId(id);
	}

	@Override
	public DataUnitDecoder<ArpOperation> getDecoder() {
		return DECODER;
	}

	@Override
	public byte[] encode() {
		return BitOperator.split(getId());
	}
	
	@Override
	public int size() {
		return SIZE;
	}

	/**
	 * @return the id
	 */
	public short getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(final short id) {
		this.id = id;
	}
}
