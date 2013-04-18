package at.fabianachammer.pdusend.type.pdu;

import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.TagProtocol;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.VlanTagDecoder;
import at.fabianachammer.pdusend.util.BitOperator;
import at.fabianachammer.pdusend.util.ByteArrayBuilder;

/**
 * Represents a VLAN Tag which contains information regarding VLANs inside a
 * Ethernet frame.
 * 
 * @author fabian
 * 
 */
@Guarded
public final class VlanTag implements ProtocolDataUnit {

	/**
	 * Standard tag protocol.
	 */
	private static final TagProtocol DEFAULT_TAG_PROTOCOL =
			TagProtocol.IEEE_802_1Q;

	/**
	 * Specifies the minimum priority code point a VLAN tag can have.
	 */
	private static final int MIN_PRIORITY_CODE_POINT = 0;

	/**
	 * Specifies the maximum priority code point a VLAN tag can have.
	 */
	private static final int MAX_PRIORITY_CODE_POINT = 7;

	/**
	 * Specifies the minimum VLAN identifier a VLAN tag can have.
	 */
	private static final int MIN_VLAN_IDENTIFIER = 0;

	/**
	 * Specifies the maximum VLAN identifier a VLAN tag can have.
	 */
	private static final int MAX_VLAN_IDENTIFIER = 4095;

	/**
	 * Specifies the decoder used for decoding data units of this type.
	 */
	private static final DataUnitDecoder<VlanTag> DECODER =
			new VlanTagDecoder();

	/**
	 * Tag Protocol. Describes, which protocol is used for Tagging (default is
	 * 802.1Q).
	 */
	private TagProtocol tagProtocol = DEFAULT_TAG_PROTOCOL;

	/**
	 * Priority Code Point (PCP). Describes user priority information.
	 */
	@Min(MIN_PRIORITY_CODE_POINT)
	@Max(MAX_PRIORITY_CODE_POINT)
	private byte priorityCodePoint = MIN_PRIORITY_CODE_POINT;

	/**
	 * Canonical Format Indicator (CFI). Describes whether the MAC adresses are
	 * in canonical or non-canonical order.
	 */
	private boolean canonicalFormat = true;

	/**
	 * VLAN Identifier (VID). Identifies the VLAN, which this VLAN-Tag is for.
	 */
	@Min(MIN_VLAN_IDENTIFIER)
	@Max(MAX_VLAN_IDENTIFIER)
	private short vlanIdentifier = MIN_VLAN_IDENTIFIER;

	@Override
	public DataUnitDecoder<VlanTag> getDecoder() {
		return DECODER;
	}

	@Override
	public byte[] encode() {
		ByteArrayBuilder bab = new ByteArrayBuilder();

		bab.append(BitOperator.split(tagProtocol.getId()));

		byte canonicalBit = 1;

		if (canonicalFormat) {
			canonicalBit = 0;
		}

		byte thirdHighHalfByte = (byte) ((priorityCodePoint << 1)
				+ (canonicalBit) << (Byte.SIZE / 2));

		byte[] vid = BitOperator.split(vlanIdentifier);

		bab.append((byte) (thirdHighHalfByte + vid[0]));
		bab.append((byte) vid[1]);

		return bab.toArray();
	}

	/**
	 * @return the tagProtocol
	 */
	public TagProtocol getTagProtocol() {
		return tagProtocol;
	}

	/**
	 * @param tagProtocol
	 *            the tagProtocol to set
	 */
	public void setTagProtocol(final TagProtocol tagProtocol) {
		this.tagProtocol = tagProtocol;
	}

	/**
	 * @return the priorityCodePoint
	 */
	public byte getPriorityCodePoint() {
		return priorityCodePoint;
	}

	/**
	 * @param priorityCodePoint
	 *            the priorityCodePoint to set
	 */
	public void setPriorityCodePoint(
			@AssertFieldConstraints final byte priorityCodePoint) {
		this.priorityCodePoint = priorityCodePoint;
	}

	/**
	 * @return the canonicalFormat
	 */
	public boolean isCanonicalFormat() {
		return canonicalFormat;
	}

	/**
	 * @param canonicalFormat
	 *            the canonicalFormat to set
	 */
	public void setCanonicalFormat(final boolean canonicalFormat) {
		this.canonicalFormat = canonicalFormat;
	}

	/**
	 * @return the vlanIdentifier
	 */
	public short getVlanIdentifier() {
		return vlanIdentifier;
	}

	/**
	 * @param vlanIdentifier
	 *            the vlanIdentifier to set
	 */
	public void setVlanIdentifier(
			@AssertFieldConstraints final short vlanIdentifier) {
		this.vlanIdentifier = vlanIdentifier;
	}
}
