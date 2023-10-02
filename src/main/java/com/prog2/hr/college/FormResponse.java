package com.prog2.hr.college;

/**
 * The FormResponse Container
 * 	Implements universal Response Container
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class FormResponse<T> {

	private Status status;
	private T value;
	private String message;

	public FormResponse<T> composeFormResponse(Status status, T value, String message) {
		this.status = status;
		this.value = value;
		this.message = message;

		return this;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
