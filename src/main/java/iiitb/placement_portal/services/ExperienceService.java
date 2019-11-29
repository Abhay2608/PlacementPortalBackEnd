package iiitb.placement_portal.services;


import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iiitb.placement_portal.entity.Experience;
import iiitb.placement_portal.dto.FilterDTO;
import iiitb.placement_portal.repository.ExperienceRepository;


@Service
public class ExperienceService {
	
	@Autowired
	private ExperienceRepository experienceRepository;
	
	
	public boolean addExperience(Experience experience) {
		boolean res=true;
		try {
			experienceRepository.save(experience);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	

	public ArrayList<Experience> getAllExperiences()
	{
		
		ArrayList<Experience> experiences=new ArrayList<Experience>();
		Iterable<Experience> iterable=experienceRepository.findAll();
		Iterator<Experience> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			experiences.add(iterator.next());
		}
		return experiences;
	}
	
	public ArrayList<Experience> getExperiencesByFilters(FilterDTO filter)
	{
		try {
//		System.out.println(filter);
		
		//fetch all experiences
		ArrayList<Experience> experiences=new ArrayList<>();
		Iterable<Experience> iterable=experienceRepository.findAll();
		Iterator<Experience> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			experiences.add(iterator.next());
		}
		
//		System.out.println(experiences);
//		System.out.println("check for company filter");
		ArrayList<Experience> expCompany=new ArrayList<>();
		if(filter.getCompanyId()!=null)
		{
			for(Integer i=0;i<experiences.size();i++)
			{
//				System.out.println(filter.getCompanyId() + "   " + experiences.get(i).getCompany().getId());
				if(filter.getCompanyId() == experiences.get(i).getCompany().getId())
				{
					expCompany.add(experiences.get(i));
				}
			}
		}
		else
		{
			expCompany=new ArrayList<Experience>(experiences);
		}
//		System.out.println("check for type filter");
//		System.out.println(expCompany);
//		System.out.println("check for type filter");
		ArrayList<Experience> expType=new ArrayList<>();
		if(filter.getType()!=null)
		{
			for(Integer i=0;i<expCompany.size();i++)
			{
				if(filter.getType().equals(expCompany.get(i).getType()))
				{
//					System.out.println(filter.getType());
//					System.out.println(expCompany.get(i).getType());
					expType.add(expCompany.get(i));
				}
			}
		}
		else
		{
			expType=new ArrayList<Experience>(expCompany);
		}
		
//		System.out.println(expType);
//		System.out.println("check for year filter");
		ArrayList<Experience> expYear=new ArrayList<>();
		if(filter.getYear()!=null)
		{
			for(Integer i=0;i<expType.size();i++)
			{
				if(filter.getYear().equals(expType.get(i).getYear()))
				{
					expYear.add(expType.get(i));
				}
			}
		}
		else
		{
			expYear=new ArrayList<Experience>(expType);
		}
		
//		System.out.println(expYear);
		return expYear;
		
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Experience getExperienceById(Integer id) {
		return experienceRepository.findById(id).get();
	}
		
		




}