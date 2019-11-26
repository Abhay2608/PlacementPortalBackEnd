package iiitb.placement_portal.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import iiitb.placement_portal.entity.Company;
import iiitb.placement_portal.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public ArrayList<Company> getAllCompanies(){
		ArrayList<Company> companies = new ArrayList<Company>();
		Iterable<Company> iterable = companyRepository.findAll();
		Iterator<Company> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			companies.add(iterator.next());
		}
		return companies;
	}
	public boolean addCompany(Company company) {
		boolean res=true;
		if(company.getName()==null) {
			return false;
		}
		try {
			companyRepository.save(company);
		}catch(Exception e) {
			res=false;
		}
		return res;
	}
}
