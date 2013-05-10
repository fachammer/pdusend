package at.fabianachammer.pdusend.type.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.TransportProtocolIdentifier;

/**
 * Decoder for decoding transport protocol identifiers.
 * 
 * @author fabian
 * 
 */
@Guarded
public class TransportProtocolIdentifierDecoder implements
		DataUnitDecoder<TransportProtocolIdentifier> {

	/**
	 * size the input must have.
	 */
	private static final int SIZE = 1;

	@Override
	public TransportProtocolIdentifier decode(@NotNull @Size(
			min = SIZE, max = SIZE) final byte... data) {
		return TransportProtocolIdentifier.create(data[0]);
	}
}
