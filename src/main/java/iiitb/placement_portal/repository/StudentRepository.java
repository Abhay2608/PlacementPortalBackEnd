package iiitb.placement_portal.repository;


import iiitb.placement_portal.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	public Student findByRollNo(String rollNo);
}
