package iiitb.placement_portal.repository;

import org.springframework.data.repository.CrudRepository;

import iiitb.placement_portal.entity.CompanyParticipation;

import java.util.ArrayList;

public interface CompanyParticipationRepository extends CrudRepository<CompanyParticipation, Integer> {
    public ArrayList<CompanyParticipation> findAllByStudentId(Integer studentId);
    public ArrayList<CompanyParticipation> findAllByCompanyId(Integer companyId);
    public CompanyParticipation findByStudentIdAndCompanyId(Integer studentId,Integer companyId);
    public CompanyParticipationRepository deleteByStudentIdAndCompanyId(Integer studentId,Integer companyId);
}
