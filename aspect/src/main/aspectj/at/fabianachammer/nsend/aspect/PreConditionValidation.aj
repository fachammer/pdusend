/**
 * 
 */
package at.fabianachammer.nsend.aspect;

import static at.fabianachammer.nsend.util.Validation.throwNotBetween;
import static at.fabianachammer.nsend.util.Validation.throwNotEqual;
import static at.fabianachammer.nsend.util.Validation.throwNull;

import at.fabianachammer.nsend.pdu.EtherType;
import at.fabianachammer.nsend.pdu.EthernetFrame;
import at.fabianachammer.nsend.pdu.VlanTag;

/**
 * @author fabian
 * 
 */
public aspect PreConditionValidation {

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
		throwNotEqual(b.length, 6, argumentName);
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
	 * @param vid
	 *            vland identifier that gets checked
	 */
	before(Short vid):setVlanIdentifier(vid){
		throwNotBetween(
				vid, MIN_VLAN_IDETIFIER, MAX_VLAN_IDENTIFIER, "vlan identifier");
	}
}