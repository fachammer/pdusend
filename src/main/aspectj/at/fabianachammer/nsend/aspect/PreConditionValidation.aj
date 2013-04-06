/**
 * 
 */
package at.fabianachammer.nsend.aspect;

import at.fabianachammer.nsend.pdu.EtherType;
import at.fabianachammer.nsend.pdu.EthernetFrame;
import at.fabianachammer.nsend.pdu.ProtocolDataUnit;
import at.fabianachammer.nsend.pdu.VlanTag;

/**
 * @author fabian
 * 
 */
public aspect PreConditionValidation {

	/**
	 * Maximum size of an Ethernet frame in bytes.
	 */
	private static final int ETHERNET_FRAME_MAX_SIZE = 1518;

	/**
	 * Size of an Ethernet header.
	 */
	private static final int ETHERNET_FRAME_HEADER_SIZE = 14;

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
	 * When executing setters of MAC addresses.
	 * 
	 * @param b
	 *            array that holds the new MAC address
	 */
	public pointcut setMacAddress(Byte[] b) : execution(* EthernetFrame.set*MacAddress(..)) && args(b);

	/**
	 * When executing the setter of the data.
	 * 
	 * @param data
	 *            the PDU that holds the data for the Ethernet frame
	 * @param ef
	 *            Ethernet frame that gets changed
	 */
	public pointcut setData(ProtocolDataUnit data): execution(* EthernetFrame.setData(..)) && args(data);

	/**
	 * When executing the setter of the Ether type.
	 * 
	 * @param et
	 *            new Ether type that gets set
	 */
	public pointcut setEtherType(EtherType et):execution(* EthernetFrame.setEtherType(..)) && args(et);

	/**
	 * When executing the setter of the priority code point.
	 * 
	 * @param b
	 *            priority code point that gets set
	 */
	public pointcut setPriorityCodePoint(Byte b):execution(* VlanTag.setPriorityCodePoint(..)) && args(b);

	/**
	 * When executing the setter of the vlan identifier.
	 * 
	 * @param vid
	 *            vlan identifier that gets set
	 */
	public pointcut setVlanIdentifier(Short vid):execution(* VlanTag.setVlanIdentifier(..)) && args(vid);

	/**
	 * Checks, whether the set byte array fulfills the following criteria:
	 * <ul>
	 * <li>not null</li>
	 * <li>length is equal to 6</li>
	 * </ul>
	 * 
	 * @param b
	 *            byte array that gets checked
	 */
	before(Byte[] b):setMacAddress(b){
		final String argumentName = "MAC address";
		throwNull(b, argumentName);
		throwNonEqual(b.length, 6, argumentName);
	}

	/**
	 * Checks, whether the length of the data to be set is lower than the
	 * Ethernet maximum, if the data is not null.
	 * 
	 * @param data
	 *            data that gets checked
	 */
	before(ProtocolDataUnit data):setData(data){
		if (data != null) {
			throwGreaterThan(data.toBytes().length, ETHERNET_FRAME_MAX_SIZE
					- ETHERNET_FRAME_HEADER_SIZE, "data");
		}
	}

	/**
	 * Checks, whether the Ether type to be set is not null
	 * 
	 * @param et
	 *            ethernet type that gets checked
	 */
	before(EtherType et):setEtherType(et){
		throwNull(et, "Ether type");
	}

	/**
	 * Checks, whether the set priority code point lies between the minimum and
	 * maximum priority code point.
	 * 
	 * @param b
	 *            priority code point that gets checked
	 */
	before(Byte b):setPriorityCodePoint(b){
		throwNotBetween(
				b, MIN_PRIORITY_CODE_POINT, MAX_PRIORITY_CODE_POINT,
				"priority code point");
	}

	/**
	 * Checks, whether the set vlan identifier lies between tha minimum and
	 * maximum vlan identifier.
	 * 
	 * @param vid vland identifier that gets checked
	 */
	before(Short vid):setVlanIdentifier(vid){
		throwNotBetween(vid, MIN_VLAN_IDETIFIER, MAX_VLAN_IDENTIFIER, "vlan identifier");
	}

	/**
	 * Throws an IllegalArgumentException, if the actual value is greater than a
	 * specified maximum value.
	 * 
	 * @param argument
	 *            actual value to check
	 * @param max
	 *            maximum value that the actual value can have
	 * @param argumentName
	 *            name of the argument that gets checked (and printed out in the
	 *            exception message)
	 */
	private <T> void throwGreaterThan(Comparable<T> argument, T max,
			String argumentName) {
		if (argument.compareTo(max) > 0) {
			throw new IllegalArgumentException(argumentName
					+ " must not be bigger than " + max.toString());
		}
	}

	/**
	 * Throws an IllegalArgumentException, if the argument value is not equal to
	 * a specified value.
	 * 
	 * @param argument
	 *            value to check
	 * @param equalTo
	 *            value the argument has to be equal to
	 * @param argumentName
	 *            name of the argument that gets checked (and printed out in the
	 *            exception message)
	 */
	private void throwNonEqual(Object argument, Object equalTo,
			String argumentName) {
		if (!argument.equals(equalTo)) {
			throw new IllegalArgumentException(argumentName
					+ " must be exactly " + equalTo.toString());
		}
	}

	/**
	 * Throws a NullPointerException, if the argument is null.
	 * 
	 * @param argument
	 *            value to check
	 * @param argumentName
	 *            name of the argument that gets checked (and printed out in the
	 *            exception message)
	 */
	private void throwNull(Object argument, String argumentName) {
		if (argument == null) {
			throw new NullPointerException(argumentName
					+ "must not be null");
		}
	}

	/**
	 * Throws an IllegalArgumentException, if the argument doesn't lie between
	 * the min and max value.
	 * 
	 * @param argument
	 *            object the be checked
	 * @param min
	 *            minimum value of the object
	 * @param max
	 *            maximum value of the object
	 * @param argumentName
	 *            name of the argument that gets checked (and printed out in the
	 *            exception message)
	 */
	private <T> void throwNotBetween(Comparable<T> argument, T min, T max,
			String argumentName) {
		if (argument.compareTo(min) < 0
				|| argument.compareTo(max) > 0) {
			throw new IllegalArgumentException(argumentName
					+ " must be between " + min + " and " + max);
		}
	}
}
