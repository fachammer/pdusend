package at.fabianachammer.pdusend.type.decoder;

import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.ServiceTypeRFC791;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Decoder for decoding ServiceType data units.
 * 
 * @author fabian
 * 
 */
@Guarded
public class ServiceTypeRFC791Decoder implements
		DataUnitDecoder<ServiceTypeRFC791> {

	/**
	 * minimum input size.
	 */
	private static final int MIN_SIZE = 1;

	@Override
	public ServiceTypeRFC791 decode(
			@Size(min = MIN_SIZE) final byte... data) {
		byte input = data[0];

		ServiceTypeRFC791 serviceType = new ServiceTypeRFC791();

		ServiceTypeRFC791.Precedence precedence =
				ServiceTypeRFC791.Precedence.getByServiceTypeByte(input);
		serviceType.setPrecedence(precedence);

		serviceType.setLowDelay(BitOperator.isSet(input, 5));
		serviceType.setHighThroughput(BitOperator.isSet(input, 4));
		serviceType.setHighReliability(BitOperator.isSet(input, 3));

		return serviceType;
	}
}
