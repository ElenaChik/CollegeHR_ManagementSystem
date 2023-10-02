package com.prog2.hr.college;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Department class.
 *  Implements Serializable
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	private String depID;
	private String depName;
	private Teacher dean;
	private List<Teacher> teachers; 
	private List<Staff> staffs;
	
	/**
	 * Constructor
	 * 
	 * @param dean
	 */
	public Department(String depID, String name) {
		this.depID = depID;
		this.depName = name;
		teachers = new ArrayList<>();
		staffs = new ArrayList<>();
	}
	
	
	/** 
	 * Add  ONE Teacher
	 * 
	 * @param candidate
	 */
	public void addTeacher(Teacher candidate) {
		if (this.teachers.contains(candidate)) {
			System.out.println("Candidate with this ID already exist !");
		} else {
			this.teachers.add(candidate);
		}
	}
	
	/** 
	 * Add MANY Teachers at the time
	 * 
	 * @param t
	 */
	public void addTeachers(Teacher... candidates) {
		for (Teacher candidate: candidates)
			addTeacher(candidate);
	}
	
	/** set Dean
	 * 
	 * @param dean
	 */
	public void setDean(Teacher candidate) {
		if (this.teachers.contains(candidate)) {
			this.dean = candidate;
		}
	}
	
	/** 
	 * Add  ONE Staff
	 * 
	 * @param candidate
	 */
	public void addStaff(Staff candidate) {
		if (this.staffs.contains(candidate)) {
			System.out.println("Candidate with this ID already exist!");
		} else {
			this.staffs.add(candidate);
		}
	}

	/** 
	 * toString method
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "Department [depID=" + depID + ", depName=" + depName + ", dean=" + dean + "]";
	}
	
	/** 
	 * getters and setters
	 * 
	 * @param depName
	 */
	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Teacher getDean() {
		return dean;
	}

	public String getDepID() {
		return depID;
	}

	public void setDepID(String depID) {
		this.depID = depID;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}
}
