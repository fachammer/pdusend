/**
 * 
 */
package at.fabianachammer.pdusend.type.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.ArpOperation;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Implements data unit encoding for ARP operations.
 * 
 * @author fabian
 * 
 */
@Guarded
public class ArpOperationDecoder implements
		DataUnitDecoder<ArpOperation> {

	/**
	 * maximum size byte array input.
	 */
	private static final int MAX_DATA_SIZE = 2;

	/**
	 * minimum size of byte array input.
	 */
	private static final int MIN_DATA_SIZE = 1;

	@Override
	public final ArpOperation decode(
			@Size(min = MIN_DATA_SIZE, max = MAX_DATA_SIZE) @NotNull final byte... data) {

		short id = 0;
		if (data.length == 1) {
			id = data[0];
		} else {
			id = BitOperator.merge(data[1], data[0]);
		}

		for (ArpOperation ao : ArpOperation.values()) {
			if (ao.getId() == id) {
				return ao;
			}
		}

		ArpOperation returnValue = ArpOperation.Unknown;
		returnValue.setId(id);
		return returnValue;
	}
}