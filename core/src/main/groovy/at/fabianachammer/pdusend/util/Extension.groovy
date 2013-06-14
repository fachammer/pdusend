package at.fabianachammer.pdusend.util

/**
 * @author fabian
 *
 */
class Extension {

	static void extend(){
		Number.metaClass.hasBitSetOnIndex = {index ->
			BitOperator.isBitSetOnIndex(delegate, index)
		}
		Number.metaClass.setBitOnIndex = {index ->
			BitOperator.setBitOnIndex(delegate, index)
		}
		Number.metaClass.clearBitOnIndex = {index ->
			BitOperator.clearBitOnIndex(delegate, index)
		}
	}
}
