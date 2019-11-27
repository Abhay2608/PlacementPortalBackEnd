package iiitb.placement_portal.controller;


import iiitb.placement_portal.dto.UpcomingCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.entity.Student;
import iiitb.placement_portal.services.StudentService;
import org.apache.commons.io.FilenameUtils;

import javax.ws.rs.PathParam;
import java.util.ArrayList;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping("/student")
public class StudentController {

		@Autowired
		private StudentService studentService;
		
		@PostMapping(value="/register")
		public ResponseEntity<String> registerStudent(@RequestBody Student student){
			if(studentService.registerStudent(student)==true) {
				return new ResponseEntity<>("registration successfull",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>("registration denied",HttpStatus.BAD_REQUEST);
			}
		}

		@RequestMapping(method = RequestMethod.GET,value = "/getAllStudents")
		public ResponseEntity<ArrayList<Student>> getAllStudents(){
			return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
		}

		@RequestMapping(method=RequestMethod.POST,value="/login")
		public ResponseEntity<Object> authenticateStudent(@RequestBody Student student){
			Student dbStudent = studentService.authenticateStudent(student);
			if(dbStudent != null)
			{
				return new ResponseEntity<>(dbStudent,HttpStatus.OK);
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
		public ResponseEntity<String> applyCompany(@RequestParam("cv") MultipartFile cv,@RequestParam(value = "student") String s, @RequestParam(value = "company") String c,@RequestParam(value = "appliedFor") String aF){
		
			Gson gson = new Gson();
			Student student = gson.fromJson(s, Student.class); 
			Company company = gson.fromJson(c, Company.class); 
			Boolean appliedFor[] = gson.fromJson(aF, Boolean[].class); 

			String extension = FilenameUtils.getExtension(cv.getOriginalFilename());
			if(!(extension.equals("pdf") )) {
				return new ResponseEntity<>("please upload pdf file",HttpStatus.BAD_REQUEST);
			}

			
			if(studentService.applyCompany(student, company, appliedFor,cv,extension,"cv") == true)
			{
				return new ResponseEntity<>("Company application successfull",HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<>("Company application failed",HttpStatus.BAD_REQUEST);
			}
		}
		
		@RequestMapping(method = RequestMethod.POST,value="/uploadImage")
		public ResponseEntity<String> addImage(@RequestParam("rollNo") String rollNo,@RequestParam("file") MultipartFile photo) {			
			String extension = FilenameUtils.getExtension(photo.getOriginalFilename());
			if(!(extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg"))) {
				return new ResponseEntity<>("please upload image file",HttpStatus.BAD_REQUEST);
			}
			rollNo = rollNo.toLowerCase();
			if(studentService.addFile(rollNo,photo,extension,"photo")==true) {
				return new ResponseEntity<>("image added",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
			}
		}

		@RequestMapping(method = RequestMethod.POST,value="/uploadCV")
		public ResponseEntity<String> addCv(@RequestParam("rollNo")String rollNo, @RequestParam("file") MultipartFile cv) {
			String extension = FilenameUtils.getExtension(cv.getOriginalFilename());
			if(!(extension.equals("pdf") )) {
				return new ResponseEntity<>("please upload pdf file",HttpStatus.BAD_REQUEST);
			}
			rollNo = rollNo.toLowerCase();

			if(studentService.addFile(rollNo,cv,extension,"cv")==true) {
				return new ResponseEntity<>("cv added",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
			}
		}

		@RequestMapping(method=RequestMethod.GET,value="/viewUpcomingCompanies/{rollNo}")
		public ResponseEntity<ArrayList<UpcomingCompanyDTO>> viewUpcomingCompanies(@PathParam("rollNo")String rollNo){
			System.out.println(rollNo);
			rollNo = rollNo.toLowerCase();
			return new ResponseEntity<ArrayList<UpcomingCompanyDTO>>(studentService.viewUpcomingCompanies(rollNo),HttpStatus.OK);
		}

}
