/**
 * 
 */
package at.fabianachammer.nsend.aspect;

import at.fabianachammer.nsend.pdu.EtherType;
import at.fabianachammer.nsend.pdu.EthernetFrame;
import at.fabianachammer.nsend.pdu.ProtocolDataUnit;

/**
 * @author fabian
 * 
 */
public aspect Validation {

	/**
	 * Maximum size of an Ethernet frame in bytes.
	 */
	private static final int ETHERNET_FRAME_MAX_SIZE = 1518;

	/**
	 * Size of an Ethernet header.
	 */
	private static final int ETHERNET_FRAME_HEADER_SIZE = 14;

	/**
	 * When calling setters of MAC addresses.
	 * 
	 * @param b
	 *            array that holds the new MAC address
	 */
	public pointcut setMacAddress(Byte[] b) : execution(* EthernetFrame.set*MacAddress(..)) && args(b);

	/**
	 * When calling the setter of the data.
	 * 
	 * @param data
	 *            the PDU that holds the data for the Ethernet frame
	 * @param ef
	 *            Ethernet frame that gets changed
	 */
	public pointcut setData(ProtocolDataUnit data): execution(* EthernetFrame.setData(..)) && args(data);

	/**
	 * When calling the setter of the Ether type.
	 * 
	 * @param et
	 *            new Ether type that gets set
	 */
	public pointcut setEtherType(EtherType et):execution(* EthernetFrame.setEtherType(..)) && args(et);

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
	 */
	before(EtherType et):setEtherType(et){
		throwNull(et, "Ether type");
	}

	/**
	 * Throws an IllegalArgumentException, if the actual value is greater than a
	 * specified maximum value.
	 * 
	 * @param actual
	 *            actual value to check
	 * @param max
	 *            maximum value that the actual value can have
	 * @param argumentName
	 *            name of the argument that gets checked (and printed out in the
	 *            error message)
	 */
	private <T> void throwGreaterThan(Comparable<T> actual, T max,
			String argumentName) {
		if (actual.compareTo(max) > 0) {
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
	 *            error message)
	 */
	private void throwNonEqual(Object argument, Object equalTo,
			String argumentName) {
		if (!argument.equals(equalTo)) {
			throw new IllegalArgumentException(argumentName
					+ " must be exactly " + equalTo.toString());
		}
	}

	/**
	 * Throws an NullPointerException, if the argument is null.
	 * 
	 * @param argument
	 *            value to check
	 * @param argumentName
	 *            name of the argument that gets checked (and printed out in the
	 *            error message)
	 */
	private void throwNull(Object obj, String argumentName) {
		if (obj == null) {
			throw new NullPointerException(argumentName
					+ "must not be null");
		}
	}
}
