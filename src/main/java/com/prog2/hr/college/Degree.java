package com.prog2.hr.college;

/**
 * The Enum Degree
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public enum Degree {
	PHD(112), MASTER(82), BACHELOR(42);

	private int rate;

	private Degree(int rate) {
		this.rate = rate;
	}

	public int getRate() {
		return this.rate;
	}
}
