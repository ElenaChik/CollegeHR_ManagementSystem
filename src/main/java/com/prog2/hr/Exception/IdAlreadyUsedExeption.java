package com.prog2.hr.Exception;

/**
 * The IdAlreadyUsedExeption class.
 *  extends Exception
 *  Implements Id Already Used Exeption
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class IdAlreadyUsedExeption extends Exception {

	private static final long serialVersionUID = 1L;
	static final String ERROR_MESSAGE = "Id Already Used Exeption";

	public IdAlreadyUsedExeption() {
		super(ERROR_MESSAGE);
	}
}
