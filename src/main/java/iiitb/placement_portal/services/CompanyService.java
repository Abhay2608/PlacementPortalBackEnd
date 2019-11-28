package iiitb.placement_portal.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import iiitb.placement_portal.dto.CompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.repository.CompanyRepository;
import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.entity.CompanyContacts;
import iiitb.placement_portal.entity.Student;
import iiitb.placement_portal.repository.CompanyContactsRepository;
import iiitb.placement_portal.repository.CompanyRepository;
@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private StorageService storageService;
	@Autowired
	private CompanyContactsRepository companyContactsRepository;
	
	public ArrayList<CompanyDTO> getAllCompanies(){
		Gson gson = new Gson();
		ArrayList<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
		Iterable<Company> iterable = companyRepository.findAll();
		Iterator<Company> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			Company company = iterator.next();
			StringBuilder stringBuilder = new StringBuilder();
			if(company.getType().get(0) == true)	stringBuilder.append("summer,");
			if(company.getType().get(1) == true)	stringBuilder.append("intern,");
			if(company.getType().get(2) == true)	stringBuilder.append("fulltime,");
			if(company.getType().get(3) == true)	stringBuilder.append("intern and fulltime,");
			if(stringBuilder.length() > 0){
				stringBuilder.setLength(stringBuilder.length()-1);
			}

			company.setContact(null);
			company.setContactInString(null);


			companiesDTO.add(new CompanyDTO(true, "", stringBuilder.toString(), company));
		}
		for(CompanyDTO d : companiesDTO) {
			d.getCompany().setContact(gson.fromJson(d.getCompany().getContactInString(), new TypeToken<ArrayList<CompanyContacts>>() {}.getType()));
		}
		return companiesDTO;
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
