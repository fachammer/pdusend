/**
 * 
 */
package at.fabianachammer.pdusend.type.decoder;

import at.fabianachammer.pdusend.type.TagProtocol;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Implements decoding for tag protocols.
 * 
 * @author fabian
 * 
 */
public class TagProtocolDecoder implements
		DataUnitDecoder<TagProtocol> {

	@Override
	public final TagProtocol decode(final byte... data) {
		short id = BitOperator.merge(data[1], data[0]);

		for (TagProtocol tp : TagProtocol.values()) {
			if (tp.getId() == id) {
				return tp;
			}
		}

		TagProtocol returnValue = TagProtocol.Unknown;
		returnValue.setId(id);
		return returnValue;
	}

}
