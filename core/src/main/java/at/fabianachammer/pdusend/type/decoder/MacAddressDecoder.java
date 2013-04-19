/**
 * 
 */
package at.fabianachammer.pdusend.type.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
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

	/**
	 * maximum size of an input array.
	 */
	private static final int MAX_SIZE = 6;

	@Override
	public final MacAddress decode(
			@Size(max = MAX_SIZE) @NotNull final byte... data) {
		switch (data.length) {
		case 1:
			return new MacAddress(new byte[] { 0, 0, 0, 0, 0, data[0] });
		case 2:
			return new MacAddress(new byte[] {
					0, 0, 0, 0, data[0], data[1] });
		case 3:
			return new MacAddress(new byte[] {
					0, 0, 0, data[0], data[1], data[2] });
		case 4:
			return new MacAddress(new byte[] {
					0, 0, data[0], data[1], data[2], data[3] });
		case 5:
			return new MacAddress(new byte[] {
					0, data[0], data[1], data[2], data[3], data[4] });
		case 6:
			return new MacAddress(new byte[] {
					data[0], data[1], data[2], data[3], data[4],
					data[5] });
		default:
			return new MacAddress();
		}
	}
}
