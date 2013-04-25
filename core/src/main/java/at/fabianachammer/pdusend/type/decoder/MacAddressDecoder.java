/**
 * 
 */
package at.fabianachammer.pdusend.type.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.MacAddress;

/**
 * Implements the data unit encoding for MAC addresses.
 * 
 * @author fabian
 * 
 */
@Guarded
public class MacAddressDecoder implements DataUnitDecoder<MacAddress> {

	@Override
	public final MacAddress decode(@NotNull final byte... data) {

		byte[] mac = new byte[MacAddress.SIZE];
		int j = Math.max(0, data.length
				- mac.length);
		for (int i = mac.length
				- data.length + j; i < mac.length; i++) {
			mac[i] = data[j++];
		}

		return new MacAddress(mac);
	}
}
