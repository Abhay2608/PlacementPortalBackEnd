package iiitb.placement_portal.dto;

import iiitb.placement_portal.entity.Company;

public class CompanyDTO {
    private boolean isEligible;
    private String nonEligibiltyReason;
    private String comingFor;
    private Company company;
    private boolean isApplied;
    private boolean isExpired;

    public boolean isApplied() {
        return isApplied;
    }

    public void setApplied(boolean applied) {
        isApplied = applied;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public CompanyDTO(boolean isEligible, String nonEligibiltyReason, String comingFor, Company company, boolean isApplied, boolean isExpired) {
        this.isEligible = isEligible;
        this.nonEligibiltyReason = nonEligibiltyReason;
        this.comingFor = comingFor;
        this.company = company;
        this.isApplied = isApplied;
        this.isExpired = isExpired;
    }

    public CompanyDTO(boolean isEligible, String nonEligibiltyReason, Company company) {
        this.isEligible = isEligible;
        this.nonEligibiltyReason = nonEligibiltyReason;
        this.company = company;
    }

    public CompanyDTO(boolean isEligible, String nonEligibiltyReason, String comingFor, Company company) {
        this.isEligible = isEligible;
        this.nonEligibiltyReason = nonEligibiltyReason;
        this.comingFor = comingFor;
        this.company = company;
    }

    public CompanyDTO() {
    }

    public String getComingFor() {
        return comingFor;
    }

    public void setComingFor(String comingFor) {
        this.comingFor = comingFor;
    }

    public boolean isEligible() {
        return isEligible;
    }

    public void setEligible(boolean eligible) {
        isEligible = eligible;
    }

    public String getNonEligibiltyReason() {
        return nonEligibiltyReason;
    }

    public void setNonEligibiltyReason(String nonEligibiltyReason) {
        this.nonEligibiltyReason = nonEligibiltyReason;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "UpcomingCompanyDTO{" +
                "isEligible=" + isEligible +
                ", nonEligibiltyReason='" + nonEligibiltyReason + '\'' +
                ", company=" + company +
                '}';
    }
}
