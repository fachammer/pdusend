package at.fabianachammer.pdusend.type.pdu.decoder;

import java.util.zip.CRC32;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.MacAddress;
import at.fabianachammer.pdusend.type.TagProtocol;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.EtherTypeDecoder;
import at.fabianachammer.pdusend.type.decoder.TagProtocolDecoder;
import at.fabianachammer.pdusend.type.pdu.EthernetFrame;
import at.fabianachammer.pdusend.type.pdu.VlanTag;

/**
 * Implements the decoding of Ethernet frames.
 * 
 * @author fabian
 * 
 */
@Guarded
public class EthernetFrameDecoder implements
		DataUnitDecoder<EthernetFrame> {

	/**
	 * maximum size of byte array input.
	 */
	private static final int MAX_SIZE = 1522;

	/**
	 * minimum size of byte array input.
	 */
	private static final int MIN_SIZE = 64;

	@Override
	public final EthernetFrame decode(@Size(min = MIN_SIZE,
			max = MAX_SIZE) @NotNull final byte... data) {

		EthernetFrame ethernetFrame = new EthernetFrame();

		byte[] destinationMacBytes = new byte[MacAddress.SIZE];
		byte[] sourceMacBytes = new byte[MacAddress.SIZE];
		byte[] etherTypeBytes = new byte[EtherType.SIZE];
		byte[] vlanTagBytes;
		byte[] embeddedDataBytes;
		byte[] paddingBytes;
		byte[] checksumBytes = new byte[EthernetFrame.CRC_SIZE];
		int counter = 0;

		System.arraycopy(data, counter, destinationMacBytes, 0,
				destinationMacBytes.length);
		ethernetFrame.setDestinationMacAddress(new MacAddress(destinationMacBytes));
		counter += destinationMacBytes.length;
		
		System.arraycopy(data, counter, sourceMacBytes, 0,
				sourceMacBytes.length);
		ethernetFrame.setSourceMacAddress(new MacAddress(sourceMacBytes));
		counter += sourceMacBytes.length;
		
		System.arraycopy(data, counter, etherTypeBytes, 0,
				etherTypeBytes.length);

		TagProtocol tagProtocol =
				new TagProtocolDecoder().decode(etherTypeBytes);

		if (tagProtocol != TagProtocol.Unknown) {
			vlanTagBytes = new byte[VlanTag.SIZE];
			System.arraycopy(data, counter, vlanTagBytes, 0,
					vlanTagBytes.length);
			counter += vlanTagBytes.length;

			VlanTag vlanTag =
					new VlanTagDecoder().decode(vlanTagBytes);
			ethernetFrame.setVlanTag(vlanTag);

			System.arraycopy(data, counter, etherTypeBytes, 0,
					etherTypeBytes.length);
		}
		
		EtherType etherType =
				new EtherTypeDecoder().decode(etherTypeBytes);
		ethernetFrame.setEtherType(etherType);
		counter += etherTypeBytes.length;

		embeddedDataBytes = new byte[data.length
				- counter - checksumBytes.length];
		System.arraycopy(data, counter, embeddedDataBytes, 0,
				embeddedDataBytes.length);
		DataUnit embeddedData =
				etherType.getProtocolDecoder().decode(
						embeddedDataBytes);
		ethernetFrame.setData(embeddedData);
		counter += embeddedData.size();

		paddingBytes = new byte[Math.max(EthernetFrame.MIN_SIZE
				- EthernetFrame.CRC_SIZE - counter, 0)];
		System.arraycopy(data, counter, paddingBytes, 0,
				paddingBytes.length);
		ethernetFrame.setPadding(paddingBytes);
		counter += paddingBytes.length;

		System.arraycopy(data, counter, checksumBytes, 0,
				checksumBytes.length);
		ethernetFrame.setChecksum(checksumBytes);

		return ethernetFrame;
	}
}
