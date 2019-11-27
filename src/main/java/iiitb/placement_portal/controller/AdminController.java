package iiitb.placement_portal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import iiitb.placement_portal.entity.Admin;
import iiitb.placement_portal.services.AdminService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin( origins = "*" )
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private AdminService adminService;

	@RequestMapping(method=RequestMethod.GET,value="")
	public ResponseEntity<ArrayList<Admin>> getAllAdmins(){
		return new ResponseEntity<>(adminService.getAllAdmin(),HttpStatus.OK);		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
		if(adminService.addAdmin(admin)==true) {
			return new ResponseEntity<>("admin added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("id") int id){
		return new ResponseEntity<>(adminService.getAdminById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deleteAdmin(@PathVariable("id") int id) {
		if(adminService.removeAdmin(id)) {
			return new ResponseEntity<>("admin removed",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> updateAdmin(@RequestBody Admin admin) {
		if(adminService.updateAdmin(admin) != null) {
			return new ResponseEntity<>("admin updated",HttpStatus.OK);			
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);			
		}
		
	}
	
	
}