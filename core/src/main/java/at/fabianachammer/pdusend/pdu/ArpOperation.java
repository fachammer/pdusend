/**
 * 
 */
package at.fabianachammer.pdusend.pdu;

/**
 * Represents an operation of an ARP segment.
 * 
 * @author fabian
 * 
 */
public enum ArpOperation {
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
	 * Specifies the id of the operation.
	 */
	private short id;

	/**
	 * Creates a new Operation with the specified id.
	 * @param id id of the operation
	 */
	private ArpOperation(final short id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public short getId() {
		return id;
	}	
}
