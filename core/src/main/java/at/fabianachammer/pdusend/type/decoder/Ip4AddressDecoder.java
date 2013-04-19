/**
 * 
 */
package at.fabianachammer.pdusend.type.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.Ip4Address;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Implements data unit encoding for IPv4 addresses.
 * 
 * @author fabian
 * 
 */
@Guarded
public class Ip4AddressDecoder implements DataUnitDecoder<Ip4Address> {

	/**
	 * maximum array size for input.
	 */
	private static final int MAX_SIZE = 4;

	@Override
	public final Ip4Address decode(
			@Size(max = MAX_SIZE) @NotNull final byte... data) {

		switch (data.length) {
		case 1:
			return new Ip4Address(data[0]);
		case 2:
			return new Ip4Address(BitOperator.merge(data[1], data[0]));
		case 3:
			return new Ip4Address(BitOperator.merge(data[2], data[1],
					data[0], (byte) 0));
		case 4:
			return new Ip4Address(BitOperator.merge(data[3], data[2],
					data[1], data[0]));
		default:
			return new Ip4Address();
		}
	}
}
