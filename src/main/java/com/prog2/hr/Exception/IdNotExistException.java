package com.prog2.hr.Exception;

/**
 * The IdNotExistException class.
 * 	extends Exception
 *  Implements Id Not Exist Exception
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class IdNotExistException extends Exception {

	private static final long serialVersionUID = 1L;
	static final String ERROR_MESSAGE = "ID Does Not Exist Exception";
	
	public IdNotExistException() {
		super(ERROR_MESSAGE);
	}
}
