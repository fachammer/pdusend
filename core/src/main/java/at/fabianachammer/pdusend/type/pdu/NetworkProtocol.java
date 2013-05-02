package at.fabianachammer.pdusend.type.pdu;

import at.fabianachammer.pdusend.type.EtherType;

/**
 * Represents protocol data units on the network layer of the OSI model (layer
 * 3).
 * 
 * @author fabian
 * 
 */
public interface NetworkProtocol {

	/**
	 * returns the ether type of the NetworkProtocolDataUnit.
	 * 
	 * @return ether type of the NetworkProtocolDataUnit
	 */
	EtherType getEtherType();
}
