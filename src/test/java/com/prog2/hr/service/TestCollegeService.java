package com.prog2.hr.service;

import com.prog2.hr.college.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for {@link CollegeService} class.
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class TestCollegeService {

	/* ERROR_MESSAGE default message on IdNotExistException */
	static final String ERROR_MESSAGE = "ID Does Not Exist Exception";
	/* College Service object */
	private CollegeService cs;
	/* Department object */
	Department department;
	/* Person ID : Teacher|Staff */
	private String persID;

	/**
	 * Method setup
	 * 	performs Before Each Test Method
	 * 	initialize College Service object
	 */
	@BeforeEach
	public void setup() {
		persID = "0";
		cs = new CollegeService();
		cs.createCollege("Vanier", 150);
		department = createDepartment("1", "Mathematics");
		cs.getCollege().addDepartment(department.getDepID(), department);
	}

	/**
	 * Test for getEmployeePayrollResponse Method
	 * 	Not existed Person ID
	 * 	throws IdNotExistException
	 */
	@Test
	public void TestGetEmployeePayrollResponseErrorCase() {
		
		// Given
		persID = "101"; // Not existed Person ID
		
		// When must throw IdNotExistException
		FormResponse<String> fResponse = cs.getEmployeePayrollResponse(persID);
		
		// Then must throw IdNotExistException
		assertEquals(fResponse.getStatus(), Status.ERROR);
		assertEquals(fResponse.getMessage(), ERROR_MESSAGE);
		assertNull(fResponse.getValue());
	}

	/**
	 * Test for getEmployeePayrollResponse Method
	 * 	for Existed Teacher ID
	 */
	@Test
	public void TestGetEmployeePayrollResponseForTeacher() {

		// Given
		persID = "1"; // Existing Teacher ID

		// When
		FormResponse<String> fResponseForTeacher = cs.getEmployeePayrollResponse(persID);

		// Then
		Assertions.assertEquals(fResponseForTeacher.getStatus(), Status.DONE);
		// salary = (hoursWorked(10) * PHD(112) * 2) * 0.76 = 1702.4d
		assertEquals(fResponseForTeacher.getValue(), "1702.4");
		assertTrue(fResponseForTeacher.getMessage().contains("ExperienceYears"));
	}

	/**
	 * Test for getEmployeePayrollResponse Method
	 * 	for Existed Staff ID
	 */
	@Test
	public void TestGetEmployeePayrollResponseForStaff() {

		// Given
		persID = "2"; // Existing Staff ID

		// When
		FormResponse<String> fResponseForStaff = cs.getEmployeePayrollResponse(persID);

		// Then
		Assertions.assertEquals(fResponseForStaff.getStatus(), Status.DONE);
		// salary = (workload(10) * 32 * 2) * 0.75 = 480.0d
		assertEquals(fResponseForStaff.getValue(), "480.0");
		assertTrue(fResponseForStaff.getMessage().contains("ExperienceYears"));
	}

	/**
	 * Test Departments To Array Method
	 */
	@Test
	public void TestDepartmentsToArray() {

		// Given
		String depID = "2";
		String depName = "Software";
		cs.getCollege().addDepartment(depID, createDepartment(depID, depName));

		// When
		// create departments array {"ID","Department","Dean"}
		String[][] depArr = cs.departmentsToArray();

		// Then
		assertNotNull(depArr);
		assertEquals(depArr.length, 2);
		assertEquals(depArr[0].length, 3);;
		assertEquals(depArr[1][0], "2");
		assertEquals(depArr[1][1], depName);
		assertNull(depArr[1][2]);
	}

	/**
	 * Method createDepartment
	 * 	compose Department object including Teacher and Staff objects
	 *
	 * @param depID
	 * @param name
	 * @return Department
	 */
	public static Department createDepartment(String depID, String name) {

		Department department = new Department(depID, name);

		Teacher teacher = new Teacher("1", "Elena","Chailik", "F", "3",
				"", "Java Developer", Degree.PHD, false, 10);
		department.addTeacher(teacher);

		Staff staff = new Staff("2", "Boris", "Wilson",
				"M", "12","", "", 10.0d);
		department.addStaff(staff);

		Staff staff2 = new Staff("3", "Jorge", "Summer",
				"M", "5","", "", 41.0d);
		department.addStaff(staff2);

		return department;
	}
}
