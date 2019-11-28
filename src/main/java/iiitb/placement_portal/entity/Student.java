package iiitb.placement_portal.entity;

import java.time.Year;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String rollNo;
	private String email;
	private String password;
	private String name;
	private String phone;
	private float cgpa;
	private String stream;
	private String course;
	private String batch;
	private String placementYear;
	private String address;
	private String pincode;
	private String state;
	private String nationality;
	private String xBoard;
	private float xPercentage;
	private String xYear;
	private String xiiBoard;
	private float xiiPercentage;
	private String xiiYear;
	private String undergraduateUniversity;
	private String undergraduateDegree;
	private String undergraduateYear;
	private float undergraduatePercentage;
	private String gender;
	private Date dob;
	private String image;
	private String cv;
	private boolean isBanned;
	private String lastCompanyName;
	private String lastCompanyDesignation;
	private Integer numberOfMonths;

	public Student(Integer id, String rollNo, String email, String password, String name, String phone, float cgpa, String stream, String course, String batch, String placementYear, String address, String pincode, String state, String nationality, String xBoard, float xPercentage, String xYear, String xiiBoard, float xiiPercentage, String xiiYear, String undergraduateUniversity, String undergraduateDegree, String undergraduateYear, float undergraduatePercentage, String gender, Date dob, String image, String cv, boolean isBanned, String lastCompanyName, String lastCompanyDesignation, Integer numberOfMonths) {
		this.id = id;
		this.rollNo = rollNo;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.cgpa = cgpa;
		this.stream = stream;
		this.course = course;
		this.batch = batch;
		this.placementYear = placementYear;
		this.address = address;
		this.pincode = pincode;
		this.state = state;
		this.nationality = nationality;
		this.xBoard = xBoard;
		this.xPercentage = xPercentage;
		this.xYear = xYear;
		this.xiiBoard = xiiBoard;
		this.xiiPercentage = xiiPercentage;
		this.xiiYear = xiiYear;
		this.undergraduateUniversity = undergraduateUniversity;
		this.undergraduateDegree = undergraduateDegree;
		this.undergraduateYear = undergraduateYear;
		this.undergraduatePercentage = undergraduatePercentage;
		this.gender = gender;
		this.dob = dob;
		this.image = image;
		this.cv = cv;
		this.isBanned = isBanned;
		this.lastCompanyName = lastCompanyName;
		this.lastCompanyDesignation = lastCompanyDesignation;
		this.numberOfMonths = numberOfMonths;
	}

	public String getLastCompanyName() {
		return lastCompanyName;
	}

	public void setLastCompanyName(String lastCompanyName) {
		this.lastCompanyName = lastCompanyName;
	}

	public String getLastCompanyDesignation() {
		return lastCompanyDesignation;
	}

	public void setLastCompanyDesignation(String lastCompanyDesignation) {
		this.lastCompanyDesignation = lastCompanyDesignation;
	}

	public Integer getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMonths(Integer numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}

	/*public String getCv() {
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
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public float getCgpa() {
		return cgpa;
	}
	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public Year getPlacementYear() {
		return placementYear;
	}
	public void setPlacementYear(Year placementYear) {
		this.placementYear = placementYear;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getxBoard() {
		return xBoard;
	}
	public void setxBoard(String xBoard) {
		this.xBoard = xBoard;
	}
	public float getxPercentage() {
		return xPercentage;
	}
	public void setxPercentage(float xPercentage) {
		this.xPercentage = xPercentage;
	}
	public Year getxYear() {
		return xYear;
	}
	public void setxYear(Year xYear) {
		this.xYear = xYear;
	}
	public String getXiiBoard() {
		return xiiBoard;
	}
	public void setXiiBoard(String xiiBoard) {
		this.xiiBoard = xiiBoard;
	}
	public float getXiiPercentage() {
		return xiiPercentage;
	}
	public void setXiiPercentage(float xiiPercentage) {
		this.xiiPercentage = xiiPercentage;
	}
	public Year getXiiYear() {
		return xiiYear;
	}
	public void setXiiYear(Year xiiYear) {
		this.xiiYear = xiiYear;
	}
	public String getUndergraduateUniversity() {
		return undergraduateUniversity;
	}
	public void setUndergraduateUniversity(String undergraduateUniversity) {
		this.undergraduateUniversity = undergraduateUniversity;
	}
	public String getUndergraduateDegree() {
		return undergraduateDegree;
	}
	public void setUndergraduateDegree(String undergraduateDegree) {
		this.undergraduateDegree = undergraduateDegree;
	}
	public Year getUndergraduateYear() {
		return undergraduateYear;
	}
	public void setUndergraduateYear(Year undergraduateYear) {
		this.undergraduateYear = undergraduateYear;
	}
	public float getUndergraduatePercentage() {
		return undergraduatePercentage;
	}
	public void setUndergraduatePercentage(float undergraduatePercentage) {
		this.undergraduatePercentage = undergraduatePercentage;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", rollNo=" + rollNo + ", email=" + email + ", password=" + password + ", name="
				+ name + ", phone=" + phone + ", cgpa=" + cgpa + ", stream=" + stream + ", course=" + course
				+ ", batch=" + batch + ", placementYear=" + placementYear + ", address=" + address + ", pincode="
				+ pincode + ", state=" + state + ", nationality=" + nationality + ", xBoard=" + xBoard
				+ ", xPercentage=" + xPercentage + ", xYear=" + xYear + ", xiiBoard=" + xiiBoard + ", xiiPercentage="
				+ xiiPercentage + ", xiiYear=" + xiiYear + ", undergraduateUniversity=" + undergraduateUniversity
				+ ", undergraduateDegree=" + undergraduateDegree + ", undergraduateYear=" + undergraduateYear
				+ ", undergraduatePercentage=" + undergraduatePercentage + ", gender=" + gender + ", dob=" + dob
				+ ", image=" + image + ", cv=" + cv + "]";
	}
	public Student(Integer id, String rollNo, String email, String password, String name, String phone, float cgpa,
			String stream, String course, String batch, Year placementYear, String address, String pincode,
			String state, String nationality, String xBoard, float xPercentage, Year xYear, String xiiBoard,
			float xiiPercentage, Year xiiYear, String undergraduateUniversity, String undergraduateDegree,
			Year undergraduateYear, float undergraduatePercentage, String gender, Date dob, String image, String cv) {
		super();
		this.id = id;
		this.rollNo = rollNo;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.cgpa = cgpa;
		this.stream = stream;
		this.course = course;
		this.batch = batch;
		this.placementYear = placementYear;
		this.address = address;
		this.pincode = pincode;
		this.state = state;
		this.nationality = nationality;
		this.xBoard = xBoard;
		this.xPercentage = xPercentage;
		this.xYear = xYear;
		this.xiiBoard = xiiBoard;
		this.xiiPercentage = xiiPercentage;
		this.xiiYear = xiiYear;
		this.undergraduateUniversity = undergraduateUniversity;
		this.undergraduateDegree = undergraduateDegree;
		this.undergraduateYear = undergraduateYear;
		this.undergraduatePercentage = undergraduatePercentage;
		this.gender = gender;
		this.dob = dob;
		this.image = image;
		this.cv = cv;
	}*/

	public Student(Integer id, String rollNo, String email, String password, String name, String phone, float cgpa, String stream, String course, String batch, String placementYear, String address, String pincode, String state, String nationality, String xBoard, float xPercentage, String xYear, String xiiBoard, float xiiPercentage, String xiiYear, String undergraduateUniversity, String undergraduateDegree, String undergraduateYear, float undergraduatePercentage, String gender, Date dob, String image, String cv, boolean isBanned) {
		this.id = id;
		this.rollNo = rollNo;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.cgpa = cgpa;
		this.stream = stream;
		this.course = course;
		this.batch = batch;
		this.placementYear = placementYear;
		this.address = address;
		this.pincode = pincode;
		this.state = state;
		this.nationality = nationality;
		this.xBoard = xBoard;
		this.xPercentage = xPercentage;
		this.xYear = xYear;
		this.xiiBoard = xiiBoard;
		this.xiiPercentage = xiiPercentage;
		this.xiiYear = xiiYear;
		this.undergraduateUniversity = undergraduateUniversity;
		this.undergraduateDegree = undergraduateDegree;
		this.undergraduateYear = undergraduateYear;
		this.undergraduatePercentage = undergraduatePercentage;
		this.gender = gender;
		this.dob = dob;
		this.image = image;
		this.cv = cv;
		this.isBanned = isBanned;
	}

	public Student() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public float getCgpa() {
		return cgpa;
	}

	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getPlacementYear() {
		return placementYear;
	}

	public void setPlacementYear(String placementYear) {
		this.placementYear = placementYear;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getxBoard() {
		return xBoard;
	}

	public void setxBoard(String xBoard) {
		this.xBoard = xBoard;
	}

	public float getxPercentage() {
		return xPercentage;
	}

	public void setxPercentage(float xPercentage) {
		this.xPercentage = xPercentage;
	}

	public String getxYear() {
		return xYear;
	}

	public void setxYear(String xYear) {
		this.xYear = xYear;
	}

	public String getXiiBoard() {
		return xiiBoard;
	}

	public void setXiiBoard(String xiiBoard) {
		this.xiiBoard = xiiBoard;
	}

	public float getXiiPercentage() {
		return xiiPercentage;
	}

	public void setXiiPercentage(float xiiPercentage) {
		this.xiiPercentage = xiiPercentage;
	}

	public String getXiiYear() {
		return xiiYear;
	}

	public void setXiiYear(String xiiYear) {
		this.xiiYear = xiiYear;
	}

	public String getUndergraduateUniversity() {
		return undergraduateUniversity;
	}

	public void setUndergraduateUniversity(String undergraduateUniversity) {
		this.undergraduateUniversity = undergraduateUniversity;
	}

	public String getUndergraduateDegree() {
		return undergraduateDegree;
	}

	public void setUndergraduateDegree(String undergraduateDegree) {
		this.undergraduateDegree = undergraduateDegree;
	}

	public String getUndergraduateYear() {
		return undergraduateYear;
	}

	public void setUndergraduateYear(String undergraduateYear) {
		this.undergraduateYear = undergraduateYear;
	}

	public float getUndergraduatePercentage() {
		return undergraduatePercentage;
	}

	public void setUndergraduatePercentage(float undergraduatePercentage) {
		this.undergraduatePercentage = undergraduatePercentage;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean banned) {
		isBanned = banned;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", rollNo='" + rollNo + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				", cgpa=" + cgpa +
				", stream='" + stream + '\'' +
				", course='" + course + '\'' +
				", batch='" + batch + '\'' +
				", placementYear='" + placementYear + '\'' +
				", address='" + address + '\'' +
				", pincode='" + pincode + '\'' +
				", state='" + state + '\'' +
				", nationality='" + nationality + '\'' +
				", xBoard='" + xBoard + '\'' +
				", xPercentage=" + xPercentage +
				", xYear='" + xYear + '\'' +
				", xiiBoard='" + xiiBoard + '\'' +
				", xiiPercentage=" + xiiPercentage +
				", xiiYear='" + xiiYear + '\'' +
				", undergraduateUniversity='" + undergraduateUniversity + '\'' +
				", undergraduateDegree='" + undergraduateDegree + '\'' +
				", undergraduateYear='" + undergraduateYear + '\'' +
				", undergraduatePercentage=" + undergraduatePercentage +
				", gender='" + gender + '\'' +
				", dob=" + dob +
				", image='" + image + '\'' +
				", cv='" + cv + '\'' +
				", isBanned=" + isBanned +
				'}';
	}
}

