package iiitb.placement_portal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class CompanyParticipation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer companyId;
	private Integer studentId;
	private Boolean appliedFor[];	//summer intern, intern, full time, I+F
	private String cv;
	
	public CompanyParticipation(Integer id, Integer companyId, Integer studentId, Boolean[] appliedFor,String cv) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.studentId = studentId;
		this.appliedFor = appliedFor;
		this.cv = cv;
	}
	
	
	public CompanyParticipation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getCv() {
		return cv;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
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
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Boolean[] getAppliedFor() {
		return appliedFor;
	}
	public void setAppliedFor(Boolean[] appliedFor) {
		this.appliedFor = appliedFor;
	}
	
	
}
