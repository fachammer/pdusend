package at.fabianachammer.pdusend.dsl

import at.fabianachammer.pdusend.sender.NetworkSender
import at.fabianachammer.pdusend.type.AtomicDataUnit
import at.fabianachammer.pdusend.type.DataUnit

class DSL {
	
	NetworkSender sender
	
	DataUnit dataUnit
	NetworkInterface networkInterface
	
	DSL(NetworkSender sender){
		this.sender = sender
	}

	DSL send(DataUnit dataUnit){
		this.dataUnit = dataUnit
		return this
	}
	
	DSL on(NetworkInterface networkInterface){
		this.networkInterface = networkInterface
		sender.send(networkInterface, dataUnit)
		return this
	}
}
