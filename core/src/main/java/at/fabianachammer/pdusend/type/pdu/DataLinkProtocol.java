package at.fabianachammer.pdusend.type.pdu;

import at.fabianachammer.pdusend.type.HardwareAddressType;

/**
 * Represents protocol data units on the data link layer of the OSI model (layer
 * 2).
 * 
 * @author fabian
 * 
 */
public interface DataLinkProtocol {

	/**
	 * returns the hardware address type of the DataLinkProtocolDataUnit.
	 * 
	 * @return hardware address type of the DataLinkProtocolDataUnit
	 */
	HardwareAddressType getHardwareAddressType();

}
