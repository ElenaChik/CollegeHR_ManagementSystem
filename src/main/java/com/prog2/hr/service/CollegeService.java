package com.prog2.hr.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import com.prog2.hr.Exception.IdAlreadyUsedExeption;
import com.prog2.hr.Exception.IdNotExistException;
import com.prog2.hr.Exception.OverLimitWorkloadException;
import com.prog2.hr.college.College;
import com.prog2.hr.college.Degree;
import com.prog2.hr.college.Department;
import com.prog2.hr.college.Staff;
import com.prog2.hr.college.Status;
import com.prog2.hr.college.Teacher;
import com.prog2.hr.college.FormResponse;

import static com.prog2.hr.app.Constants.*;

/**
 * The CollegeService is a Service class
 *  Implements all methods used in dialog forms 
 *  of HR Application
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class CollegeService {
	
	private College college;
	
	/**
	 * Constructor creates College object
	 * 
	 * @return
	 */
	public College getCollege() {
		return college;
	}
	
	/** 
	 * Start application
	 * 	Create userFile with a Path to serealized file.
	 *  Deserialize College Object
	 *  Create new College If deserialization passed unsuccessfully
	 */
	public void startApplication() {

		File userFile = new File(PATH);
		if (userFile.isFile()) {
			try {
				college = deserializeData(PATH);
			// If Exception then create College
			} catch (Exception e) {
				createCollege(NAME, AGE) ;
			}
		// If File doesn't exist then create College
		} else {
			createCollege(NAME, AGE) ;
		}
		printEmployeesOfDepartments( );
	}
	

	/**
	 * Method creates College with name and foundation year 
	 * 
	 * @param name
	 * @param age
	 */
	public void createCollege(String name, int age) {
		this.college = new College(name,age);
	}
	
	/**
	 * Method creates Department with ID and Name 
	 * 	Check if this department ID is already used
	 * 	Create new Department and add it to College, 
	 * 	if this department ID is not used.
	 * 
	 * @param depID
	 * @param name
	 * 
	 * @return FormResponse object {Status, String Value, message}
	 */
	public FormResponse<String> addDepartment(String depID, String name) {
		Department department;
		FormResponse<String> fResp = new FormResponse<>();
		
		try {
			checkDepIDUsed(depID);
			department = new Department(depID, name);
			this.college.addDepartment(depID, department);
		} catch (IdAlreadyUsedExeption alrUsedEx) {
			return fResp.composeFormResponse(Status.ERROR, null, alrUsedEx.getMessage());
		}
		
		return fResp.composeFormResponse(Status.DONE, null, " Department was added");
	}

	/**
	 * Method adds Teacher to the specified department ID
	 * 	Convert String degree to Enum Degree rate
	 * 	Creates Teacher.
	 * 	Check if department ID exists.
	 * 	Check if person ID exists, if it is available
	 * 	Than adds Teacher to the specified department ID
	 * 
	 * 
	 * @param persID
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param experienceYears
	 * @param contact
	 * @param speciality
	 * @param degree
	 * @param isFullTime
	 * @param hoursWorked
	 * @param depID
	 * 
	 * @return FormResponse object {Status, String Value, message}
	 */
	public FormResponse<String> addTeacher(String persID, String firstName, String lastName, String gender,  String experienceYears,
			String contact, String speciality, String degree, 
			boolean isFullTime, String hoursWorked, String depID) {
		
		FormResponse<String> fResp = new FormResponse<>();
		
		//Convert String degree to Enum Degree
		Degree eDegree = stringToDegree(degree);

		try { 
			// Check if department ID exists
			// If false than creates Exception
			checkDepIDExist(depID);
			// Check if person ID already exists
			// If true than creates Exception
			checkPersIDExist(persID);
			// [1] Create Teacher
			if (hoursWorked == null || hoursWorked.isEmpty()) hoursWorked = "0";

			Teacher teacher = new Teacher(persID, firstName, lastName,
					gender, experienceYears,contact, speciality, eDegree,
					isFullTime, Integer.parseInt(hoursWorked));
			// Add Teacher to the Department
			college.getDepartments()
					.get(depID)
					.addTeacher(teacher);
		} catch (IdNotExistException idEx) {
			return fResp.composeFormResponse(Status.ERROR, null, idEx.getMessage());
		} catch (IdAlreadyUsedExeption idUsedEx) {
			return fResp.composeFormResponse(Status.ERROR, null, idUsedEx.getMessage());
		}

		return fResp.composeFormResponse(Status.DONE, null, "Positive message");
	}
	
	/** 
	 * Method adds Staff to the specified department ID
	 * 	Check if workload is valid
	 * 	Creates Staff.
	 * 	Check if department ID exists.
	 * 	Check if person ID exists, if it is available
	 * 	Than adds Staff to the specified department ID
	 * 
	 * @param persID
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param experienceYears
	 * @param contact
	 * @param duty
	 * @param workload
	 * @param depID
	 * 
	 * @return FormResponse
	 */
	public FormResponse<String> addStaff(String persID, String firstName, String lastName, 
			String gender,  String experienceYears,
			String contact, String duty, String workload, String depID) {

		Staff staff = null;
		FormResponse<String> fResp = new FormResponse<>();

		// Check if Workload is valid
		try { 
			isWorkloadValid(workload);
			// [1] Create Staff
			staff = new Staff(persID, firstName, lastName, 
					gender, experienceYears,
					contact, duty, Double.parseDouble(workload));
		// If Workload in NOT valid
		} catch (OverLimitWorkloadException ovlEx) {
			return fResp.composeFormResponse(Status.ERROR, null, ovlEx.getMessage());
		}

		// [2] Add Staff into Department with department ID
		// With HashMap
		try {
			checkDepIDExist(depID);
			checkPersIDExist(persID);
			college.getDepartments()
					.get(depID)
					.addStaff(staff);
			
		// If Department with this ID doesn't exist
		} catch (IdNotExistException idEx) {
			return fResp.composeFormResponse(Status.ERROR, null, idEx.getMessage());
		} catch (IdAlreadyUsedExeption idUsedEx) {
			return fResp.composeFormResponse(Status.ERROR, null, idUsedEx.getMessage());
		}

		return fResp.composeFormResponse(Status.DONE, null, "Positive message");
	}

	/** 
	 * Method check if Person ID already exist in the HashMap of Departments
	 * 	than check in each ArrayList of Teachers and Staff
	 * 
	 * @param persID
	 * @throws IdAlreadyUsedExeption
	 */
	private void checkPersIDExist(String persID) throws IdAlreadyUsedExeption {
		
		Department department;
		
		Map <String, Department> departments = getCollege().getDepartments();
		for (Map.Entry<String, Department> keyAndDepartment : departments.entrySet()) {
			department = keyAndDepartment.getValue();

			// Search in Teachers
			for (Teacher teacher: department.getTeachers()) {
				if (teacher.getPersID().equals(persID)) {
					throw new IdAlreadyUsedExeption();
				}
			}

			// Search in in Staff
			for (Staff staff: department.getStaffs()) {
				if (staff.getPersID().equals(persID)) {
					throw new IdAlreadyUsedExeption();
				}
			}
		}
	}
	
	/** 
	 * Method checks if Department ID exist in the HashMap of departments
	 * 	by the key: department ID
	 * 
	 * @param depID
	 * @throws IdNotExistException
	 */
	private void checkDepIDExist(String depID) throws IdNotExistException {
		if (!college.getDepartments().containsKey(depID)) {
			throw new IdNotExistException();
		} 
	}
	
	/**
	 * Method checks if Department ID already used in the HashMap of departments
	 * 	by the key: department ID
	 * 
	 * @param depID
	 * @throws IdAlreadyUsedExeption
	 */
	private void checkDepIDUsed(String depID) throws IdAlreadyUsedExeption {
		if (college.getDepartments().containsKey(depID)) {
			throw new IdAlreadyUsedExeption();
		}
	}
	
	/** 
	 * Method checks if Stuff Workload is valid
	 * @param workload
	 * 
	 * @return boolean true if workload is valid
	 * @throws OverLimitWorkloadException 
	 */
	private boolean isWorkloadValid(String workload) throws OverLimitWorkloadException {
		if (Double.parseDouble(workload) <= 40) {
			return true;
		} else {
			throw new OverLimitWorkloadException();
		}
	}

	/**
	 * Method serialize data into a file at this path 
	 * 	or creates new file with this name 
	 * 	if file doesn't exist
	 * 
	 * @param path
	 * @param object
	 */
	public static void serializeData(String path, Object object) {
		try ( FileOutputStream fileOut = new FileOutputStream(path)) {
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(object);
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	/**
	* Deserialize data from a file at this path
	*
	* @param path the path of the file
	* @return the data deserialized from the file
	* @throws IOException 
	* @throws ClassNotFoundException 
	*/
	public static College deserializeData(String path) throws IOException, ClassNotFoundException {
		College college = null;
		FileInputStream fileIn = new FileInputStream(path);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		college = (College) in.readObject();
		in.close();
		fileIn.close();
		    	
		return college;
	}

	/** 
	 * Convert Departments HashMap to Array
	 * @return String[][] Multiple Arrays of Strings
	 * 	that has information about department: 
	 * 	ID, Name, Dean
	 */
	public String[][] departmentsToArray() {
		// Read departments HashMap
		Map <String, Department> departments = getCollege().getDepartments();
		//Create new Arrays
		String[][] depArray = new String[departments.size()][3];
		int i = 0;

		// In the loop from each key (department ID) of HashMap get:
		for (Map.Entry<String, Department> set : departments.entrySet()) { 
			// get department ID and set it to department Array at [0] index 
			depArray[i][0] = set.getKey();
			// get department object
			Department department = set.getValue();
			// get department name and set it to department Array at [1] index 
			depArray[i][1] = department.getDepName();
			// get Dean variable in Teacher's object
			Teacher teacher = department.getDean();

			// if Teacher is not Dean than set teacher's first and last name 
			//	as a Dean  into department Array at [2] index 
			if (teacher != null) {
				depArray[i][2] = teacher.getFirstName() + " " + teacher.getLastName();
			}

			i++;
		}

		return depArray;
	}

	/**
	 * Method transforms Enum Degree to String Array
	 * 
	 * @return String[] The Arrays of Strings
	 */
	public String[] enumDegreeToStringArray() {
		String[] arrayDegree = new String[3];
		int i = 0;

		for(Degree de: Degree.values()) {
			arrayDegree[i] = de.toString();
			i++;
		}

		return arrayDegree;
	}
	
	/**
	 * Method transform Enum Degree to String Array
	 * 
	 * @param degree
	 * @return numeric value of Degree
	 */
	public Degree stringToDegree(String degree) {
		return Degree.valueOf(degree);
	}
	

	/**
	 * Method get Object of Employee Payroll Response 
	 * 	that contains Status of completed request,
	 * 	Value - calculated salary,
	 * 	Message - about completed request
	 * 
	 * @param persID 
	 * @return FormRespose {Status, Value, Message}
	 */
	public FormResponse<String> getEmployeePayrollResponse(String persID) {

		FormResponse<String> fResp = new FormResponse<>();

		// Step 1 - Find if exist the employee with persID
		// if found -> return double salary
		// else throw new IdNotExistException(errorMessage)
		try {
			String[] result = findAndCalculateSalary(persID);
			fResp.setValue(result[0]);
			fResp.setStatus(Status.DONE);
			fResp.setMessage(result[1]);
		} catch (IdNotExistException ee) {
			fResp.setValue(null);
			fResp.setStatus(Status.ERROR);
			fResp.setMessage(ee.getMessage());
		}

		return fResp;
	}
	
	/**
	 * Method calculate salary to employee according employee ID
	 * 	Find if exist the employee with persID
	 * 	If found, Compute Payroll
	 * 
	 * @param persID
	 * @return String[] {Salary, Personal Information}
	 * @throws IdNotExistException
	 */
	private String[] findAndCalculateSalary(String persID) throws IdNotExistException {

		String[] result = new String[2];
		Department department;

		// find Employee by persID
		// Go through each Department
		Map <String, Department> departments = getCollege().getDepartments();
		for (Map.Entry<String, Department> keyAndDepartment : departments.entrySet()) {
			department = keyAndDepartment.getValue();

			// Search in Teachers
			for (Teacher teacher: department.getTeachers()) {
				if (teacher.getPersID().equals(persID)) {
					// Compute Payroll
					result[0] = Double.toString(teacher.computePayroll());
					// Get Personal information
					result[1] = teacher.getPersonalInfo();
					return result;
				}
			}

			// Search in in Staff
			for (Staff staff: department.getStaffs()) {
				if (staff.getPersID().equals(persID)) {
					// Compute Payroll
					result[0] = Double.toString(staff.computePayroll());
					// Get Personal information
					result[1] = staff.getPersonalInfo();
					return result;
				}
			}
		}

		// if doesn't exist 
		throw new IdNotExistException();
	}

	/** 
	 * Method Teachers to Array creates an Array of Teachers
	 * 	for specific department ID
	 * 
	 * @param depID
	 * @return String[][] multiple Array contains
	 * 			person ID and Name
	 */
	public String[][] teachersToArray(String depID) {
		String[][] teacherArray = null;

		Map <String, Department> departments = getCollege().getDepartments();
		if (departments.containsKey(depID)) {
			List<Teacher> teachers = departments.get(depID).getTeachers();
			teacherArray = new String[teachers.size()][2];
			int i = 0;
			for (Teacher teacher: teachers) {
				teacherArray[i][0] = teacher.getPersID();
				teacherArray[i][1] = teacher.getFirstName() + " " + teacher.getLastName();
				i++;
			}
		}

		return teacherArray;
	}
	
	/** 
	 * Method Staff to Array creates an Array of Staff
	 * 	for specific department ID
	 * 
	 * @param depID
	 * @return String[][] multiple Array contains
	 * 	person ID and Name
	 */
	public String[][] staffToArray(String depID) {
		String[][] staffArray = null;

		Map <String, Department> departments = getCollege().getDepartments();
		if (departments.containsKey(depID)) {
			List<Staff> staffs = departments.get(depID).getStaffs();
			staffArray = new String[staffs.size()][2];
			int i = 0;
			for (Staff staff: staffs) {
				staffArray[i][0] = staff.getPersID();
				staffArray[i][1] = staff.getFirstName() + " " + staff.getLastName();
				i++;
			}
		}

		return staffArray;
	}
	
	/** 
	 * Method set Dean to Department with Department ID
	 * 	Check if this department ID exists
	 * 	Check if this teacher ID exists in this department
	 * 	Set Dean to this teacher
	 * 
	 * @param depID
	 * @param teacherID
	 * @return FormResponse
	 */
	public FormResponse<String> setDean(String depID, String teacherID) {
		FormResponse<String> fResp = new FormResponse<>();
		
		Map <String, Department> departments = getCollege().getDepartments();
		// Check if this Department ID exists
		if (departments.containsKey(depID)) {
			List<Teacher> teachers = departments.get(depID).getTeachers();
			// Check if this teacher ID exist in this Department
			for (Teacher teacher: teachers) {
				if (teacher.getPersID().equals(teacherID)) {
					// Set Dean to this Teacher
					departments.get(depID).setDean(teacher);
					return fResp.composeFormResponse(Status.DONE, depID, "message : Is created");
				}
			}
		}
	
		return fResp.composeFormResponse(Status.ERROR, depID, "negative message");
	}
	
	/**
	 * Method print all Departments
	 */
	public void printDepartments( ) {
		Map<String, Department> deps = this.getCollege().getDepartments();

		for (Map.Entry<String, Department> set :  deps.entrySet()) {
			System.out.println(set.getKey() + " = " + set.getValue());
		}
	}

	/**
	 * Method prints All Employees at each Department
	 */
	public void printEmployeesOfDepartments( ) {
		Map<String, Department> deps = this.getCollege().getDepartments();

		for (Map.Entry<String, Department> set :  deps.entrySet()) {
			System.out.println("\nDepartment: " + set.getKey() + " = " + set.getValue());
			for (Teacher teacher:  set.getValue().getTeachers()) {
				System.out.println("\nTEACHER -> " + teacher.toString());
			}
			for (Staff staff:  set.getValue().getStaffs()) {
				System.out.println("\nSFAFF -> " + staff.toString());
			}
		}
	}
}
