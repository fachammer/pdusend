package at.fabianachammer.pdusend.type.pdu.decoder;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.type.Ip4Address;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.Ip4AddressDecoder;
import at.fabianachammer.pdusend.type.decoder.ServiceTypeRFC791Decoder;
import at.fabianachammer.pdusend.type.decoder.TransportProtocolIdentifierDecoder;
import at.fabianachammer.pdusend.type.pdu.Ip4Packet;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Decoder for decoding IP4 packets.
 * 
 * @author fabian
 * 
 */
@Guarded
public class Ip4PacketDecoder implements DataUnitDecoder<Ip4Packet> {

	@Override
	public Ip4Packet decode(@NotNull @Size(min = Ip4Packet.MIN_SIZE,
			max = Ip4Packet.MAX_SIZE) final byte... data) {
		Ip4Packet packet = new Ip4Packet();

		byte[] firstByteHalfBytes = BitOperator.split(data[0]);

		packet.setVersion(firstByteHalfBytes[0]);
		packet.setInternetHeaderLength(firstByteHalfBytes[1]);
		packet.setServiceType(new ServiceTypeRFC791Decoder()
				.decode(data[1]));
		packet.setTotalLength(BitOperator.merge(data[3], data[2]));
		packet.setIdentification(BitOperator.merge(data[5], data[4]));

		packet.setFragmented(!BitOperator.isSet(data[6], 7));
		packet.setLastFragment(!BitOperator.isSet(data[6], 6));

		short fragmentOffset = BitOperator.merge(data[7], data[6]);
		fragmentOffset =
				(short) BitOperator.setBit(fragmentOffset, 15, false);
		fragmentOffset =
				(short) BitOperator.setBit(fragmentOffset, 14, false);

		packet.setFragmentOffset(fragmentOffset);

		packet.setTimeToLive(data[8]);
		packet.setProtocol(new TransportProtocolIdentifierDecoder()
				.decode(data[9]));
		packet.setHeaderChecksum(new byte[] { data[10], data[11] });
		Ip4AddressDecoder ip4AddressDecoder = new Ip4AddressDecoder();
		packet.setSourceAddress(ip4AddressDecoder.decode(data[12],
				data[13], data[14], data[15]));
		packet.setDestinationAddress(ip4AddressDecoder.decode(
				data[16], data[17], data[18], data[19]));

		byte[] embeddedData = new byte[data.length
				- Ip4Packet.MIN_SIZE];

		if (embeddedData.length > 0) {
			System.arraycopy(data, Ip4Packet.MIN_SIZE, embeddedData,
					0, embeddedData.length);

			DataUnit embeddedDataUnit =
					packet.getProtocol().getProtocolDecoder()
							.decode(embeddedData);

			packet.setEmbeddedData(embeddedDataUnit);
		}

		return packet;
	}

}
