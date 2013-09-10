package at.fabianachammer.pdusend.type

/**
 * Factory for implementing the creation of data units.
 * @author fabian
 *
 */
interface DataUnitGenerator {

	/**
	 * @param value value from which the generator should create a DataUnit of
	 * @return implementation of a DataUnit
	 */
	DataUnit generateDataUnit(value)
}
