package at.fabianachammer.pdusend.type.pdu.decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.pdu.EthernetFrame;
import at.fabianachammer.pdusend.type.pdu.RawDataUnit;
import at.fabianachammer.pdusend.type.pdu.VlanTag;

/**
 * @author fabian
 * 
 */
public class EthernetFrameDecoderTest {

	@Test
	public final void decodeWithValidByteArrayWithDefaultEthernetFrameValuesWithNoVlanTagReturnsDefaultEthernetFrameAndSetData() {
		EthernetFrameDecoder decoder = new EthernetFrameDecoder();
		EthernetFrame expected = new EthernetFrame();
		expected.setEmbeddedData(new RawDataUnit(new byte[46]));
		expected.setPadding(new byte[0]);
		expected.setChecksum(new byte[4]);
		expected.setEtherType(EtherType.UNKNOWN);
		byte[] input =
				{
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00 };

		EthernetFrame actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test
	public final void decodeWithValidByteArrayWithDefaultEthernetFrameValuesWithIEEE8021QVlanTagReturnsEthernetFrameWithVlanTagAndSetData() {
		EthernetFrameDecoder decoder = new EthernetFrameDecoder();
		EthernetFrame expected = new EthernetFrame();
		VlanTag expectedVlanTag = new VlanTag();
		expected.setVlanTag(expectedVlanTag);
		expected.setEmbeddedData(new RawDataUnit(new byte[42]));
		expected.setPadding(new byte[0]);
		expected.setChecksum(new byte[4]);
		expected.setEtherType(EtherType.UNKNOWN);
		byte[] input =
				{
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x81,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00 };

		EthernetFrame actual = decoder.decode(input);

		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void decodeWithIllegalByteArraySizeThrowsIllegalArgumentException() {
		EthernetFrameDecoder decoder = new EthernetFrameDecoder();
		byte[] illegalSizeInput = new byte[1523];

		decoder.decode(illegalSizeInput);
	}

	@Test(expected = NullPointerException.class)
	public final void decodeWithNullThrowsNullPointerException() {
		new EthernetFrameDecoder().decode(null);
	}
}
