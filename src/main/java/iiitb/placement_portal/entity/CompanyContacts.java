package iiitb.placement_portal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

@Entity
public class CompanyContacts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer companyId;
	private String name;
	private String phone;
	private String mobile;
	private String email;
	private String designation;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public CompanyContacts() {
		
	}
	@Override
	public String toString() {
		return "CompanyContacts [id=" + id + ", companyId=" + companyId + ", name=" + name + ", phone=" + phone
				+ ", mobile=" + mobile + ", email=" + email + ", designation=" + designation + "]";
	}
	public CompanyContacts(Integer id, Integer companyId, String name, String phone, String mobile, String email,
			String designation) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.name = name;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.designation = designation;
	}
	
	
	
}
