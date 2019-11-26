package iiitb.placement_portal.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import iiitb.placement_portal.entity.CompanyContacts;
import iiitb.placement_portal.services.CompanyContactsService;

	@CrossOrigin( origins = "*" )
	@RestController
	@RequestMapping("/companyContacts")
	public class CompanyContactsController {
		
		
		@Autowired
		private CompanyContactsService companyContactsService;

		@GetMapping
		public ResponseEntity<ArrayList<CompanyContacts>> getAllCompanyContactss(){
			return new ResponseEntity<>(companyContactsService.getAllCompanyContacts(),HttpStatus.OK);		
		}
		
		@PostMapping
		public ResponseEntity<String> addCompanyContacts(@RequestBody CompanyContacts companyContacts) {
			if(companyContactsService.addCompanyContacts(companyContacts)==true) {
				return new ResponseEntity<>("companyContacts added",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<CompanyContacts> getCompanyContactsById(@PathVariable("id") int id){
			return new ResponseEntity<>(companyContactsService.getCompanyContactsById(id),HttpStatus.OK);
		}
		
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<String> deleteCompanyContacts(@PathVariable("id") int id) {
			if(companyContactsService.removeCompanyContacts(id)) {
				return new ResponseEntity<>("companyContacts removed",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
			}
		}
		
		@PutMapping
		public ResponseEntity<String> updateCompanyContacts(@RequestBody CompanyContacts companyContacts) {
			if(companyContactsService.updateCompanyContacts(companyContacts) != null) {
				return new ResponseEntity<>("companyContacts updated",HttpStatus.OK);			
			}else {
				return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);			
			}			
		}
	}