/**
 * 
 */
package at.fabianachammer.pdusend.type.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.TagProtocol;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Implements decoding for tag protocols.
 * 
 * @author fabian
 * 
 */
@Guarded
public class TagProtocolDecoder implements
		DataUnitDecoder<TagProtocol> {

	/**
	 * maximum size for input arrays.
	 */
	private static final int MAX_SIZE = 2;

	@Override
	public final TagProtocol decode(
			@Size(max = MAX_SIZE) @NotNull final byte... data) {
		short id = 0;
		switch (data.length) {
		case 1:
			id = data[0];
			break;
		case 2:
			id = BitOperator.merge(data[1], data[0]);
			break;
		default:
			return TagProtocol.Unknown;
		}

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
