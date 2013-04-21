/**
 * 
 */
package at.fabianachammer.pdusend.type.pdu;

import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.type.Ip4Address;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;

/**
 * Represents an IPv4 segment. Currently doesn't support options.
 * 
 * @author fabian
 * 
 */
public class Ip4Segment implements EmbeddingProtocolDataUnit {

	private static final byte VERSION = 4;

	private byte differentiatedServiceCodePoint;
	private byte explicitCongestionNotification;
	private short identification;
	private boolean dontFragment;
	private boolean moreFragments;
	private short fragmentOffset;
	private byte timeToLive;
	private byte protocol;
	private Ip4Address sourceIpAddress;
	private Ip4Address destinationIpAddress;

	@Override
	public final DataUnitDecoder<Ip4Segment> getDecoder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final byte[] encode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DataUnit getEmbeddedData() {
		// TODO Auto-generated method stub
		return null;
	}

}
