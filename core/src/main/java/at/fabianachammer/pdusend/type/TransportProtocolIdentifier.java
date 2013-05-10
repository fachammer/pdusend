package at.fabianachammer.pdusend.type;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.TransportProtocolIdentifierDecoder;
import at.fabianachammer.pdusend.type.pdu.ProtocolDataUnit;
import at.fabianachammer.pdusend.type.pdu.decoder.RawDataUnitDecoder;

/**
 * Represents a protocol on the transport layer of the OSI-model (layer 4).
 * 
 * @author fabian
 * 
 */
public class TransportProtocolIdentifier extends DataUnit implements
		ProtocolIdentifier {

	/**
	 * size of a transport protocol identifier in bytes.
	 */
	public static final int SIZE = 1;

	/**
	 * Unknown transport protocol identifier (here 0xff is used because zero is
	 * not a reserved field).
	 */
	public static final TransportProtocolIdentifier UNKNOWN =
			new TransportProtocolIdentifier((byte) 0xff,
					new RawDataUnitDecoder());

	/**
	 * specifies all predefined values for transport protocol identifiers.
	 */
	public static final TransportProtocolIdentifier[] VALUES =
			{ UNKNOWN };

	/**
	 * decoder used for decoding data units of this type.
	 */
	private static final DataUnitDecoder<TransportProtocolIdentifier> DECODER =
			new TransportProtocolIdentifierDecoder();
	/**
	 * id of the protocol. Defined in
	 * http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xml.
	 */
	private byte id;

	/**
	 * decoder that is used to decode the a protocol data unit of the protocol
	 * that this object represents.
	 */
	private DataUnitDecoder<? extends ProtocolDataUnit> protocolDecoder;

	/**
	 * Creates a new transport protocol identifier with the specified id and
	 * protocol decoder.
	 * 
	 * @param id
	 *            id that the identifier should have
	 * @param protocolDecoder
	 *            decoder that is used to decode a protocol data unit of the
	 *            protocol that this object represents.
	 */
	public TransportProtocolIdentifier(
			final byte id,
			final DataUnitDecoder<? extends ProtocolDataUnit> protocolDecoder) {
		this.id = id;
		this.protocolDecoder = protocolDecoder;
	}

	@Override
	public DataUnitDecoder<? extends ProtocolDataUnit> getProtocolDecoder() {
		return protocolDecoder;
	}

	@Override
	public DataUnitDecoder<TransportProtocolIdentifier> getDecoder() {
		return DECODER;
	}

	@Override
	public byte[] encode() {
		return new byte[] { id };
	}

	@Override
	public int size() {
		return SIZE;
	}

	/**
	 * @return the id
	 */
	public byte getId() {
		return id;
	}

	/**
	 * Creates a new transport protocol identifier with the specified id and a
	 * raw data unit decoder as protocol decoder.
	 * 
	 * @param id
	 *            id of the transport protocol identifier
	 * @return transport protocol identifier with the specified id and a raw
	 *         data unit decoder as protocol decoder
	 */
	public static TransportProtocolIdentifier create(final byte id) {
		for (TransportProtocolIdentifier tpi : VALUES) {
			if (tpi.getId() == id) {
				return tpi;
			}
		}

		return new TransportProtocolIdentifier(id,
				new RawDataUnitDecoder());
	}
}
