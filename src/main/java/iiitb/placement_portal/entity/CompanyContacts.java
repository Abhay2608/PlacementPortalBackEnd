package iiitb.placement_portal.entity;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter @Setter @NoArgsConstructor @ToString

public class CompanyContacts {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private Integer id;
	private String name;
	private Integer companyId;
	private String phone;
	private String mobile;
	private String email;
	private String designation;
	public CompanyContacts(Integer id, String name, String phone, String mobile, String email,
			String designation) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.designation = designation;
	}
}