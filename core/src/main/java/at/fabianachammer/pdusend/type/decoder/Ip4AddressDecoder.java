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

	@Override
	public final Ip4Address decode(@NotNull @Size(
			max = Ip4Address.SIZE) final byte... data) {

		byte[] ip4Address = new byte[Ip4Address.SIZE];
		int j = Math.max(0, data.length
				- ip4Address.length);
		for (int i = ip4Address.length
				- data.length + j; i < ip4Address.length; i++) {
			ip4Address[i] = data[j++];
		}

		int mergeResult =
				BitOperator.merge(ip4Address[3], ip4Address[2],
						ip4Address[1], ip4Address[0]);

		return new Ip4Address(mergeResult);
	}
}
