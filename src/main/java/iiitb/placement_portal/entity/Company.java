package iiitb.placement_portal.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private CompanyContacts contact[];
	private String jd;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date opentime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date closetime;
	private String courseRequirement[];
	private String streamRequirement[];
	private float cgpaRequired;
	private boolean type[];	//summer intern, intern, full time, I+F
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
	public CompanyContacts[] getContact() {
		return contact;
	}
	public void setContact(CompanyContacts[] contact) {
		this.contact = contact;
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
	public String[] getCourseRequirement() {
		return courseRequirement;
	}
	public void setCourseRequirement(String[] courseRequirement) {
		this.courseRequirement = courseRequirement;
	}
	public String[] getStreamRequirement() {
		return streamRequirement;
	}
	public void setStreamRequirement(String[] streamRequirement) {
		this.streamRequirement = streamRequirement;
	}
	public float getCgpaRequired() {
		return cgpaRequired;
	}
	public void setCgpaRequired(float cgpaRequired) {
		this.cgpaRequired = cgpaRequired;
	}
	public boolean[] getType() {
		return type;
	}
	public void setType(boolean[] type) {
		this.type = type;
	}
}
