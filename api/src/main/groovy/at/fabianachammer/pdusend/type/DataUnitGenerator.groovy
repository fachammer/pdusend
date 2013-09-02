package at.fabianachammer.pdusend.type

/**
 * abstract factory for implementing the creation of data units.
 * @author fabian
 *
 */
interface DataUnitGenerator {
	
	DataUnit generateDataUnit()
}
