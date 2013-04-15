/**
 * 
 */
package at.fabianachammer.pdusend.aspect;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.pdu.ArpSegment;

/**
 * @author fabian
 * 
 */
public class ArpSegmentValidationTest {

	@Test(expected = NullPointerException.class)
	public final void testSetNullHardwareTypeOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setHardwareType(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullProtocolTypeOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setProtocolType(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullOperationOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setOperation(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullSenderHardwareAddressOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setSenderHardwareAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullSenderProtocolAddressOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setSenderProtocolAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullTargetHardwareAddressOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setTargetHardwareAddress(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullTargetProtocolAddressOnArpSegmentThrowsNullPointerException() {
		ArpSegment arpSegment = new ArpSegment();

		arpSegment.setTargetProtocolAddress(null);
	}

}
