import at.fabianachammer.pdusend.type.DataUnit

DataUnit ad = (DataUnit) 1

send ad on null  

send ad on 1  

send ad.encode() on 0  

send ad on NetworkInterface.getByIndex(1) 