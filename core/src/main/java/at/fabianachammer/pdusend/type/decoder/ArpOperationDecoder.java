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
	private static final int	MAX_DATA_SIZE	= 2;

	/**
	 * minimum size of byte array input.
	 */
	private static final int	MIN_DATA_SIZE	= 1;

	@Override
	public final ArpOperation decode(@Size(min = MIN_DATA_SIZE,
			max = MAX_DATA_SIZE) @NotNull final byte... data) {
		short id = getIdFromData(data);

		for (ArpOperation ao : ArpOperation.VALUES) {
			if (ao.getId() == id) {
				return ao;
			}
		}

		ArpOperation returnValue = new ArpOperation(id);
		return returnValue;
	}

	/**
	 * returns the ID of the ARP operation from the given data.
	 * 
	 * @param data
	 *            data that contains the whole ARP operation (ID)
	 * @return ID of the ARP operation
	 */
	private short getIdFromData(final byte... data) {
		if (data.length == 1) {
			return data[0];
		} else {
			return BitOperator.merge(data[1], data[0]);
		}
	}
}