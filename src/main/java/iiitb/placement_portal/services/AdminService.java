package iiitb.placement_portal.services;



import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import iiitb.placement_portal.dto.StudentDTO;
import iiitb.placement_portal.entity.*;
import iiitb.placement_portal.repository.CompanyParticipationRepository;
import iiitb.placement_portal.repository.CompanyRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iiitb.placement_portal.repository.AdminRepository;
import iiitb.placement_portal.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CompanyParticipationRepository companyParticipationRepository;
	@Autowired
	private CompanyRepository companyRepository;

	public boolean authenticateAdmin(Admin admin) {
		boolean res = false;
		try {
			Admin dbAdmin = adminRepository.findByEmail(admin.getEmail());
			if(dbAdmin.getPassword().equals(admin.getPassword())) {
				res = true;
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return res;
	}

	public boolean addAdmin(Admin admin) {
		boolean res=true;
		if(admin.getEmail()==null) {
			return false;
		}
		try {
			adminRepository.save(admin);
		}catch(Exception e) {
			res=false;
			log.debug(this.getClass() + " addAdmin method - exception " + e);
		}
		return res;
	}

	public ArrayList<Admin> getAllAdmin(){
		ArrayList<Admin> admin=new ArrayList<Admin>();
		Iterable<Admin> iterable=adminRepository.findAll();
		Iterator<Admin> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			admin.add(iterator.next());
		}
		return admin;
	}
	
	public Admin getAdminById(Integer id) {
		return adminRepository.findById(id).get();
	}
		
	public boolean removeAdmin(Integer id) {
		adminRepository.deleteById(id);
		return true;
	}
	
	public Admin updateAdmin(Admin admin) {
		if(adminRepository.save(admin) != null) {
			return admin;
		}
		else return null;
	}

	public boolean banStudent(String rollNo) {
		boolean res = false;
		try{
			Student student = studentRepository.findByRollNo(rollNo.toUpperCase());
			if(student == null)	{
				student = studentRepository.findByRollNo(rollNo.toLowerCase());
			}
			student.setBanned(true);
			studentRepository.save(student);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean unbanStudent(String rollNo)	{
		boolean res = false;
		try{
			Student student = studentRepository.findByRollNo(rollNo.toUpperCase());
			if(student == null)	{
				student = studentRepository.findByRollNo(rollNo.toLowerCase());
			}
			student.setBanned(false);
			studentRepository.save(student);
			return true;
		}
		catch (Exception e)	{
			return  false;
		}
	}

	public ArrayList<Student> getAllBannedStudents(){
		ArrayList<Student> bannedStudent = new ArrayList<>();
		Iterable<Student> iterable = studentRepository.findAll();
		Iterator<Student> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			Student student = iterator.next();
			if(student.isBanned() == true){
				bannedStudent.add(student);
			}
		}
		return bannedStudent;
	}

	public ArrayList<StudentDTO> getAllAppliedStudentsForCompany(Integer id){
		ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
		ArrayList<CompanyParticipation> companyParticipations = companyParticipationRepository.findAllByCompanyId(id);

		System.out.println(companyParticipations.size());

		for(CompanyParticipation companyParticipation : companyParticipations){
			Student student = studentRepository.findById(companyParticipation.getStudentId()).get();
			Boolean af[] = companyParticipation.getAppliedFor();
			StringBuilder appliedFor = new StringBuilder();
			if(af[0])	appliedFor.append("Summer,");
			if(af[1])	appliedFor.append("Intern,");
			if(af[2])	appliedFor.append("Fulltime,");
			if(af[3])	appliedFor.append("Intern And Fulltime,");

			if(appliedFor.length() > 0){
				appliedFor.setLength(appliedFor.length()-1);
			}
			//StudentDTO studentDTO = new StudentDTO(student.getName(), student.getEmail(), student.getRollNo(), companyParticipation.getAppliedFor());
			//StudentDTO studentDTO = new StudentDTO(student.getName(), student.getEmail(), student.getRollNo(), appliedFor.toString());
			StudentDTO studentDTO = new StudentDTO(student.getName(), student.getEmail(), student.getRollNo(), appliedFor.toString(), student.getCgpa());
			studentDTOS.add(studentDTO);
		}
		return studentDTOS;
	}

	public ArrayList<Company> getAllCompaniesAdmin(){
		Gson gson = new Gson();
		ArrayList<Company> companies = new ArrayList<Company>();
		Iterable<Company> iterable = companyRepository.findAll();
		Iterator<Company> iterator=iterable.iterator();

		while(iterator.hasNext()) {
			Company company = iterator.next();
			companies.add(company);
		}

		for(Company company : companies) {
			company.setContact(gson.fromJson(company.getContactInString(), new TypeToken<ArrayList<CompanyContacts>>() {}.getType()));
		}

		return companies;
	}

	public boolean generateExcelSheet(Integer id){
		Company company = companyRepository.findById(id).get();
		StringBuilder sheetName = new StringBuilder();
		sheetName.append(company.getName() + " ");
		ArrayList<Boolean> appliedFor = company.getType();
		if(appliedFor.get(0) == true)	sheetName.append("summer ");
		if(appliedFor.get(1) == true)	sheetName.append("intern ");
		if(appliedFor.get(2) == true)	sheetName.append("fulltime ");
		if(appliedFor.get(3) == true)	sheetName.append("intern and fulltime");
		sheetName.append(".xlsx");
		String[] columns = {"Roll Number", "Name", "Email Id", "CGPA"};

		ArrayList<StudentDTO> studentDTOS = getAllAppliedStudentsForCompany(id);
		Workbook workbook = new XSSFWorkbook();
		CreationHelper creationHelper = workbook.getCreationHelper();
		Sheet sheet = workbook.createSheet("Student");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short)14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create cells
		for(int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		int rowNum = 1;
		for(StudentDTO studentDTO: studentDTOS) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(studentDTO.getRollNo());
			row.createCell(1).setCellValue(studentDTO.getName());
			row.createCell(2).setCellValue(studentDTO.getEmail());
			row.createCell(3).setCellValue(studentDTO.getCgpa());
		}

		// Resize all columns to fit the content size
		for(int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		try{
			FileOutputStream fileOut = new FileOutputStream("C://PlacementPortalExcelFiles/" + sheetName.toString());
			System.out.println("/" + sheetName.toString());
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();
			return true;
		}
		catch (Exception e){
			System.out.println(e);
			return false;
		}

	}
}
