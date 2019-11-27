package iiitb.placement_portal.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.repository.CompanyRepository;
import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.entity.CompanyContacts;
import iiitb.placement_portal.repository.CompanyContactsRepository;
import iiitb.placement_portal.repository.CompanyRepository;
@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
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

	public boolean addCompany(Company company) {
		System.out.println(company);
		boolean res=true;
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
		}catch(Exception e) {
			System.out.println(e);
			res=false;
		}
		return res;
	}
}
