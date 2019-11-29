package iiitb.placement_portal.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import iiitb.placement_portal.dto.CompanyDTO;
import iiitb.placement_portal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.entity.CompanyContacts;
import iiitb.placement_portal.entity.Student;
import iiitb.placement_portal.repository.CompanyRepository;
@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private StorageService storageService;
	@Autowired
	private CompanyContactsRepository companyContactsRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CompanyParticipationRepository companyParticipationRepository;

	public ArrayList<CompanyDTO> getAllCompanies(Integer id){
		System.out.println(id);
		Student student = studentRepository.findById(id).get();
		Gson gson = new Gson();
		ArrayList<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
		Iterable<Company> iterable = companyRepository.findAll();
		Iterator<Company> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			Company company = iterator.next();
			StringBuilder comingFor = new StringBuilder();
			StringBuilder nonEligibiltyReason = new StringBuilder();
			if(company.getType().get(0) == true)	comingFor.append("summer,");
			if(company.getType().get(1) == true)	comingFor.append("intern,");
			if(company.getType().get(2) == true)	comingFor.append("fulltime,");
			if(company.getType().get(3) == true)	comingFor.append("intern and fulltime,");
			if(comingFor.length() > 0){
				comingFor.setLength(comingFor.length()-1);
			}

			company.setContact(null);
			company.setContactInString(null);

			checkEligibilty(student,company,nonEligibiltyReason);
			boolean isEligible = true;
			if(nonEligibiltyReason.length() > 0){
				isEligible = false;
			}
			companiesDTO.add(new CompanyDTO(isEligible,nonEligibiltyReason.toString(), comingFor.toString(), company,isAppliedCheck(student, company),isExpiredCheck(company)));
		}
		for(CompanyDTO d : companiesDTO) {
			d.getCompany().setContact(gson.fromJson(d.getCompany().getContactInString(), new TypeToken<ArrayList<CompanyContacts>>() {}.getType()));
		}
		return companiesDTO;
	}

	public void checkEligibilty(Student student,Company company,StringBuilder nonEligibiltyReason){
		if(student.isBanned() == true){
			nonEligibiltyReason.append("Student banned for placements.\n");
		}

		boolean cgpa = false,course = false, stream = false;
		if((int)(company.getCgpaRequired()*100) > (int)(student.getCgpa()*100)){
			nonEligibiltyReason.append("CGPA criteria not satisfied.\n");
		}

		ArrayList<String> courseRequirements = company.getCourseRequirement();
		ArrayList<String> streamRequirements = company.getStreamRequirement();

		for(String tmp : courseRequirements){
			if(tmp.toLowerCase().equals(student.getCourse().toLowerCase())){
				course = true;
				break;
			}
		}
		if(course == false){
			nonEligibiltyReason.append("Course criteria not satisfied.\n");
		}

		for(String tmp : streamRequirements){
			if(tmp.toLowerCase().equals(student.getStream().toLowerCase())){
				stream = true;
				break;
			}
		}
		if(stream == false){
			nonEligibiltyReason.append("Stream criteria not satisfied.\n");
		}


	}

	public boolean addCompany(Company company,MultipartFile file, String extension,String fileType) {
		boolean res=true;
		if(file==null) {
			return false;
		}
		if(company.getName()==null) {
			return false;
		}
		try {
			Gson gson = new Gson();

			if(company.getContact()!=null) {
				String companyContactsInString = gson.toJson(company.getContact());
				company.setContactInString(companyContactsInString);

				ArrayList<CompanyContacts> companyContacts = company.getContact();
				for (CompanyContacts c : companyContacts) {
					c.setCompanyId(company.getId());
					companyContactsRepository.save(c);
				}

				company.setContact(null);
			}
			companyRepository.save(company);

			Optional<Company> c=companyRepository.findById(company.getId());
			String documentLink = fileType + "_" + c.get().getId() + "." + extension;
			res=storageService.addFile(documentLink, file);
			c.get().setJd(documentLink);
			companyRepository.save(c.get());
		}catch(Exception e) {
			System.out.println(e);
			res=false;
		}
		return res;
	}

	public boolean deleteCompany(Integer id){
		companyRepository.deleteById(id);
		return true;
	}

	public boolean isAppliedCheck(Student student,Company company){
		if(companyParticipationRepository.findByStudentIdAndCompanyId(student.getId(), company.getId()) == null){
			return false;
		}
		return true;
	}

	public boolean isExpiredCheck(Company company){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateobj = new Date();
		if(company.getClosetime().after(dateobj)) {
			return false;
		}
		return true;
	}
//	public boolean addFile(String rollNo,MultipartFile file, String extension, String type) {
//		boolean res=true;
//		if(file==null) {
//			return false;
//		}
//		try {
//			Student stu=studentRepository.findByRollNo(rollNo);
//			String documentLink = type + "_" + rollNo + "." + extension;
//			res=storageService.addFile(documentLink, file);
//			if(type.equals("photo")) {
//				stu.setImage(documentLink);
//			}else if(type.equals("cv")) {
//				stu.setCv(documentLink);
//			}
//			studentRepository.save(stu);
//		}catch(Exception e) {
//			res=false;
//		}
//		return res;
//	}
	
}
