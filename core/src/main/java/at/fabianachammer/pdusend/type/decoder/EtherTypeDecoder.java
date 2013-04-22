/**
 * 
 */
package at.fabianachammer.pdusend.type.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Implements data unit encoding for EtherTypes.
 * 
 * @author fabian
 * 
 */
@Guarded
public class EtherTypeDecoder implements DataUnitDecoder<EtherType> {

	/**
	 * exact size an input array must have.
	 */
	private static final int SIZE = 2;

	@Override
	public final EtherType decode(
			@Size(min = SIZE, max = SIZE) @NotNull final byte... data) {
		final short id = BitOperator.merge(data[1], data[0]);

		for (EtherType et : EtherType.VALUES) {
			if (et.getId() == id) {
				return et;
			}
		}

		EtherType returnValue = new EtherType(id);
		return returnValue;
	}
}
