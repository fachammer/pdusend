package at.fabianachammer.pdusend.type;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.pdu.ProtocolDataUnit;

/**
 * @author fabian
 * 
 */
public interface ProtocolIdentifier {

	/**
	 * Returns the decoder used for decoding bytes of this protocol.
	 * 
	 * @return DataUnitDecoder that can decode arrays of bytes into data units
	 *         of this protocol
	 */
	DataUnitDecoder<? extends ProtocolDataUnit> getProtocolDecoder();
}
