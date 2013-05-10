package at.fabianachammer.pdusend.type;

import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.ServiceTypeRFC791Decoder;

/**
 * Represents the service type field in an IP4 packet (RFC 791
 * http://tools.ietf.org/html/rfc791).
 * 
 * @author fabian
 * 
 */
@Guarded
public class ServiceTypeRFC791 extends ServiceType {

	/**
	 * size of the service type in bytes.
	 */
	public static final int SIZE = 1;

	/**
	 * defines the precedence bits of the service type.
	 */
	@NotNull
	private Precedence precedence = Precedence.Routine;

	/**
	 * specifies, whether the service needs low delay.
	 */
	private boolean lowDelay = false;

	/**
	 * specifies, whether the service needs high throughput.
	 */
	private boolean highThroughput = false;

	/**
	 * specifies, whether the service needs high reliability.
	 */
	private boolean highReliability = false;

	/**
	 * the decoder used for decoding data units of this type.
	 */
	private static final DataUnitDecoder<ServiceTypeRFC791> DECODER =
			new ServiceTypeRFC791Decoder();

	@Override
	public DataUnitDecoder<ServiceTypeRFC791> getDecoder() {
		return DECODER;
	}

	@Override
	public byte[] encode() {
		byte lowDelayBit = 0;
		if (lowDelay) {
			lowDelayBit = 1;
		}

		byte highThroughputBit = 0;
		if (highThroughput) {
			highThroughputBit = 1;
		}

		byte highReliabilityBit = 0;
		if (highReliability) {
			highReliabilityBit = 1;
		}

		byte shiftedPrecedence =
				(byte) ((getPrecedence().getBitMask() & Byte.MAX_VALUE) << 5);
		byte shiftedLowDelay =
				(byte) ((lowDelayBit & Byte.MAX_VALUE) << 4);
		byte shiftedHighThroughput =
				(byte) ((highThroughputBit & Byte.MAX_VALUE) << 3);
		byte shiftedHighRealiability =
				(byte) ((highReliabilityBit & Byte.MAX_VALUE) << 2);

		return new byte[] { (byte) (shiftedPrecedence
				+ shiftedLowDelay + shiftedHighThroughput + shiftedHighRealiability) };
	}

	@Override
	public int size() {
		return SIZE;
	}

	/**
	 * @return the precedence
	 */
	public Precedence getPrecedence() {
		return precedence;
	}

	/**
	 * @param precedence
	 *            the precedence to set
	 */
	public void setPrecedence(
			@AssertFieldConstraints final Precedence precedence) {
		this.precedence = precedence;
	}

	/**
	 * @return the lowDelay
	 */
	public boolean isLowDelay() {
		return lowDelay;
	}

	/**
	 * @param lowDelay
	 *            the lowDelay to set
	 */
	public void setLowDelay(final boolean lowDelay) {
		this.lowDelay = lowDelay;
	}

	/**
	 * @return the highThroughput
	 */
	public boolean isHighThroughput() {
		return highThroughput;
	}

	/**
	 * @param highThroughput
	 *            the highThroughput to set
	 */
	public void setHighThroughput(final boolean highThroughput) {
		this.highThroughput = highThroughput;
	}

	/**
	 * @return the highReliability
	 */
	public boolean isHighReliability() {
		return highReliability;
	}

	/**
	 * @param highReliability
	 *            the highReliability to set
	 */
	public void setHighReliability(final boolean highReliability) {
		this.highReliability = highReliability;
	}

	/**
	 * Represents the precedence bits in the service type field of an IP4
	 * packet.
	 * 
	 * @author fabian
	 * 
	 */
	public enum Precedence {
		/**
		 * network control precedence (111).
		 */
		NetworkControl((byte) 7),

		/**
		 * internetwork control precedence (110).
		 */
		InternetworkControl((byte) 6),

		/**
		 * CRITIC/ECP precedence (101).
		 */
		CRITIC_ECP((byte) 5),

		/**
		 * flash override precedence (100).
		 */
		FlashOverride((byte) 4),

		/**
		 * flash precedence (011).
		 */
		Flash((byte) 3),

		/**
		 * immediate precedence (010).
		 */
		Immediate((byte) 2),

		/**
		 * priority precedence (001).
		 */
		Priority((byte) 1),

		/**
		 * routine precedence (000).
		 */
		Routine((byte) 0);

		/**
		 * bit mask that defines the precedence.
		 */
		private byte bitMask;

		/**
		 * creates a new precedence with the specified bit mask.
		 * 
		 * @param bitMask
		 *            bit mask of the precedence
		 */
		private Precedence(final byte bitMask) {
			this.bitMask = bitMask;
		}

		/**
		 * Returns the precedence that matches with the corresponding service
		 * type byte.
		 * 
		 * @param input
		 *            service type byte
		 * @return precedence that matches the three most significant bits of
		 *         the service type byte
		 */
		public static Precedence getByServiceTypeByte(final byte input) {
			byte precedenceBits =
					(byte) ((input & Byte.MAX_VALUE) >>> 4);
			for (Precedence p : Precedence.values()) {
				if (p.getBitMask() == precedenceBits) {
					return p;
				}
			}

			throw new IllegalArgumentException(
					"Precedence with the bitmask "
							+ Integer.toBinaryString(precedenceBits)
							+ " is not defined");
		}

		/**
		 * @return the bitMask
		 */
		public byte getBitMask() {
			return bitMask;
		}
	}
}
