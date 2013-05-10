package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.ServiceTypeRFC791.Precedence;

/**
 * @author fabian
 *
 */
public class PrecedenceTest {

	@Test
	public void getByServiceTypeByteWithAllBitsSetInputReturnsNetworkControlPrecedence() {
		Precedence expected = Precedence.NetworkControl;
		byte input = (byte) 0xff;
		
		Precedence actual = Precedence.getByServiceTypeByte(input);
		
		assertEquals(expected, actual);
	}

}
