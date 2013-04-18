package at.fabianachammer.pdusend.type.pdu;

import at.fabianachammer.pdusend.type.DataUnit;

/**
 * Interface that needs implementing classes to have a method that returns a
 * data unit that is contained in it.
 * 
 * @author fabian
 * 
 */
public interface EmbeddingProtocolDataUnit extends ProtocolDataUnit {
	
	/**
	 * Returns the embedded data unit of the protocol data unit.
	 * 
	 * @return embedded data unit of the protocol data unit
	 */
	DataUnit getEmbeddedData();
}
