/**
 * 
 */
package at.fabianachammer.nsend.pdu;

/**
 * interface for protocol data units (PDUs) which can be sent over the network.
 * @author fabian
 *
 */
public interface ProtocolDataUnit {
	
	/**
	 * converts the PDU into its byte representation.
	 * @return the bytes which make up the PDU
	 */
	byte[] toBytes();
}
