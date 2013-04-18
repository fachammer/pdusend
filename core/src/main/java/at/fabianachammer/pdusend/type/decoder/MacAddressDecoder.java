/**
 * 
 */
package at.fabianachammer.pdusend.type.decoder;

import at.fabianachammer.pdusend.type.MacAddress;

/**
 * Implements the data unit encoding for MAC addresses.
 * 
 * @author fabian
 * 
 */
public class MacAddressDecoder implements
		DataUnitDecoder<MacAddress> {

	@Override
	public final MacAddress decode(final byte... data) {
		return new MacAddress(data);
	}
}
