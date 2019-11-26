package iiitb.placement_portal.services;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iiitb.placement_portal.entity.CompanyContacts;
import iiitb.placement_portal.repository.CompanyContactsRepository;
import lombok.extern.slf4j.Slf4j;

@Service
public class CompanyContactsService {
	
	@Autowired
	private  CompanyContactsRepository companyContactsRepository;
	
	
	public boolean addCompanyContacts(CompanyContacts companyContacts) {
		boolean res=true;
		if(companyContacts.getName()==null) {
			return false;
		}
		try {
			companyContactsRepository.save(companyContacts);
		}catch(Exception e) {
			res=false;
		}
		return res;
	}
	

	public ArrayList<CompanyContacts> getAllCompanyContacts(){
		ArrayList<CompanyContacts> companyContacts=new ArrayList<CompanyContacts>();
		Iterable<CompanyContacts> iterable=companyContactsRepository.findAll();
		Iterator<CompanyContacts> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			companyContacts.add(iterator.next());
		}
		return companyContacts;
	}
	
	public CompanyContacts getCompanyContactsById(Integer id) {
		return companyContactsRepository.findById(id).get();
	}
		
	public boolean removeCompanyContacts(Integer id) {
		companyContactsRepository.deleteById(id);
		return true;
	}
	
	public CompanyContacts updateCompanyContacts(CompanyContacts companyContacts) {
		if(companyContactsRepository.save(companyContacts) != null) {
			return companyContacts;
		}
		else return null;
	}
	


}