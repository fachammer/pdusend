package at.fabianachammer.pdusend.aspect;

import net.sf.oval.context.MethodParameterContext;
import net.sf.oval.exception.ConstraintsViolatedException;
import net.sf.oval.exception.ExceptionTranslator;
import net.sf.oval.exception.OValException;
import net.sf.oval.guard.GuardAspect;

/**
 * aspect that validates input values on setters of pdusend classes.
 * 
 * @author fabian
 * 
 */
public aspect PreConditionValidation extends GuardAspect {

	public PreConditionValidation() {
		super();

		getGuard().setExceptionTranslator(new ExceptionTranslator() {

			@Override
			public RuntimeException translateException(
					OValException ex) {
				if (ex instanceof ConstraintsViolatedException) {
					ConstraintsViolatedException e =
							(ConstraintsViolatedException) ex;
					if (e.getConstraintViolations()[0].getInvalidValue() == null) {
						return new NullPointerException(e.getMessage());
					}

					else if (e.getConstraintViolations()[0].getContext() instanceof MethodParameterContext) {
						return new IllegalArgumentException(e.getMessage());
					}
				}

				return new RuntimeException(ex.getMessage());
			}
		});
	}
}