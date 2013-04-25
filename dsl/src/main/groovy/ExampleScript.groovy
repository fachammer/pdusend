import at.fabianachammer.pdusend.PduSender
import at.fabianachammer.pdusend.type.*

def ef = ethernet(
		destinationMacAddress: 0x0011223344550000,
		sourceMacAddress: 0xffeeddccbbaa,
		etherType: EtherType.ARP){ 
		arp(hardwareType: HardwareAddressType.ETHERNET,
			protocolType: EtherType.UNKNOWN,
			senderHardwareAddress: 0x001122334455,
			senderProtocolAddress: 0xc0a80001,
			targetProtocolAddress: 0xc0a80002)
}
		
send 0x7f2233 on lo