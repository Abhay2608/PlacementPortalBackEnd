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
	private String domain;
	private String profile;

	public Company(Integer id, String name, String jd, Date opentime, Date closetime, float cgpaRequired, String contactInString, ArrayList<String> courseRequirement, ArrayList<String> streamRequirement, ArrayList<CompanyContacts> contact, ArrayList<Boolean> type, String domain, String profile) {
		this.id = id;
		this.name = name;
		this.jd = jd;
		this.opentime = opentime;
		this.closetime = closetime;
		this.cgpaRequired = cgpaRequired;
		this.contactInString = contactInString;
		this.courseRequirement = courseRequirement;
		this.streamRequirement = streamRequirement;
		this.contact = contact;
		this.type = type;
		this.domain = domain;
		this.profile = profile;
	}

	public Company() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public Date getOpentime() {
		return opentime;
	}

	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}

	public Date getClosetime() {
		return closetime;
	}

	public void setClosetime(Date closetime) {
		this.closetime = closetime;
	}

	public float getCgpaRequired() {
		return cgpaRequired;
	}

	public void setCgpaRequired(float cgpaRequired) {
		this.cgpaRequired = cgpaRequired;
	}

	public String getContactInString() {
		return contactInString;
	}

	public void setContactInString(String contactInString) {
		this.contactInString = contactInString;
	}

	public ArrayList<String> getCourseRequirement() {
		return courseRequirement;
	}

	public void setCourseRequirement(ArrayList<String> courseRequirement) {
		this.courseRequirement = courseRequirement;
	}

	public ArrayList<String> getStreamRequirement() {
		return streamRequirement;
	}

	public void setStreamRequirement(ArrayList<String> streamRequirement) {
		this.streamRequirement = streamRequirement;
	}

	public ArrayList<CompanyContacts> getContact() {
		return contact;
	}

	public void setContact(ArrayList<CompanyContacts> contact) {
		this.contact = contact;
	}

	public ArrayList<Boolean> getType() {
		return type;
	}

	public void setType(ArrayList<Boolean> type) {
		this.type = type;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
}
