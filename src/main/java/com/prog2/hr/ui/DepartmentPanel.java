package com.prog2.hr.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

import com.prog2.hr.college.FormResponse;
import com.prog2.hr.college.Status;
import com.prog2.hr.service.CollegeService;

import static com.prog2.hr.app.Constants.*;

/**
 * The DepartmentPanel class.
 * 	extends JPanel
 *  Implements add Department and set Dean Forms
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class DepartmentPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField depNameField;
	private JTextField depIDField;
	private JLabel depNameLabel;
	private JLabel statusDepLabel;
	private JLabel depIDLabel;
	private JButton btnNewButton;
	
	private JButton buttonAddDean;
	
	private JTable tableDeps;
	private JTable tableTeacher;
	private JTable tableStuff;
	
	private CollegeService cs;
	
	private String activeDepartmentID;
	private String activeTeacherID;

	public DepartmentPanel(CollegeService cs) {

		this.cs = cs;
		setLayout(null);

		/*
		 * Create New Department Form
		 */
		createNewDepartmentForm();

		/*
	     * Create Table of Departments
	     */
	    createDepartmentTable(this.cs.departmentsToArray());

	    /*
	     * Create Table of Teachers
	     */
        createTeacherTable(this.cs.teachersToArray(getActiveDepartmentID()));

        /*
	     * Create Table of Stuff
	     */
        createStuffTable(this.cs.staffToArray(getActiveDepartmentID()));

        /*
         *  Create Add Dean button
         */
        createButtonAddDean() ;

        /*
		 * Create STATUS message Form
		 */
		createStatusForm();

		/*
		 * set Default Dean Elements
		 */
		setDeanElements(false, false);
	}

	public String getActiveDepartmentID() {
		return activeDepartmentID;
	}

	public void setActiveDepartmentID(String activeDepartmentID) {
		this.activeDepartmentID = activeDepartmentID;
	}

	public String getActiveTeacherID() {
		return activeTeacherID;
	}

	public void setActiveTeacherID(String activeTeacherID) {
		this.activeTeacherID = activeTeacherID;
	}

	/**
	 * Method create New Department Form
	 */
	private void createNewDepartmentForm() {

		depNameLabel = new JLabel("Department name: ");
		depNameLabel.setBounds(46, 32, 144, 14);
		add(depNameLabel);

		depNameField = new JTextField();
		depNameField.setBounds(185, 29, 202, 20);
		add(depNameField);
		depNameField.setColumns(10);

		depIDLabel = new JLabel("Department ID: ");
		depIDLabel.setBounds(46, 60, 137, 14);
		add(depIDLabel);

		depIDField = new JTextField();
		depIDField.setColumns(10);
		depIDField.setBounds(185, 57, 202, 20);
		add(depIDField);

		btnNewButton = new JButton(BUTTON_ADD_DEPARTMENT);
		btnNewButton.addMouseListener(new MouseAdapter() {

			// Create department with ID
			@Override
			public void mouseClicked(MouseEvent e) {
				FormResponse<String> addDepartmentResponse 
					= cs.addDepartment(depIDField.getText(), depNameField.getText());

				//Set empty field after button Create Department was clicked
				if (addDepartmentResponse.getStatus() == Status.DONE) {
					depIDField.setText("");
					depNameField.setText("");
					//Show STATUS message
					statusDepLabel.setText(addDepartmentResponse.getMessage());
				} else {
					//Show STATUS message
					statusDepLabel.setText(Status.ERROR.toString() + " " + addDepartmentResponse.getMessage());
				}

				/*
        		 * Update Departments Table
        		 */
				updateDepartmentTable();
			}
		});

		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(147, 98, 144, 33);
		add(btnNewButton);
	}
	
	/**
	 * Method create Status Form
	 */
	private void createStatusForm() {
		statusDepLabel = new JLabel("STATUS:");
		statusDepLabel.setBounds(21, 541, 447, 20);
		add(statusDepLabel);
	}
	
	/**
	 * Method Create Department Table
	 * based on JTable element
	 * 
	 * @param arrDepartments String[][]
	 */
	private void createDepartmentTable(String[][] arrDepartments) {

		String data[][] = arrDepartments;   
		DefaultTableModel modelDeps = new DefaultTableModel(data,COLUMN_DEPARTAMENT);

		tableDeps = new JTable(modelDeps);
        tableDeps.setBounds(200,40,200,200);

        JScrollPane spDeps = new JScrollPane(tableDeps);
        spDeps.setBounds(20, 200, 460, 122);
        
        add(spDeps);

        /*
         * Listener for JTable table Department
         */
        tableDeps.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent e) {
        		setDeanElements(true, false);
        		int row = tableDeps.rowAtPoint(e.getPoint());
        		//System.out.println("Value is :" + tableDeps.getValueAt(row, 0).toString());
        		setActiveDepartmentID(tableDeps.getValueAt(row, 0).toString());
        		/*
        		 * Update Teacher Table
        		 */
        		updateTeacherTable();
        		updateStaffTable();
        	}
        });
	}

	/**
	 * Method Create Teacher Table
	 * based on JTable element
	 * 
	 * @param arrTeachers String[][]
	 */
	private void createTeacherTable(String[][] arrTeachers) {
		
		String teacherData[][] = arrTeachers;
		DefaultTableModel modelTeacher = new DefaultTableModel(teacherData,COLUMN_TEACHER);
		
        tableTeacher = new JTable(modelTeacher);
        tableTeacher.setBounds(200,40,200,200);
        JScrollPane spTeacher = new JScrollPane(tableTeacher);
        spTeacher.setBounds(20, 342, 220, 122);
        
        add(spTeacher);
        
        /*
         * Listener for JTable table Department
         */	    
        tableTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent e) {
        		setDeanElements(true, true);
        		int row = tableTeacher.rowAtPoint(e.getPoint());
        		//System.out.println("Value is :" + tableTeacher.getValueAt(row, 0).toString());
        		setActiveTeacherID(tableTeacher.getValueAt(row, 0).toString());
        	}
        });
	}

	/**
	 * Method Create Stuff Table
	 * based on JTable element
	 * 
	 * @param arrStuff String[][]
	 */
	public void createStuffTable(String[][] arrStuff) {
		
		String stuffData[][] = arrStuff;
		DefaultTableModel modelStuff = new DefaultTableModel(stuffData,COLUMN_STUFF);
		
        tableStuff = new JTable(modelStuff);
        tableStuff.setBounds(200,40,200,200);
        tableStuff.setEnabled(false);
        
        JScrollPane spStuff = new JScrollPane(tableStuff);
        spStuff.setBounds(260, 342, 220, 122);
        
        add(spStuff);
	}
	
	/**
	 * Method Create Button Add Dean and Add selected Teacher as Dean
	 */
	private void createButtonAddDean() {
		
		buttonAddDean = new JButton(BUTTON_SET_DEAN);  
		buttonAddDean.setBounds(147, 486, 144, 33); 
	    add(buttonAddDean);

	    buttonAddDean.addActionListener((ActionListener) new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    		cs.setDean(getActiveDepartmentID(), getActiveTeacherID());
	    		updateDepartmentTable();
	    	}  
	    });

	    buttonAddDean.setBorder(new LineBorder(new Color(0, 0, 0)));
	    buttonAddDean.setBackground(Color.LIGHT_GRAY);
	}
	
	private void setDeanElements(boolean tableTeacherStatus, boolean buttonAddDeanStatus) {
		tableTeacher.setEnabled(tableTeacherStatus);
		buttonAddDean.setEnabled(buttonAddDeanStatus);
	}

	private void updateDepartmentTable() {
		tableDeps.setModel(new DefaultTableModel(cs.departmentsToArray(), COLUMN_DEPARTAMENT));
	}

	private void updateTeacherTable() {
		tableTeacher.setModel(new DefaultTableModel(cs.teachersToArray(getActiveDepartmentID()), COLUMN_TEACHER));
	}

	private void updateStaffTable() {
		tableStuff.setModel(new DefaultTableModel(cs.staffToArray(getActiveDepartmentID()), COLUMN_STUFF));
	}
}
