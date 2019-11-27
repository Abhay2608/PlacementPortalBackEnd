package iiitb.placement_portal.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Experience {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	//	student object
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="STUDENTID", referencedColumnName="ID")
    })
	private Student student;
	
	//	company object
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="COMPANYID", referencedColumnName="ID")
    })
	private Company company;
	private String type;
	private String date;
	private String year;
	private String title;
	@Column(length=10485760)
	private String body;
	
	public Experience() {
		super();
	}

	public Experience(Integer id, Student student, Company company, String type, String date, String year,
			String title, String body) {
		super();
		this.id = id;
		this.student = student;
		this.company = company;
		this.type = type;
		this.date = date;
		this.year = year;
		this.title = title;
		this.body = body;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Experience [id=" + id + ", student=" + student + ", company=" + company + ", type=" + type + ", date="
				+ date + ", year=" + year + ", title=" + title + "]";
	}

	
	
}