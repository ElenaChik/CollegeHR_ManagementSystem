package com.prog2.hr.Exception;

/**
 * The OverLimitWorkloadException class.
 * 	extends Exception
 *  Implements OverLimit Workload Exception
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class OverLimitWorkloadException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String EXCEPTION_MESSAGE = "OverLimit Workload Exception";

	public OverLimitWorkloadException() {
		super(EXCEPTION_MESSAGE);
	}
}
