package iiitb.placement_portal.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iiitb.placement_portal.entity.Experience;
import iiitb.placement_portal.dto.FilterDTO;
import iiitb.placement_portal.services.ExperienceService;
@CrossOrigin( origins = "*" )
@RestController
@RequestMapping("/experience")
public class ExperienceController {
	
	
	@Autowired
	private ExperienceService experienceService;

	@GetMapping
	public ResponseEntity<ArrayList<Experience>> getAllExperiences(){
		return new ResponseEntity<>(experienceService.getAllExperiences(),HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<String> addExperience(@RequestBody Experience experience) {
		if(experienceService.addExperience(experience)==true) {
			return new ResponseEntity<>("experience added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Experience> getExperienceById(@PathVariable("id") int id){
		return new ResponseEntity<>(experienceService.getExperienceById(id),HttpStatus.OK);
	}
	
	@PostMapping("/getExperiencesByFilters")
	public ResponseEntity<ArrayList<Experience>> addExperience(@RequestBody FilterDTO filter) {
		return new ResponseEntity<>(experienceService.getExperiencesByFilters(filter),HttpStatus.OK);
	}
}