package iiitb.placement_portal.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlacementData {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer studentId;
    private Integer summerCompanyId;
    private Integer internCompanyId;
    private Integer fulltimeCompanyId;
    private Integer internAndFulltimeCompanyId;

    public PlacementData(Integer id, Integer studentId, Integer summerCompanyId, Integer internCompanyId, Integer fulltimeCompanyId, Integer internAndFulltimeCompanyId) {
        this.id = id;
        this.studentId = studentId;
        this.summerCompanyId = summerCompanyId;
        this.internCompanyId = internCompanyId;
        this.fulltimeCompanyId = fulltimeCompanyId;
        this.internAndFulltimeCompanyId = internAndFulltimeCompanyId;
    }

    public PlacementData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSummerCompanyId() {
        return summerCompanyId;
    }

    public void setSummerCompanyId(Integer summerCompanyId) {
        this.summerCompanyId = summerCompanyId;
    }

    public Integer getInternCompanyId() {
        return internCompanyId;
    }

    public void setInternCompanyId(Integer internCompanyId) {
        this.internCompanyId = internCompanyId;
    }

    public Integer getFulltimeCompanyId() {
        return fulltimeCompanyId;
    }

    public void setFulltimeCompanyId(Integer fulltimeCompanyId) {
        this.fulltimeCompanyId = fulltimeCompanyId;
    }

    public Integer getInternAndFulltimeCompanyId() {
        return internAndFulltimeCompanyId;
    }

    public void setInternAndFulltimeCompanyId(Integer internAndFulltimeCompanyId) {
        this.internAndFulltimeCompanyId = internAndFulltimeCompanyId;
    }
}
