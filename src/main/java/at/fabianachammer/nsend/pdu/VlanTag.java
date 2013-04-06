package at.fabianachammer.nsend.pdu;

import at.fabianachammer.nsend.pdu.util.BitOperator;
import at.fabianachammer.nsend.pdu.util.PrimitiveArrayList;

/**
 * Represents a VLAN Tag which contains information regarding VLANs inside a
 * Ethernet frame.
 * 
 * @author fabian
 * 
 */
public final class VlanTag implements ProtocolDataUnit {

	/**
	 * Standard tag protocol.
	 */
	private static final TagProtocol DEFAULT_TAG_PROTOCOL =
			TagProtocol.IEEE_802_1Q;	

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
	public Byte[] toBytes() {

		PrimitiveArrayList<Byte> byteList = new PrimitiveArrayList<Byte>();

		byteList.addArray(BitOperator.split(tagProtocol.getIdentifier()));

		byte canonicalBit = 1;

		if (canonicalFormat) {
			canonicalBit = 0;
		}

		byte thirdHighHalfByte = (byte) ((priorityCodePoint << 1)
				+ (canonicalBit) << (Byte.SIZE / 2));

		Byte[] vid = BitOperator.split(vlanIdentifier);

		byteList.add((byte) (thirdHighHalfByte + vid[0]));
		byteList.add(vid[1]);

		return byteList.toArray(new Byte[0]);
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
	public void setPriorityCodePoint(final byte priorityCodePoint) {
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
	public void setVlanIdentifier(final short vlanIdentifier) {
		this.vlanIdentifier = vlanIdentifier;
	}
}
