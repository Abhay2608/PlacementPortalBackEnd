package iiitb.placement_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;

import iiitb.placement_portal.services.CompanyService;
import iiitb.placement_portal.entity.*;


@CrossOrigin( origins = "*" )
@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(method=RequestMethod.GET,value="")
	public ResponseEntity<ArrayList<Company>> getAllCompanies(){
		return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<String> addCompany(@RequestBody Company company) {
		if(companyService.addCompany(company)==true) {
			return new ResponseEntity<>("company added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
}
