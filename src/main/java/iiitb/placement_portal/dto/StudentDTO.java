package iiitb.placement_portal.dto;

public class StudentDTO {
    private String name;
    private String email;
    private String rollNo;
    private String appliedFor;
    private float cgpa;

    public StudentDTO(String name, String email, String rollNo, String appliedFor, float cgpa) {
        this.name = name;
        this.email = email;
        this.rollNo = rollNo;
        this.appliedFor = appliedFor;
        this.cgpa = cgpa;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public StudentDTO(String name, String email, String rollNo, String appliedFor) {
        this.name = name;
        this.email = email;
        this.rollNo = rollNo;
        this.appliedFor = appliedFor;
    }

    public String getAppliedFor() {
        return appliedFor;
    }

    public void setAppliedFor(String appliedFor) {
        this.appliedFor = appliedFor;
    }

    public StudentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
}
