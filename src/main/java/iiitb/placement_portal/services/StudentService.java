package iiitb.placement_portal.services;

import java.util.*;
import java.lang.String;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import iiitb.placement_portal.dto.CompanyDTO;
import iiitb.placement_portal.entity.CompanyContacts;
import jdk.nashorn.internal.runtime.regexp.joni.ast.BackRefNode;
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
	private CompanyRepository companyRepository;
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
			student.setBanned(false);
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
	
	public boolean applyCompany(Student student, Company company, Boolean appliedFor[],MultipartFile file, String extension,String fileType) {
		boolean res=true;
		if(file==null) {
			return false;
		}
		return checkPolicy(student,company,appliedFor,file,extension,fileType);

		/*if(course && stream && date && appliedFlag && cgpa) {

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
		}*/
		
	}

	public boolean checkPolicy(Student student, Company company, Boolean appliedFor[],MultipartFile file, String extension,String fileType){
		CompanyParticipation companyParticipationCheck = companyParticipationRepository.findByStudentIdAndCompanyId(student.getId(), company.getId());
		if(companyParticipationCheck != null){
			System.out.println("already applied");
			return false;
		}
		//System.out.println("inside policy");
		/*for(int i=0;i<appliedFor.length;i++){
			System.out.println(appliedFor[i]);
		}*/
		/*System.out.println(appliedFor[0]);System.out.println(appliedFor[1]);System.out.println(appliedFor[2]);System.out.println(appliedFor[3]);

		System.out.println(student.toString());
		System.out.println();
		System.out.println(company.toString());*/
		ArrayList<Boolean> companyType = company.getType();
		boolean applyflag = true;

		//policy for placement start
		for(int i=0;i<appliedFor.length;i++){
			if(companyType.get(i) != appliedFor[i].booleanValue()){
				applyflag = false;
				break;
			}
		}
		//policy for placement end
		if(applyflag == true && student.isBanned() == false){
			boolean res;
			Student stu=studentRepository.findByRollNo(student.getRollNo());
			String documentLink = fileType + "_" + student.getRollNo() + "." + extension;
			res=storageService.addFile(documentLink, file);
			stu.setCv(documentLink);
			studentRepository.save(stu);

			CompanyParticipation companyParticipation = new CompanyParticipation();
			companyParticipation.setAppliedFor(appliedFor);
			companyParticipation.setCompanyId(company.getId());
			companyParticipation.setStudentId(student.getId());
			companyParticipationRepository.save(companyParticipation);
			return true;
		}
		return false;
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

	public ArrayList<CompanyDTO> viewUpcomingCompanies(Integer id){
		Student student = studentRepository.findById(id).get();
		System.out.println("Inside viewUpcomingCompanies(Integer id): " + student.getRollNo());
		return viewUpcomingCompanies(student.getRollNo());
	}

	public ArrayList<CompanyDTO> viewUpcomingCompanies(String rollNo){
		ArrayList<CompanyDTO> resultantCompanyDTO = new ArrayList<>();
		Student dbStudent = studentRepository.findByRollNo(rollNo.toUpperCase());
		if(dbStudent == null){
			dbStudent = studentRepository.findByRollNo(rollNo.toLowerCase());
		}
		ArrayList<CompanyDTO> companyDTO = companyService.getAllCompanies();

		System.out.println(companyDTO.size());
		ArrayList<Company> companies = new ArrayList<>();
		for(CompanyDTO c: companyDTO){
			companies.add(c.getCompany());
		}

		for(Company company : companies){

			StringBuilder stringBuilder = new StringBuilder();
			Boolean isEligible = false;
			if(checkRequirement(dbStudent,company,stringBuilder) == true){
				System.out.println(company.getName());
				if(stringBuilder.toString().length() == 0)	{
					isEligible = true;
				}

				company.setContact(null);
				company.setContactInString(null);

				//companyDTO.add(new CompanyDTO(isEligible, stringBuilder.toString(), company));
				resultantCompanyDTO.add(new CompanyDTO(isEligible, stringBuilder.toString(), "", company));
			}
		}
		return  resultantCompanyDTO;
	}

	private boolean checkRequirement(Student student, Company company,StringBuilder stringBuilder){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateobj = new Date();
		if(company.getClosetime().after(dateobj)) {

			if(student.isBanned() == true){
				stringBuilder.append("Student banned for placements.\n");
			}

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
				//System.out.println("tmp:  " + tmp.toLowerCase() + "  student:  " + student.getCourse().toLowerCase() + "\n");
				//System.out.println(tmp.toLowerCase().equals(student.getCourse().toLowerCase()));
				if(tmp.toLowerCase().equals(student.getCourse().toLowerCase())){
					course = true;
					//System.out.println(course);
					break;
				}
			}
			if(course == false){
				stringBuilder.append("Course criteria not satisfied.\n");
			}

			for(String tmp : streamRequirements){
				if(tmp.toLowerCase().equals(student.getStream().toLowerCase())){
					stream = true;
					break;
				}
			}
			if(stream == false){
				stringBuilder.append("Stream criteria not satisfied.");
			}
			return true;
		}
		return false;
	}

	public ArrayList<CompanyDTO> viewAppliedCompanies(Integer id){
		//Integer id1 = Integer.parseInt(id);
		ArrayList<CompanyDTO> companyDTOS = new ArrayList<>();
		ArrayList<CompanyParticipation> companyParticipation = companyParticipationRepository.findAllByStudentId(id);
		for(CompanyParticipation companyParticipation1 : companyParticipation){
			Company company = companyRepository.findById(companyParticipation1.getCompanyId()).get();
			StringBuilder stringBuilder = new StringBuilder();
			Boolean appliedFor[] = companyParticipation1.getAppliedFor();
			if(appliedFor[0] == true)	stringBuilder.append("summer,");
			if(appliedFor[1] == true)	stringBuilder.append("intern,");
			if(appliedFor[2] == true)	stringBuilder.append("fulltime,");
			if(appliedFor[3] == true)	stringBuilder.append("intern and fulltime,");
			if(stringBuilder.length() > 0) {
				stringBuilder.setLength(stringBuilder.length() - 1);
			}
			System.out.println("Inside viewAppliedCompanies: " + stringBuilder);

			company.setContact(null);
			company.setContactInString(null);

			CompanyDTO companyDTO1 = new CompanyDTO(true, "", stringBuilder.toString(), company);
			System.out.println("CompanyDTO: " + companyDTO1.getComingFor());
			companyDTOS.add(companyDTO1);
		}
		return companyDTOS;
	}
}
