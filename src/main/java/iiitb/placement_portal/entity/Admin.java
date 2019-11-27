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

public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String email;
	private String password;
	public Admin(Integer id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

}
