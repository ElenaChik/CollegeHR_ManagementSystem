package com.prog2.hr.app;

import com.prog2.hr.service.CollegeService;
import com.prog2.hr.ui.JFCollege;

/**
 * The HRApplication class.
 * 	Create Service class object and run start Application
 * 	Then create and run UI
 *
 * @author <a href="mailto:Chailikeg@yahoo.com">Olena Chailik</a>
 */
public class HRApplication {
	

	/**
	 * Write your test code below in the main.
	 *
	 */
	public static void main(String[] args) {

		CollegeService collegeService = new CollegeService();

		collegeService.startApplication();

		JFCollege jfCollege = new JFCollege(collegeService);
	}
}