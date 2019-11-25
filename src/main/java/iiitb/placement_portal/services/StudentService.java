package iiitb.placement_portal.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;

import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.entity.CompanyParticipation;
import iiitb.placement_portal.entity.Student;
import iiitb.placement_portal.repository.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
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
			/*studentObj.setAddress(student.getAddress());
			studentObj.setBatch(student.getBatch());
			studentObj.setCgpa(student.getCgpa());
			studentObj.setDob(student.getDob());
			studentObj.setEmail(student.getEmail());
			studentObj.setGender(student.getGender());
			studentObj.setImage(student.getImage());
			studentObj.setName(student.getName());
			studentObj.setNationality(student.getNationality());
			studentObj.setPassword(student.getPassword());
			studentObj.setPhone(student.getPhone());
			studentObj.setPincode(student.getPincode());
			studentObj.setPlacementYear(student.getPlacementYear());
			studentObj.setState(student.getState());
			studentObj.setStream(student.getStream());
			studentObj.setUndergraduateDegree(student.getUndergraduateDegree());
			studentObj.setUndergraduatePercentage(student.getUndergraduatePercentage());
			studentObj.setUndergraduateUniversity(student.getUndergraduateUniversity());
			studentObj.setUndergraduateYear(student.getUndergraduateYear());
			studentObj.setxBoard(student.getxBoard());
			studentObj.setXiiBoard(student.getXiiBoard());
			studentObj.setXiiPercentage(student.getXiiPercentage());
			studentObj.setXiiYear(student.getXiiYear());
			studentObj.setxPercentage(student.getxPercentage());
			studentObj.setxYear(student.getxYear());*/
			studentRepository.save(studentObj);
		}
		catch(Exception e) {
			res = false;
			System.out.println(e);
		}
		return res;
	}
	
	public boolean applyCompany(Student s, Company c, boolean appliedFor[]) {
		//cv part to be done
	
		String[] courseRequirement = c.getCourseRequirement();
		String[] streamRequirement = c.getStreamRequirement();
		Date closetime = c.getClosetime();
		boolean[] type = c.getType();
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
		
		for(int i=0;i<type.length;i++) {
			appliedFor[i] = type[i] & appliedFor[i];
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
}
