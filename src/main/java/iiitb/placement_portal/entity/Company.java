package iiitb.placement_portal.entity;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString

public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String jd;
	private Date opentime;
	private Date closetime;
	private float cgpaRequired;
	private String contactInString;
	private ArrayList<String> courseRequirement;
	private ArrayList<String> streamRequirement;
	private ArrayList<CompanyContacts> contact;
	private ArrayList<Boolean> type;	//summer intern, intern, full time, I+F

	public Company(Integer id, String name, ArrayList<CompanyContacts> contact, String jd, Date opentime,
			Date closetime, ArrayList<String> courseRequirement, ArrayList<String> streamRequirement,
			float cgpaRequired, ArrayList<Boolean> type, String contactInString) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.contactInString = contactInString;
		this.jd = jd;
		this.opentime = opentime;
		this.closetime = closetime;
		this.courseRequirement = courseRequirement;
		this.streamRequirement = streamRequirement;
		this.cgpaRequired = cgpaRequired;
		this.type = type;
	}	
}
