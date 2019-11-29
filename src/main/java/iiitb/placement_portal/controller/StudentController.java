package iiitb.placement_portal.controller;


import iiitb.placement_portal.dto.CompanyDTO;
import iiitb.placement_portal.repository.CompanyRepository;
import iiitb.placement_portal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.entity.Student;
import iiitb.placement_portal.services.EMailService;
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
		@Autowired
		private StudentRepository studentRepository;
		@Autowired
		private CompanyRepository companyRepository;
		@Autowired
		private EMailService emailService;

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
		public ResponseEntity<String> updatePassword(@RequestParam("id")Integer id,@RequestParam("oldPassword")String oldPassword,@RequestParam("newPassword")String newPassword){
			Student student = studentRepository.findById(id).get();
			/*System.out.println(oldPassword);
			System.out.println(student);
			System.out.println(newPassword);*/
			if(student.getPassword().equals(oldPassword)){
				student.setPassword(newPassword);
				if(studentService.updatePassword(student) == true)
				{
					return new ResponseEntity<>("Password updated successfully",HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<>("Password updation failed",HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<>("Student not found",HttpStatus.BAD_REQUEST);
		}
		
		@RequestMapping(method=RequestMethod.POST,value="/apply")
		public ResponseEntity<String> applyCompany(@RequestParam("cv") MultipartFile cv,@RequestParam(value = "studentId") Integer studentId, @RequestParam(value = "companyId") Integer companyId,@RequestParam(value = "appliedFor") String aF){

			Gson gson = new Gson();
			//Student student = gson.fromJson(s, Student.class);
			//Company company = gson.fromJson(c, Company.class);

			Student student = studentRepository.findById(studentId).get();
			System.out.println("company ID :" + companyId);
			Company company = companyRepository.findById(companyId).get();
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

		@RequestMapping(method=RequestMethod.DELETE,value="/withdrawApplication")
		public ResponseEntity<String> withdrawApplication(@RequestParam(value="studentId")Integer studentId,@RequestParam(value="companyId")Integer companyId){
			if(studentService.withdrawApplication(studentId,companyId) == true){
				return new ResponseEntity<>("Application withdrawn",HttpStatus.OK);
			}
			else{
				return new ResponseEntity<>("Application withdraw failed",HttpStatus.BAD_REQUEST);
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

		@RequestMapping(method=RequestMethod.GET,value="/viewUpcomingCompanies/{id}")
		public ResponseEntity<ArrayList<CompanyDTO>> viewUpcomingCompanies(@PathVariable("id")Integer id){
			return new ResponseEntity<ArrayList<CompanyDTO>>(studentService.viewUpcomingCompanies(id),HttpStatus.OK);
		}

		@RequestMapping(method=RequestMethod.GET,value = "/viewAppliedCompanies/{id}")
		public ResponseEntity<ArrayList<CompanyDTO>> viewAppliedCompanies(@PathVariable("id")Integer id){
			return new ResponseEntity<ArrayList<CompanyDTO>>(studentService.viewAppliedCompanies(id),HttpStatus.OK);
		}

		@RequestMapping(method=RequestMethod.GET,value="/resetPassword/{rollNo}")
		public Student resetPassword(@PathVariable String rollNo) {
			System.out.println("resetPassword method called");
			Student studentDetails = studentService.getStudentByRollNo(rollNo);
			if (studentDetails == null)
				return null;
			String secret = generateRandomPassword();
			studentDetails.setPassword(secret);
			studentDetails.setRollNo(rollNo);
			studentService.updateStudentDetails(studentDetails);
			emailService.sendEmail(studentDetails.getEmail(), 
					"Your new Password for Placement Portal.", 
					"Hello " + studentDetails.getName() 
					+"\n We have recieved your request for resetting password."
					+ "\n Your new password is " + studentDetails.getPassword()
					+ ". \n Use this password to login in Placement Portal. \n");
			return studentDetails;
		}
		
		public String generateRandomPassword() {
			String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
			StringBuilder sb = new StringBuilder(8);
			for(int i = 0; i < 8; i++) {
				int index = (int) (alphabets.length() * Math.random());
				sb.append(alphabets.charAt(index));
			}
			return sb.toString();
		}
}
