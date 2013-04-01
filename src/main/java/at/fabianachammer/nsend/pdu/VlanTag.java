package at.fabianachammer.nsend.pdu;

import at.fabianachammer.nsend.pdu.util.BitOperator;

/**
 * Represents a VLAN Tag which contains information regarding VLANs inside a
 * Ethernet frame.
 * 
 * @author fabian
 * 
 */
public class VlanTag implements ProtocolDataUnit {

	/**
	 * Standard tag protocol.
	 */
	private static final TagProtocol DEFAULT_TAG_PROTOCOL =
			TagProtocol.IEEE_802_1Q;

	/**
	 * Size of a VLAN Tag in Bytes.
	 */
	private static final int VLAN_TAG_SIZE = 4;

	/**
	 * Maximum value of the priority code point.
	 */
	private static final byte MAX_PRIORITY_CODE_POINT = 7;

	/**
	 * Minimum value of the priority code point.
	 */
	private static final byte MIN_PRIORITY_CODE_POINT = 0;

	/**
	 * Maximum value of the VLAN identifier.
	 */
	private static final short MAX_VLAN_IDENTIFIER = 4095;

	/**
	 * Minimum value of the VLAN identifier.
	 */
	private static final short MIN_VLAN_IDETIFIER = 0;

	/**
	 * Tag Protocol. Describes, which protocol is used for Tagging (default is
	 * 802.1Q).
	 */
	private TagProtocol tagProtocol = DEFAULT_TAG_PROTOCOL;

	/**
	 * Priority Code Point (PCP). Describes user priority information.
	 */
	private byte priorityCodePoint;

	/**
	 * Canonical Format Indicator (CFI). Describes whether the MAC adresses are
	 * in canonical or non-canonical order.
	 */
	private boolean canonicalFormat = true;

	/**
	 * VLAN Identifier (VID). Identifies the VLAN, which this VLAN-Tag is for.
	 */
	private short vlanIdentifier;

	@Override
	public final byte[] toBytes() {
		byte[] vlanTag = new byte[VLAN_TAG_SIZE];
		byte[] bytes = BitOperator.split(tagProtocol.getIdentifier());
		vlanTag[0] = bytes[1];
		vlanTag[1] = bytes[0];

		byte thirdHighHalfByte =
				(byte) ((priorityCodePoint << 1) + (canonicalFormat ? 0 : 1) << (Byte.SIZE / 2));

		byte[] vid = BitOperator.split(vlanIdentifier);

		vlanTag[2] = (byte) (thirdHighHalfByte + vid[1]);
		vlanTag[3] = vid[0];

		return vlanTag;
	}

	/**
	 * @return the tagProtocol
	 */
	public final TagProtocol getTagProtocol() {
		return tagProtocol;
	}

	/**
	 * @param tagProtocol
	 *            the tagProtocol to set
	 */
	public final void setTagProtocol(final TagProtocol tagProtocol) {
		this.tagProtocol = tagProtocol;
	}

	/**
	 * @return the priorityCodePoint
	 */
	public final byte getPriorityCodePoint() {
		return priorityCodePoint;
	}

	/**
	 * @param priorityCodePoint
	 *            the priorityCodePoint to set
	 */
	public final void setPriorityCodePoint(final byte priorityCodePoint) {
		if (priorityCodePoint < MIN_PRIORITY_CODE_POINT
				|| priorityCodePoint > MAX_PRIORITY_CODE_POINT) {
			throw new IllegalArgumentException(
					"the priority code point may only have values between "
							+ MIN_PRIORITY_CODE_POINT + " and "
							+ MAX_PRIORITY_CODE_POINT);
		}
		this.priorityCodePoint = priorityCodePoint;
	}

	/**
	 * @return the canonicalFormat
	 */
	public final boolean isCanonicalFormat() {
		return canonicalFormat;
	}

	/**
	 * @param canonicalFormat
	 *            the canonicalFormat to set
	 */
	public final void setCanonicalFormat(final boolean canonicalFormat) {
		this.canonicalFormat = canonicalFormat;
	}

	/**
	 * @return the vlanIdentifier
	 */
	public final short getVlanIdentifier() {
		return vlanIdentifier;
	}

	/**
	 * @param vlanIdentifier
	 *            the vlanIdentifier to set
	 */
	public final void setVlanIdentifier(final short vlanIdentifier) {
		if (vlanIdentifier < MIN_VLAN_IDETIFIER
				|| vlanIdentifier > MAX_VLAN_IDENTIFIER) {
			throw new IllegalArgumentException(
					"the vlan identifier may only have values between "
							+ MIN_VLAN_IDETIFIER + " and "
							+ MAX_VLAN_IDENTIFIER);
		}

		this.vlanIdentifier = vlanIdentifier;
	}
}