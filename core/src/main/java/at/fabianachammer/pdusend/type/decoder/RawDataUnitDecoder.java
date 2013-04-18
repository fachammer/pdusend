/**
 * 
 */
package at.fabianachammer.pdusend.type.decoder;

import at.fabianachammer.pdusend.type.RawDataUnit;

/**
 * Implements decoding for raw data units.
 * 
 * @author fabian
 * 
 */
public class RawDataUnitDecoder implements
		DataUnitDecoder<RawDataUnit> {

	@Override
	public final RawDataUnit decode(final byte... data) {
		return new RawDataUnit(data);
	}

}
