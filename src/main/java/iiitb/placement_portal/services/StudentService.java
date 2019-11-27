package iiitb.placement_portal.services;

import java.util.*;
import java.lang.String;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import iiitb.placement_portal.dto.UpcomingCompanyDTO;
import iiitb.placement_portal.entity.CompanyContacts;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.entity.CompanyParticipation;
import iiitb.placement_portal.entity.Student;
import iiitb.placement_portal.repository.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private iiitb.placement_portal.services.StorageService storageService;
	@Autowired
	private CompanyParticipationRepository companyParticipationRepository;
	@Autowired
	private CompanyService companyService;

	public ArrayList<Student> getAllStudents(){
		ArrayList<Student> students = new ArrayList<Student>();
		Iterable<Student> iterable = studentRepository.findAll();
		Iterator<Student> iterator = iterable.iterator();
		while(iterator.hasNext()) {
			students.add(iterator.next());
		}

		return students;
	}
	public boolean registerStudent(Student student) {
		boolean res=true;
		try {
			studentRepository.save(student);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	
	public Student getStudentByRollNo(String rollNo){
		Student student=null;
		try {
			student=studentRepository.findByRollNo(rollNo);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return student;
	}
	
	public Student authenticateStudent(Student student) {
		boolean res = false;
		try {
			String rollNoLower = student.getRollNo().toLowerCase();
			String rollNoUpper = student.getRollNo().toUpperCase();
			Student stu = studentRepository.findByRollNo(rollNoLower);
			if(stu == null){
				stu = studentRepository.findByRollNo(rollNoUpper);
			}
			if(stu.getPassword().equals(student.getPassword())) {
				res = true;
				return stu;
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public boolean updatePassword(Student student) {
		boolean res = false;
		try {
			Student stu = studentRepository.findByRollNo(student.getRollNo());
			stu.setPassword(student.getPassword());
			studentRepository.save(stu);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return res;
	}
	
	public boolean updateStudentDetails(Student student) {
		boolean res = true;
		try {
			//Student studentObj = studentRepository.findByRollNo(student.getRollNo());
			//student = studentObj;
			studentRepository.save(student);
		}
		catch(Exception e) {
			res = false;
			System.out.println(e);
		}
		return res;
	}
	
	public boolean applyCompany(Student s, Company c, Boolean appliedFor[],MultipartFile file, String extension,String fileType) {
		boolean res=true;
		if(file==null) {
			return false;
		}
	
		ArrayList<String> courseRequirement = c.getCourseRequirement();
		ArrayList<String> streamRequirement = c.getStreamRequirement();
		Date closetime = c.getClosetime();
		ArrayList<Boolean> type = c.getType();
		boolean course=false,stream=false,date=false,appliedFlag=false,cgpa=false;
		
		if(c.getCgpaRequired() <= s.getCgpa()) {
			cgpa = true;
		}
		for(String tmp : courseRequirement) {
			if(tmp.equals(s.getCourse())) {
				course = true;
				break;
			}
		}
		
		for(String tmp : streamRequirement) {
			if(tmp.equals(s.getStream())) {
				stream = true;
				break;
			}
		}
		
		for(int i=0;i<type.size();i++) {
			appliedFor[i] = type.get(i) & appliedFor[i];
			if(appliedFor[i] == true) {
				appliedFlag = true;
			}
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date dateobj = new Date();
		if(closetime.after(dateobj)) {
			date = true;
		}
		if(course && stream && date && appliedFlag && cgpa) {

			Student stu=studentRepository.findByRollNo(s.getRollNo());
			String documentLink = fileType + "_" + s.getRollNo() + "." + extension;
			res=storageService.addFile(documentLink, file);
			stu.setCv(documentLink);
			studentRepository.save(stu);

			CompanyParticipation cp = new CompanyParticipation();
			cp.setAppliedFor(appliedFor);
			cp.setCompanyId(c.getId());
			cp.setStudentId(s.getId());
			companyParticipationRepository.save(cp);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean addFile(String rollNo,MultipartFile file, String extension, String type) {
		boolean res=true;
		if(file==null) {
			return false;
		}
		try {
			Student stu=studentRepository.findByRollNo(rollNo);
			String documentLink = type + "_" + rollNo + "." + extension;
			res=storageService.addFile(documentLink, file);
			
			if(type.equals("photo")) {
				stu.setImage(documentLink);
			}else if(type.equals("cv")) {
				stu.setCv(documentLink);
			}
			studentRepository.save(stu);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}

	public ArrayList<UpcomingCompanyDTO> viewUpcomingCompanies(String rollNo){
		System.out.println(rollNo);
		Student dbStudent = studentRepository.findByRollNo(rollNo.toUpperCase());
		if(dbStudent == null){
			dbStudent = studentRepository.findByRollNo(rollNo.toLowerCase());
		}
		System.out.println(dbStudent.getRollNo());
		ArrayList<Company> companies = companyService.getAllCompanies();
		ArrayList<UpcomingCompanyDTO> upcomingCompanyDTO = new ArrayList<>();
		for(Company company : companies){
			StringBuilder stringBuilder = new StringBuilder();
			boolean isEligible = true;
			if(checkRequirement(dbStudent,company,stringBuilder,isEligible) == true){
				upcomingCompanyDTO.add(new UpcomingCompanyDTO(isEligible, stringBuilder.toString(), company));
			}
		}
		return  upcomingCompanyDTO;
	}

	private boolean checkRequirement(Student student, Company company,StringBuilder stringBuilder,boolean isEligible){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateobj = new Date();
		if(company.getClosetime().after(dateobj)) {
			boolean cgpa = false,course = false, stream = false;
			if((int)(company.getCgpaRequired()*100) > (int)(student.getCgpa()*100)){
				stringBuilder.append("CGPA criteria not satisfied.\n");
			}
			else{
				cgpa = true;
			}


			ArrayList<String> courseRequirements = company.getCourseRequirement();
			ArrayList<String> streamRequirements = company.getStreamRequirement();

			for(String tmp : courseRequirements){
				if(tmp.toLowerCase().equals(student.getCourse())){
					System.out.println("tmp:  " + tmp + "  student:  " + student.getCourse() + "\n");
					course = true;
					break;
				}
			}
			if(course == false){
				stringBuilder.append("Course criteria not satisfied.\n");
			}

			for(String tmp : streamRequirements){
				if(tmp.toLowerCase().equals(student.getStream())){
					stream = true;
					break;
				}
			}
			if(stream == false){
				stringBuilder.append("Stream criteria not satisfied.");
			}
			if(cgpa && course && stream){
				isEligible = true;
			}
			return true;
		}
		return false;
	}
}
