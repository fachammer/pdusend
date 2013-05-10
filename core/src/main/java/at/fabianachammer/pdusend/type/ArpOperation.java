/**
 * 
 */
package at.fabianachammer.pdusend.type;

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
