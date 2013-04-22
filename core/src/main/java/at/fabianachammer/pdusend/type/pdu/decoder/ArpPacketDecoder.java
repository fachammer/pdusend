package at.fabianachammer.pdusend.type.pdu.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.ArpOperation;
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.HardwareAddressType;
import at.fabianachammer.pdusend.type.decoder.ArpOperationDecoder;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.EtherTypeDecoder;
import at.fabianachammer.pdusend.type.decoder.HardwareAddressTypeDecoder;
import at.fabianachammer.pdusend.type.decoder.Ip4AddressDecoder;
import at.fabianachammer.pdusend.type.decoder.MacAddressDecoder;
import at.fabianachammer.pdusend.type.pdu.ArpPacket;

/**
 * Implements decoding for ARP segments.
 * 
 * @author fabian
 * 
 */
@Guarded
public class ArpPacketDecoder implements DataUnitDecoder<ArpPacket> {

	/**
	 * minimum size of the input array.
	 */
	private static final int MIN_SIZE = 28;

	@Override
	public final ArpPacket decode(
			@NotNull @Size(min = MIN_SIZE) final byte... data) {

		int counter = 0;

		byte[] hardwareAddressTypeBytes =
				new byte[HardwareAddressType.SIZE];
		byte[] protocolAddressTypeBytes = new byte[EtherType.SIZE];

		System.arraycopy(data, counter, hardwareAddressTypeBytes, 0,
				hardwareAddressTypeBytes.length);
		counter += hardwareAddressTypeBytes.length;
		System.arraycopy(data, counter, protocolAddressTypeBytes, 0,
				protocolAddressTypeBytes.length);
		counter += protocolAddressTypeBytes.length;

		byte hardwareAddressLength = data[counter++];
		byte protocolAddressLength = data[counter++];

		byte[] arpOperationBytes = new byte[ArpOperation.SIZE];
		System.arraycopy(data, counter, arpOperationBytes, 0,
				arpOperationBytes.length);
		counter += arpOperationBytes.length;

		byte[] senderHardwareAddressBytes =
				new byte[hardwareAddressLength];
		byte[] senderProtocolAddressBytes =
				new byte[protocolAddressLength];
		byte[] targetHardwareAddressBytes =
				new byte[hardwareAddressLength];
		byte[] targetProtocolAddressBytes =
				new byte[protocolAddressLength];

		System.arraycopy(data, counter, senderHardwareAddressBytes,
				0, senderHardwareAddressBytes.length);
		counter += senderHardwareAddressBytes.length;
		System.arraycopy(data, counter, senderProtocolAddressBytes,
				0, senderProtocolAddressBytes.length);
		counter += senderProtocolAddressBytes.length;
		System.arraycopy(data, counter, targetHardwareAddressBytes,
				0, targetHardwareAddressBytes.length);
		counter += targetHardwareAddressBytes.length;
		System.arraycopy(data, counter, targetProtocolAddressBytes,
				0, targetProtocolAddressBytes.length);
		counter += targetProtocolAddressBytes.length;

		ArpPacket arpPacket = new ArpPacket();
		arpPacket.setHardwareType(new HardwareAddressTypeDecoder()
				.decode(hardwareAddressTypeBytes));
		arpPacket.setProtocolType(new EtherTypeDecoder()
				.decode(protocolAddressTypeBytes));
		arpPacket.setOperation(new ArpOperationDecoder()
				.decode(arpOperationBytes));
		arpPacket.setSenderHardwareAddress(new MacAddressDecoder()
				.decode(senderHardwareAddressBytes));
		arpPacket.setSenderProtocolAddress(new Ip4AddressDecoder()
				.decode(senderProtocolAddressBytes));
		arpPacket.setTargetHardwareAddress(new MacAddressDecoder()
				.decode(targetHardwareAddressBytes));
		arpPacket.setTargetProtocolAddress(new Ip4AddressDecoder()
				.decode(targetProtocolAddressBytes));

		return arpPacket;
	}
}
