package iiitb.placement_portal.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompanyApplication {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer studentId;
    private Integer companyId;
    private boolean summerIntern;
    private boolean intern;
    private boolean fulltime;
    private boolean internAndFull;

    public CompanyApplication(Integer id, Integer studentId, Integer companyId, boolean summerIntern, boolean intern, boolean fulltime, boolean internAndFull) {
        this.id = id;
        this.studentId = studentId;
        this.companyId = companyId;
        this.summerIntern = summerIntern;
        this.intern = intern;
        this.fulltime = fulltime;
        this.internAndFull = internAndFull;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public boolean isSummerIntern() {
        return summerIntern;
    }

    public void setSummerIntern(boolean summerIntern) {
        this.summerIntern = summerIntern;
    }

    public boolean isIntern() {
        return intern;
    }

    public void setIntern(boolean intern) {
        this.intern = intern;
    }

    public boolean isFulltime() {
        return fulltime;
    }

    public void setFulltime(boolean fulltime) {
        this.fulltime = fulltime;
    }

    public boolean isInternAndFull() {
        return internAndFull;
    }

    public void setInternAndFull(boolean internAndFull) {
        this.internAndFull = internAndFull;
    }

    public CompanyApplication() {
    }
}
