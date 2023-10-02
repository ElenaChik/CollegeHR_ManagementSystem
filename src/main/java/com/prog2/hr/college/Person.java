package com.prog2.hr.college;

import java.io.Serializable;
import java.util.Objects;

/**
 * The abstract class  Person
 * Implements Serializable
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public abstract class  Person implements Serializable{

	private static final long serialVersionUID = 1L;
	private String persID;
	private String firstName;
	private String lastName;
	private String gender;
	private Role post; /* enum Person's post */
	private String experienceYears;
	private String contact;

	/**
	 *  Constructor
	 *  
	 * @param persID
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param post
	 * @param experienceYears
	 * @param contact
	 */
	public Person(String persID, String firstName, String lastName, String gender, Role post, String experienceYears,
			String contact) {

		this.persID = persID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.post = post;
		this.experienceYears = experienceYears;
		this.contact = contact;
	}

	/**
	 * Abstract method
	 */
	protected abstract String getPersonalInfo();

	/**
	 * Getters and setters
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMale() {
		return gender;
	}

	public void setMale(String male) {
		this.gender = male;
	}

	public Role getPost() {
		return post;
	}

	public void setPost(Role post) {
		this.post = post;
	}

	public String getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(String experienceYears) {
		this.experienceYears = experienceYears;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPersID() {
		return persID;
	}
	
	public void setPersID(String persID) {
		this.persID = persID;
	}

	/**
	 * To String
	 */
	@Override
	public String toString() {
		return "Person [persID=" + persID + ", "
				+ "\nfirstName=" + firstName + ", lastName=" + lastName + ", "
						+ "\ngender=" + gender 	+ ", post=" + post + ", "
								+ "\nexperienceYears=" + experienceYears + ", "
										+ "\ncontact=" + contact
				+ "]";
	}

	/**
	 * Hash Code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(contact, experienceYears, firstName, lastName, gender, persID, post);
	}

	/**
	 * Equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(contact, other.contact) && experienceYears == other.experienceYears
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& gender == other.gender && persID == other.persID && post == other.post;
	}
}
