package iiitb.placement_portal.services;

import java.util.Date;
import java.util.Optional;

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
import java.util.ArrayList;
import java.util.Calendar;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private iiitb.placement_portal.services.StorageService storageService;
	@Autowired
	private CompanyParticipationRepository companyParticipationRepository;
	
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
	
	public boolean authenticateStudent(Student student) {
		boolean res = false;
		try {
			Student stu = studentRepository.findByRollNo(student.getRollNo());
			if(stu.getPassword().equals(student.getPassword())) {
				res = true;
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return res;
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
			Student studentObj = studentRepository.findByRollNo(student.getRollNo());
			student = studentObj;
			studentRepository.save(studentObj);
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
		}
		return res;
	}
}
