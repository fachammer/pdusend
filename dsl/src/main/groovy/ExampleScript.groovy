import at.fabianachammer.pdusend.PduSender
import at.fabianachammer.pdusend.pdu.*


send ethernet(
		destinationMacAddress: 0x001122334455,
		sourceMacAddress: 0xffeeddccbbaa,
		etherType: EtherType.ARP){ 
		arp(hardwareType: HardwareAddressType.Ethernet,
			protocolType: EtherType.IPv4,
			senderHardwareAddress: 0x001122334455,
			senderProtocolAddress: 0xc0a80001,
			targetProtocolAddress: 0xc0a80002)
} on lo