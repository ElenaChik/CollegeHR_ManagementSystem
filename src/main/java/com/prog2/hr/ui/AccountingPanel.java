package com.prog2.hr.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.prog2.hr.college.FormResponse;
import com.prog2.hr.college.Status;
import com.prog2.hr.service.CollegeService;

import static com.prog2.hr.app.Constants.*;

/**
 * The AccountingPanel class.
 * 	extends JPanel
 * 	Implements Compute Salary methods according to the input 
 *  	of employee ID 
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */

public class AccountingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField salaryField;
	private JTextField salaryEmplIDField;

	private CollegeService cs;

	/**
	 * Constructor creates Accounting Pannel Form,
	 * 	implements Employee Payroll Response
	 * 	with {Status, Value, Personal information}
	 * 
	 * @param cs
	 */	
	public AccountingPanel(CollegeService cs) {

		this.cs = cs;

		setLayout(null);

		JLabel setSalaryLabel = new JLabel("SET SALARY");
		setSalaryLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		setSalaryLabel.setBounds(168, 221, 126, 24);
		add(setSalaryLabel);

		JLabel setSalaryForEmplLabel = new JLabel("for Employee ID: ");
		setSalaryForEmplLabel.setBounds(24, 256, 112, 24);
		add(setSalaryForEmplLabel);

		JLabel lblCalculatedSalary = new JLabel("Calculated Salary: ");
		lblCalculatedSalary.setBounds(24, 286, 133, 14);
		add(lblCalculatedSalary);

		salaryField = new JTextField();
		salaryField.setColumns(10);
		salaryField.setBounds(159, 288, 256, 20);
		salaryField.setEditable(false);
		add(salaryField);

		salaryEmplIDField = new JTextField();
		salaryEmplIDField.setColumns(10);
		salaryEmplIDField.setBounds(159, 256, 256, 20);
		add(salaryEmplIDField);

		JButton calcSalaryButton = new JButton("Calculate Salary");
		calcSalaryButton.setBounds(168, 331, 140, 24);
		add(calcSalaryButton);

		JLabel adminStatusLabel_1 = new JLabel("STATUS:");
		adminStatusLabel_1.setBounds(24, 518, 447, 24);
		add(adminStatusLabel_1);

		JTextArea personalInfo = new JTextArea("");
		//personalInfo.setSelectionColor(Color.DARK_GRAY);
	    personalInfo.setLineWrap(true);
	    personalInfo.setWrapStyleWord(true);
	    personalInfo.setBounds(10, 10, 400, 200);
	    personalInfo.setEnabled(false);
	    personalInfo.setBackground(Color.lightGray);
	    personalInfo.setDisabledTextColor(Color.BLACK);
	    JScrollPane spPersonalInfo = new JScrollPane(personalInfo);
	    spPersonalInfo.setBounds(24, 370, 450, 140);
	    add(spPersonalInfo);

		/** 
		 * Calculate Salary 
		 */
		calcSalaryButton.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseClicked(MouseEvent e) {
				FormResponse<String> fResp = new FormResponse<>();
				fResp = cs.getEmployeePayrollResponse(salaryEmplIDField.getText());
				adminStatusLabel_1.setText(fResp.getStatus().toString());
				if (fResp.getStatus() == Status.DONE) {
					salaryField.setText(fResp.getValue());
					//messageLabel.setText(fResp.getMessage());
					personalInfo.setText(fResp.getMessage());
				} else {
					personalInfo.setText(EMPTY);
					salaryField.setText(EMPTY);
				}
			}
		});
	}
}
