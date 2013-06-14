package at.fabianachammer.pdusend.type.factory

import at.fabianachammer.pdusend.type.DataUnit

/**
 * abstract factory for implementing the creation of data units.
 * @author fabian
 *
 */
interface DataUnitFactory {
	
	DataUnit createDataUnit()
}
