package iiitb.placement_portal.dto;

import iiitb.placement_portal.entity.Company;

public class UpcomingCompanyDTO {
    private boolean isEligible;
    private String nonEligibiltyReason;
    private Company company;

    public UpcomingCompanyDTO(boolean isEligible, String nonEligibiltyReason, Company company) {
        this.isEligible = isEligible;
        this.nonEligibiltyReason = nonEligibiltyReason;
        this.company = company;
    }

    public UpcomingCompanyDTO() {
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
