/**
 * 
 */
package at.fabianachammer.nsend;

/**
 * @author fabian
 *
 */
public class NSender {

	public native void send(int interfaceIndex, byte[] data);
	
	static{
		System.loadLibrary("libnsender");
	}
}
