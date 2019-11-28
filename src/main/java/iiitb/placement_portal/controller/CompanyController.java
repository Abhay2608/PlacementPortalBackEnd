package iiitb.placement_portal.controller;

import iiitb.placement_portal.dto.CompanyDTO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

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
	
	//@GetMapping
	@RequestMapping(method=RequestMethod.GET,value="/getAllCompanies")
	public ResponseEntity<ArrayList<CompanyDTO>> getAllCompanies(){
		return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
	}
	
	//@PostMapping
	@RequestMapping(method=RequestMethod.POST,value="/addCompany")
	public ResponseEntity<String> addCompany(@RequestParam("jd") MultipartFile jd,@RequestParam(value = "company") String c) {
		Gson gson = new Gson();
		Company company = gson.fromJson(c, Company.class); 
		
		String extension = FilenameUtils.getExtension(jd.getOriginalFilename());
		if(!(extension.equals("pdf") )) {
			return new ResponseEntity<>("please upload pdf file",HttpStatus.BAD_REQUEST);
		}

		if(companyService.addCompany(company,jd,extension,"jd")==true) {
			return new ResponseEntity<>("company added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method=RequestMethod.DELETE,value="/deleteCompany/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable("id")Integer id){
		if(companyService.deleteCompany(id) == true)
		{
			return new ResponseEntity<>("company removed successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("company remove failed",HttpStatus.BAD_REQUEST);
		}
	}
}
