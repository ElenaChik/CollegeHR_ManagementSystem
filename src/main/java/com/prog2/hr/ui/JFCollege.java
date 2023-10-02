package com.prog2.hr.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.JFrame;

import com.prog2.hr.service.CollegeService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.prog2.hr.app.Constants.*;

/**
 * The JFCollege class.
 * 	extends JFrame
 *  Implements ActionListener, implements Frame with tab Panels 
 *  	and The Button Save that serialize all College object
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class JFCollege extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JFrame frame;	
	private CollegeService cs;

	public JFCollege(CollegeService cs) {

		this.cs = cs; 

		frame = new JFrame(APPLICATION_NAME);
		JTabbedPane tabPanel=new JTabbedPane();  
		tabPanel.setBounds(10,11,500,600);  
		frame.getContentPane().add(tabPanel);  

		tabPanel.add(FRAME_DEPARTMENT, new DepartmentPanel(this.cs));  

		tabPanel.add(FRAME_EMPLOYEE, new EmployeePanel(this.cs));

		tabPanel.add(FRAME_ACCOUNTING, new AccountingPanel(this.cs));

		frame.setSize(548,701);  
		frame.getContentPane().setLayout(null);

		//Serialize College Object when EXIT Button clicked
		createSaveButton();
	}
	
	private void createSaveButton() {

		Button saveButton = new Button(BUTTON_SAVE);
		saveButton.setActionCommand("");
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CollegeService.serializeData(PATH, cs.getCollege());
				System.out.println("college was serialized ");
			}
		});
		saveButton.setBackground(Color.LIGHT_GRAY);
		saveButton.setForeground(Color.BLACK);
		saveButton.setFont(new Font("Dialog", Font.BOLD, 11));
		saveButton.setBounds(185, 625, 157, 21);
		frame.getContentPane().add(saveButton);
		frame.setVisible(true); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}