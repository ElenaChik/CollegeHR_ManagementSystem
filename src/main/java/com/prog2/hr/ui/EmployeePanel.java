package com.prog2.hr.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.prog2.hr.college.FormResponse;
import com.prog2.hr.college.Status;
import com.prog2.hr.service.CollegeService;

/**
 * The EmployeePanel class.
 * 	extends JPanel
 * Implements add Teacher and Stuff Forms
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class EmployeePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private String teacherDegree;
	
	private boolean isTeacher = false;
	private boolean isFullTime = false;

	JButton saveAndCloseButton;

    CollegeService cs;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField contactField;
    private JTextField experienceField;
    private JTextField specialityField;
    private JTextField workloadField;
    private JTextField hoursField;
    private JTextField personIDField;
    private JTextField personDepIDField;
    private JTextField genderField;
    private JTextField staffDutyField;

    JList<Object> listTeacherDegree;
    JCheckBox chckbxFullTime;
    JCheckBox isTeacherCheckBox;
    JLabel lblHours;

	public EmployeePanel(CollegeService cs) {

		this.cs = cs;
		setLayout(null);

		String[] degree = cs.enumDegreeToStringArray();

	    JLabel statusEmloyeeLabel = new JLabel("STATUS:");
	    statusEmloyeeLabel.setBounds(48, 547, 415, 14);
	    add(statusEmloyeeLabel);
	    JButton createEmployeeButton = new JButton("Create Employee");
	    createEmployeeButton.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		
	    		// Create employee as a Techer
	    		if (isTeacher) {
	    			
	    			// Check if Teacher is FullTime, then Hours field is disabled
	    			if (chckbxFullTime.isSelected()) {
		    			hoursField.setEnabled(false);
		    			hoursField.setText("0");
		    		} else {
		    			hoursField.setEnabled(true);
		    		}

	    			FormResponse<String> addTeacherResponse =
	    				cs.addTeacher(personIDField.getText(), 
	    				firstNameField.getText(),  lastNameField.getText(), 
	    				genderField.getText(), experienceField.getText(), 
	    				contactField.getText(), specialityField.getText(), 
	    				getTeacherDegree(), chckbxFullTime.isSelected(), 
	    				hoursField.getText(), personDepIDField.getText());

	    			
	    			// Set message to Status Lable the result of Clicked Button
	    			statusEmloyeeLabel.setText(addTeacherResponse.getMessage());
	    		
	    			// Print All created Departments with all Employee in it
	    			cs.printEmployeesOfDepartments();
	    			
//	    			System.out.println("\nTeacher  " 	+ firstNameField.getText() + " " 
//	    						+ lastNameField.getText()  
//	    						+  " at the department " + personDepIDField.getText()  
//	    						+  " was created");

	    			//Set empty field after button was clicked
	    			if (addTeacherResponse.getStatus() == Status.DONE) {
	    				personIDField.setText("");
		    			firstNameField.setText("");
		    			lastNameField.setText("");
	    				genderField.setText("");
	    				experienceField.setText("");
	    				contactField.setText("");
	    				specialityField.setText("");
	    				hoursField.setText("");
	    				personDepIDField.setText("");
			    		chckbxFullTime.setSelected(false);
			    		hoursField.setText("");
	    			}
	    			
	    			isFullTime = (isFullTime == false) ? true : false;

	    		// Create employee as a Staff
	    		} else  {

	    			//Create Staff
	    			FormResponse<String> addStaffResponse = 
	    				cs.addStaff(personIDField.getText(), 
	    					firstNameField.getText(),  lastNameField.getText(), 
			    			genderField.getText(), experienceField.getText(), 
			    			contactField.getText(), staffDutyField.getText(), 
			    			workloadField.getText(), personDepIDField.getText());

		    		// Set message to Status Lable 
	    			//the result of Clicked Button Add Employee
			    	statusEmloyeeLabel.setText(addStaffResponse.getMessage());

			    	// Print All created Departments with all Employee in it
			    	cs.printEmployeesOfDepartments();

//			    	System.out.println("\nStaff  " 	+ firstNameField.getText() 
//			    		+ " " + lastNameField.getText()  
//			    		+  " at the department " + personDepIDField.getText()  
//			    		+  " was created");

			    	//Set empty field after button was clicked
	    			if (addStaffResponse.getStatus() == Status.DONE) {
				    	personIDField.setText("");
			    		firstNameField.setText("");
			    		lastNameField.setText("");
		    			genderField.setText("");
		    			experienceField.setText("");
		    			contactField.setText("");
		    			staffDutyField.setText("");
		    			workloadField.setText("");
		    			personDepIDField.setText("");
	    			}
	    		}

	    		isTeacher = false;
	    		activateStaff(isTeacher);
	    		isTeacherCheckBox.setSelected(false);
	    	}
	    });

	    createEmployeeButton.setBounds(172, 494, 139, 28);
	    createEmployeeButton.setBorder(new LineBorder(new Color(0, 0, 0)));
	    createEmployeeButton.setBackground(Color.LIGHT_GRAY);
	    add(createEmployeeButton);

	    JLabel firstNameLabel = new JLabel("First  Name: ");
	    firstNameLabel.setBounds(10, 14, 78, 14);
	    add(firstNameLabel);
	    		    
	    firstNameField = new JTextField();
	    firstNameField.setBounds(143, 11, 342, 20);
	    firstNameField.setColumns(10);
	    add(firstNameField);

	    JLabel lastNameLabel = new JLabel("Last  Name: ");
	    lastNameLabel.setBounds(10, 40, 78, 14);
	    add(lastNameLabel);

	    lastNameField = new JTextField();
	    lastNameField.setColumns(10);
	    lastNameField.setBounds(143, 37, 342, 20);
	    add(lastNameField);

	    contactField = new JTextField();
	    contactField.setColumns(10);
	    contactField.setBounds(143, 124, 342, 20);
	    add(contactField);

	    JLabel ContactInfoLabel = new JLabel("Contact information: ");
	    ContactInfoLabel.setBounds(10, 127, 123, 14);
	    add(ContactInfoLabel);

	    JLabel ExperienceYearsLabel = new JLabel("Experience, years: ");
	    ExperienceYearsLabel.setBounds(10, 154, 123, 14);
	    add(ExperienceYearsLabel);

	    JLabel genderLabel = new JLabel("Gender  : ");
	    genderLabel.setBounds(10, 96, 64, 17);
	    add(genderLabel);

	    experienceField = new JTextField();
	    experienceField.setColumns(10);
	    experienceField.setBounds(143, 151, 342, 20);
	    add(experienceField);

	    JLabel lblSpeciality = new JLabel("Teacher Speciality: ");
	    lblSpeciality.setBounds(10, 233, 122, 20);
	    add(lblSpeciality);

	    specialityField = new JTextField();
	    specialityField.setColumns(10);
	    specialityField.setBounds(142, 233, 102, 20);
	    add(specialityField);

	    JLabel lblDegree = new JLabel("Teacher Degree: ");
	    lblDegree.setBounds(10, 264, 122, 21);
	    add(lblDegree);

	    chckbxFullTime = new JCheckBox("Full Time");
	    chckbxFullTime.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(hoursField.isEnabled())
	    			hoursField.setEnabled(false);
	    		else
	    			hoursField.setEnabled(true);
	    	}
	    });

	    chckbxFullTime.setBounds(10, 339, 95, 23);
	    add(chckbxFullTime);

	    lblHours = new JLabel("or hours: ");
	    lblHours.setBounds(120, 340, 64, 20);
	    add(lblHours);

	    JLabel lblDuty = new JLabel("Staff Duty:");
	    lblDuty.setBounds(319, 247, 66, 14);
	    add(lblDuty);

	    JLabel lblWorkload = new JLabel("Workload:");
	    lblWorkload.setBounds(318, 322, 67, 14);
	    add(lblWorkload);

	    workloadField = new JTextField();
	    workloadField.setColumns(10);
	    workloadField.setBounds(317, 339, 168, 20);
	    add(workloadField);

	    hoursField = new JTextField();
	    hoursField.setColumns(10);
	    hoursField.setBounds(187, 339, 57, 20);
	    add(hoursField);

	    personIDField = new JTextField();
	    personIDField.setColumns(10);
	    personIDField.setBounds(143, 65, 342, 20);
	    add(personIDField);

	    JLabel lblPersonID = new JLabel("ID: ");
	    lblPersonID.setBounds(10, 68, 36, 14);
	    add(lblPersonID);

	    JLabel lblDepartmentID = new JLabel("Add to the Department with ID:");
	    lblDepartmentID.setBounds(10, 182, 185, 14);
	    add(lblDepartmentID);

	    personDepIDField = new JTextField();
	    personDepIDField.setColumns(10);
	    personDepIDField.setBounds(202, 179, 283, 20);
	    add(personDepIDField);

	    genderField = new JTextField();
	    genderField.setColumns(10);
	    genderField.setBounds(143, 94, 342, 20);
	    add(genderField);

	    // Method returns TRUE if check box "is a Teacher" is selected
	    isTeacherCheckBox = new JCheckBox("is a Teacher");
	    isTeacherCheckBox.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		isTeacher = (isTeacher == false) ? true : false;
	    		System.out.println("isTeacher = " + isTeacher);
	    		activateStaff(isTeacher);
	    	}
	    });

	    isTeacherCheckBox.setBounds(10, 211, 99, 23);
	    add(isTeacherCheckBox);

	    staffDutyField = new JTextField();
	    staffDutyField.setColumns(10);
	    staffDutyField.setBounds(317, 264, 168, 20);
	    add(staffDutyField);
	    JScrollPane spList = new JScrollPane();
	    spList.setBounds(120, 264, 127, 56);
	    add(spList);

	    listTeacherDegree = new JList<>(degree);
	    listTeacherDegree.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    listTeacherDegree.setSelectedIndex(0);
	    spList.setViewportView(listTeacherDegree);

	    listTeacherDegree.addListSelectionListener(new ListSelectionListener(){
	    	@Override
	    	public void valueChanged(ListSelectionEvent e) {
	    		if (!e.getValueIsAdjusting()) {
	    		    setTeacherDegree(listTeacherDegree.getSelectedValue().toString());
	    		}
	    	}
	    });

	    JLabel lblForTeacher = new JLabel("for TEACHER");
	    lblForTeacher.setFont(new Font("Tahoma", Font.BOLD, 12));
	    lblForTeacher.setBounds(139, 214, 105, 14);
	    add(lblForTeacher);

	    JLabel lblForStuff = new JLabel("for STUFF ");
	    lblForStuff.setFont(new Font("Tahoma", Font.BOLD, 12));
	    lblForStuff.setBounds(353, 214, 105, 14);
	    add(lblForStuff);

	    Component verticalStrut = Box.createVerticalStrut(20);
	    verticalStrut.setBounds(48, 233, 1, 20);
	    add(verticalStrut);

	    activateStaff(isTeacher);
	}

	private void activateStaff(boolean isTeacher) {

		// Activate Teacher
		specialityField.setEnabled(isTeacher);
		listTeacherDegree.setEnabled(isTeacher);
		chckbxFullTime.setEnabled(isTeacher);
		hoursField.setEnabled(isTeacher);

		// Activate Staff
		staffDutyField.setEnabled(!isTeacher);
		workloadField.setEnabled(!isTeacher);
	}

	public String getTeacherDegree() {
		return listTeacherDegree.getSelectedValue().toString();
	}

	public void setTeacherDegree(String teacherDegree) {
		this.teacherDegree = teacherDegree;
	}
}
