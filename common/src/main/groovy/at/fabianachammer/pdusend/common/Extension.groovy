package at.fabianachammer.pdusend.common

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
			BitOperator.newNumberWithBitSetOnIndex(delegate, index)
		}
		Number.metaClass.bitCount = {
			BitOperator.bitCountFromNumber(delegate)
		}
		Number.metaClass.toByteArray << {int sizeInBits ->
			BitOperator.toByteArray(delegate, sizeInBits)
		}
	}

	/**
	 * private constructor as this is an utility class.
	 */
	private Extension(){
	}
}
