package com.prog2.hr.college;

import java.util.Objects;

/**
 * The Teacher class.
 * 	extends Person 
 * Implements IPayRoll
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class Teacher extends Person implements IPayRoll{

	private static final long serialVersionUID = 1L;
	private String speciality;
	private Degree degree;
	private boolean isFullTime;
	private int hoursWorked;

	/** 
	 * Constructor 
	 */
	public Teacher(String persID, String firstName, String lastName, String gender, String experienceYears,
			String contact, String speciality, Degree degree, boolean isFullTime, int hoursWorked) {
		super(persID, firstName, lastName, gender, Role.TEACHER, experienceYears, contact);
		this.speciality = speciality;
		this.degree = degree;
		this.isFullTime = isFullTime;

		if (isFullTime) {
			this.hoursWorked  = 32;
		} else {
			this.hoursWorked = hoursWorked;
		}
	}

	/**
	 *  Calculate salary for full time and part time depending on Degree Rate
	 *  
	 *  @return salary
	 */
	public double computePayroll() {

		double salary = 0;
		int rate = degreeToRate(degree);
		
		// Calculate salary FullTime and PartTime
		if (hoursWorked == 32) {
			salary = (32 * rate * 2) * 0.85;
		} else {
			salary = (hoursWorked * rate * 2) * 0.76;
		}

		return salary;
	}

	/* 
	 * Transform DegreeRate  to rate number 
	 */
	private int degreeToRate(Degree degree) {
		return degree.getRate();
	}
	
	/*
	 * Implemented method Get Personal Info 
	 */
	@Override
	public  String getPersonalInfo() {
		return "\nTeacher ID: " + getPersID() 
			+ "\n" + getFirstName() + " " + getLastName() 
			+ "\nGender " + getMale() + ", Post " + getPost() + ", "
			+ "\nspeciality " + speciality + ", degree " + degree 
			+ ", ExperienceYears " + getExperienceYears() 
			+ ", Contact " + getContact()
			+ "\nisFullTime " + isFullTime  
			+ ", hoursWorked " + hoursWorked;
	}


	/* 
	 * Getters and Setters
	 */
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public boolean isFullTime() {
		return isFullTime;
	}

	public void setFullTime(boolean isFullTime) {
		this.isFullTime = isFullTime;
	}

	public int getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	/*
	 * To String method
	 */
	@Override
	public String toString() {
		return "\nTeacher [ID=" + getPersID() + "\nFirstName=" + getFirstName() + ", LastName=" + getLastName() 
			+ "Gender=" + getMale() + ", Post=" + getPost() + ", "
				+ "\nspeciality=" + speciality + ", degree=" + degree + ", ExperienceYears=" + getExperienceYears() 
						+ "\nisFullTime=" + isFullTime  + ", hoursWorked=" + hoursWorked + ", "
										+ "Contact=" + getContact() +  "]";
	}

	/* 
	 * HashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(hoursWorked);
		return result;
	}

	/*
	 * Equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		return getPersID().equals(other.getPersID());
	}
}