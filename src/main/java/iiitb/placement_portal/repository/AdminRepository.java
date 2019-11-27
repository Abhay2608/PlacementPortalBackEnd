package iiitb.placement_portal.repository;


import iiitb.placement_portal.entity.Admin;
import iiitb.placement_portal.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
}
