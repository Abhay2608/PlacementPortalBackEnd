package iiitb.placement_portal.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

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
	
	public ArrayList<Company> getAllCompanies(){
		Gson gson = new Gson();
		ArrayList<Company> companies = new ArrayList<Company>();
		Iterable<Company> iterable = companyRepository.findAll();
		Iterator<Company> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			companies.add(iterator.next());
		}
		for(Company d : companies) {
			d.setContact(gson.fromJson(d.getContactInString(), new TypeToken<ArrayList<CompanyContacts>>() {}.getType()));
		}
		return companies;
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
