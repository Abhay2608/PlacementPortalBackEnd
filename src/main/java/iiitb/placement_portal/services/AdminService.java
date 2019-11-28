package iiitb.placement_portal.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iiitb.placement_portal.entity.Admin;
import iiitb.placement_portal.entity.Student;
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
	


}
