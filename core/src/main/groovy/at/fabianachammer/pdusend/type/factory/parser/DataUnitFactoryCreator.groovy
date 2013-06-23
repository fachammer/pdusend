package at.fabianachammer.pdusend.type.factory.parser

import at.fabianachammer.pdusend.type.factory.DataUnitFactory

/**
 * @author fabian
 *
 */
interface DataUnitFactoryCreator {

	DataUnitFactory createDataUnitFactory(Map factoryData)
}
