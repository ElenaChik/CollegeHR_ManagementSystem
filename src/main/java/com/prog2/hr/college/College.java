package com.prog2.hr.college;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The College class.
 * 	Implements Serializable
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class College implements Serializable {

	private static final long serialVersionUID = 7480322979455235766L;

	private String name;
	private int age;
	private Map<String, Department> departments;

	/** 
	 * College Constructor
	 */
	public College(String name, int age) {
		this.name = name;
		this.age = age;
		departments = new HashMap<>();
	}

	/**
	 * Add Department to  College 
	 */
	public void addDepartment(String depID, Department department) {
		departments.put(depID, department);
	}
	
	/**
	 * Delete Department from  College 
	 */
	public void deleteDepartment(String depID) {
		departments.remove(depID);
	}

	/**
	 * Getters and Setters 
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Map<String, Department> departments) {
		this.departments = departments;
	}

	/**
	 * To String method
	 */
	@Override
	public String toString() {
		return "College [name=" + name + ", age=" + age + ", departments=" + departments + "]";
	}
}
