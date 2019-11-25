package iiitb.placement_portal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.entity.Student;
import iiitb.placement_portal.services.StudentService;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping("/student")
public class StudentController {

		@Autowired
		private StudentService studentService;
		
		@RequestMapping(method=RequestMethod.POST,value="/register")
		public ResponseEntity<String> registerStudent(@RequestBody Student student){
			if(studentService.registerStudent(student)==true) {
				return new ResponseEntity<>("registration successfull",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>("registration denied",HttpStatus.BAD_REQUEST);
			}
		}
		
		@RequestMapping(method=RequestMethod.POST,value="/login")
		public ResponseEntity<String> authenticateStudent(@RequestBody Student student){
			if(studentService.authenticateStudent(student)==true)
			{
				return new ResponseEntity<>("login success",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>("login denied",HttpStatus.UNAUTHORIZED);
			}
		}
		
		@RequestMapping(method=RequestMethod.PUT,value="/update")
		public ResponseEntity<String> updateStudentDetails(@RequestBody Student student){
			if(studentService.updateStudentDetails(student) == true)
			{
				return new ResponseEntity<>("student details updated successfully",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>("student details updation failed",HttpStatus.BAD_REQUEST);
			}
		}
		
		@RequestMapping(method=RequestMethod.PUT,value="/updatePassword")
		public ResponseEntity<String> updatePassword(@RequestBody Student student){
			if(studentService.updatePassword(student) == true)
			{
				return new ResponseEntity<>("Password updated successfully",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>("Password updation failed",HttpStatus.BAD_REQUEST);
			}
		}
		
		@RequestMapping(method=RequestMethod.POST,value="/apply")
		public ResponseEntity<String> applyCompany(@RequestBody Student student, Company company, boolean appliedFor[]){
			if(studentService.applyCompany(student, company, appliedFor) == true)
			{
				return new ResponseEntity<>("Company application successfull",HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<>("Company application failed",HttpStatus.BAD_REQUEST);
			}
		}
}
