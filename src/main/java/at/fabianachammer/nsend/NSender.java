/**
 * 
 */
package at.fabianachammer.nsend;

/**
 * Klasse zum Senden von beliebigen PDUs.
 * 
 * @author fabian
 * 
 */
public class NSender {

	static {
		System.loadLibrary("libnsender");
	}

	/**
	 * Methode, die einen beliebigen Byte-Array über eine bestimmte
	 * Netzwerkschnittstelle (NIC) verschickt.
	 * 
	 * @param interfaceIndex ID des NIC, der die Daten versenden soll
	 * @param data Daten, die gesendet werden sollen
	 */
	private native void send(final int interfaceIndex, final byte[] data);
}
