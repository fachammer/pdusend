package at.fabianachammer.pdusend.dsl

import at.fabianachammer.pdusend.type.ServiceTypeRFC791
import at.fabianachammer.pdusend.type.pdu.ArpPacket
import at.fabianachammer.pdusend.type.pdu.EthernetFrame
import at.fabianachammer.pdusend.type.pdu.Ip4Packet
import at.fabianachammer.pdusend.type.pdu.VlanTag


/**
 * Class that represents all the objects used in the pdusend dsl.
 * @author fabian
 *
 */
class Objects {

	def binding = [
		ethernetObj: { new EthernetFrame() },
		arpObj: { new ArpPacket() },
		vlanTagObj: { new VlanTag() },
		ip4Obj: {new Ip4Packet() },
		serviceTypeObj: {new ServiceTypeRFC791() }
	]
}
