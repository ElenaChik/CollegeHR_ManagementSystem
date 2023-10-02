package com.prog2.hr.college;

import java.util.Objects;

/**
 * The DepartmentPanel class.
 * 	extends JPanel
 *  Implements add Department and set Dean Forms
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class Staff extends Person implements IPayRoll {

	private static final long serialVersionUID = 1L;
	private String duty;
	private double workload;

	/** 
	 * Constructor
	 * 
	 * @param persID
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param post
	 * @param experienceYears
	 * @param contact
	 * @param duty
	 * @param workload
	 */
	public Staff(String persID, String firstName, String lastName, 
				String gender, String experienceYears,
				String contact, String duty, double workload) {
			super(persID, firstName, lastName, gender, Role.STUFF, experienceYears, contact);
			this.duty = duty;
			this.workload = workload;
		}

	/**
	 * Calculate salary
	 * 
	 * @return salary
	 */
	@Override
	public double computePayroll() {
		return  (workload * 32 * 2) * 0.75;
	}
	
	/** 
	 * Realization of abstract method getPersonalInfo
	 * 
	 * @return personal Info
	 */
	@Override
	public String getPersonalInfo() {
		return "\nStaff ID: " + getPersID() 
		+ "\n" + getFirstName() + " " + getLastName() 
		+ "\nGender " + getMale() + ", Post " + getPost() 
		+ ", ExperienceYears " + getExperienceYears() 
		+ ", \nContact " + getContact()
		+ ", \nduty " + duty
		+ ", workload " + workload;
	}

	/** 
	 * Getters and Setters
	 * 
	 * @return Duty
	 */
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public double getWorkload() {
		return workload;
	}

	public void setWorkload(double workload) {
		this.workload = workload;
	}

	/** 
	 * Overriding of toString method
	 */
	@Override
	public String toString() {
		return "\nStaff [ID=" + getPersID() + "\nFirstName=" + getFirstName() + ", LastName=" + getLastName() 
		+ "Gender=" + getMale() + ", Post=" + getPost() + ", "
		+ "\nContact=" + getContact() + "duty=" + duty + ", workload=" + workload + "]";
	}

	/** 
	 * Overriding of HashCode
	 * 
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(duty, workload);
		return result;
	}


	/** 
	 * Overriding of equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		return this.getPersID().equals(other.getPersID());
	}
}
