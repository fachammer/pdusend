package at.fabianachammer.pdusend.core.type.factory

import at.fabianachammer.pdusend.core.type.DataUnit

/**
 * abstract factory for implementing the creation of data units.
 * @author fabian
 *
 */
interface DataUnitFactory {
	
	DataUnit createDataUnit()
}
