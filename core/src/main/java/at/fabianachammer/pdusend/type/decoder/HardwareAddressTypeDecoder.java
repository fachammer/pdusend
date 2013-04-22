/**
 * 
 */
package at.fabianachammer.pdusend.type.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.HardwareAddressType;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Implements decoding for hardware address types.
 * 
 * @author fabian
 * 
 */
@Guarded
public class HardwareAddressTypeDecoder implements
		DataUnitDecoder<HardwareAddressType> {

	/**
	 * minimum byte array size of input.
	 */
	private static final int MIN_SIZE = 1;

	/**
	 * maximum byte array size of input.
	 */
	private static final int MAX_SIZE = 2;

	@Override
	public final HardwareAddressType decode(@Size(min = MIN_SIZE,
			max = MAX_SIZE) @NotNull final byte... data) {
		short id = 0;
		if (data.length == 1) {
			id = data[0];
		} else {
			id = BitOperator.merge(data[1], data[0]);
		}

		for (HardwareAddressType hat : HardwareAddressType.VALUES) {
			if (hat.getId() == id) {
				return hat;
			}
		}

		HardwareAddressType returnValue = new HardwareAddressType(id);
		return returnValue;
	}

}
