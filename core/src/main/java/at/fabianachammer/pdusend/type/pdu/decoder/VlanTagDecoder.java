/**
 * 
 */
package at.fabianachammer.pdusend.type.pdu.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.TagProtocol;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.TagProtocolDecoder;
import at.fabianachammer.pdusend.type.pdu.VlanTag;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Implements the decoding of VLAN tags.
 * 
 * @author fabian
 * 
 */
@Guarded
public class VlanTagDecoder implements DataUnitDecoder<VlanTag> {

	@Override
	public final VlanTag decode(@Size(min = VlanTag.SIZE,
			max = VlanTag.SIZE) @NotNull final byte... data) {
		VlanTag vlanTag = new VlanTag();
		byte[] tagProtocolBytes = new byte[TagProtocol.SIZE];
		System.arraycopy(data, 0, tagProtocolBytes, 0,
				tagProtocolBytes.length);
		TagProtocol tagProtocol =
				new TagProtocolDecoder().decode(tagProtocolBytes);
		vlanTag.setTagProtocol(tagProtocol);

		byte priorityCodePoint = (byte) (data[2] & 0b11100000);
		vlanTag.setPriorityCodePoint(priorityCodePoint);

		byte canonicalFormatBit = (byte) (data[2] & 0b00010000);
		boolean isCanonicalFormat = true;
		if (canonicalFormatBit == 1) {
			isCanonicalFormat = false;
		}
		vlanTag.setCanonicalFormat(isCanonicalFormat);

		short vlanId =
				BitOperator.merge(data[3], (byte) (data[2] & 0b1111));
		vlanTag.setVlanIdentifier(vlanId);

		return vlanTag;
	}

}
