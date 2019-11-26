package iiitb.placement_portal.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import com.fasterxml.jackson.annotation.JsonFormat;
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
	private CompanyContacts contact[];
	private String jd;
	private Date opentime;
	private Date closetime;
	private String courseRequirement[];
	private String streamRequirement[];
	private float cgpaRequired;
	private boolean type[];	//summer intern, intern, full time, I+F
	
	public Company(Integer id, String name, CompanyContacts[] contact, String jd, Date opentime, Date closetime,
			String[] courseRequirement, String[] streamRequirement, float cgpaRequired, boolean[] type) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.jd = jd;
		this.opentime = opentime;
		this.closetime = closetime;
		this.courseRequirement = courseRequirement;
		this.streamRequirement = streamRequirement;
		this.cgpaRequired = cgpaRequired;
		this.type = type;
	}
	
}
