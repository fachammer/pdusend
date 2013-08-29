package at.fabianachammer.pdusend.core.type.factory.parser

import at.fabianachammer.pdusend.core.type.factory.DataUnitFactory

/**
 * @author fabian
 *
 */
interface DataUnitFactoryCreator {

	DataUnitFactory createDataUnitFactory(Map factoryData)
}
