package at.fabianachammer.pdusend.util

/**
 * class that extends primitive types by adding methods.
 * @author fabian
 *
 */
final class Extension {

	static void extend(){
		Number.metaClass.hasBitSetOnIndex = {index ->
			BitOperator.isBitSetOnIndex(delegate, index)
		}
		Number.metaClass.orBitOnIndex = {index ->
			BitOperator.setBitOnIndex(delegate, index)
		}
		Number.metaClass.andNotBitOnIndex = {index ->
			BitOperator.clearBitOnIndex(delegate, index)
		}
		Number.metaClass.bitCount = {
			BitOperator.bitCountFromNumber(delegate)
		}
	}

	private Extension(){

	}
}
