/**
 * 
 */
package at.fabianachammer.pdusend.type.pdu.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.pdu.RawDataUnit;

/**
 * Implements decoding for raw data units.
 * 
 * @author fabian
 * 
 */
@Guarded
public class RawDataUnitDecoder implements
		DataUnitDecoder<RawDataUnit> {

	@Override
	public final RawDataUnit decode(@NotNull final byte... data) {
		return new RawDataUnit(data);
	}

}
