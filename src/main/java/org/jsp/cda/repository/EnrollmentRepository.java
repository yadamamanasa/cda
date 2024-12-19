package org.jsp.cda.repository;

import java.util.List;

import org.jsp.cda.entity.Enrollment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnrollmentRepository  extends JpaRepository<Enrollment, Integer>{
	
	
	@Query("select e from Enrollment e where e.course.id=:cid ")
	List<Enrollment> findEnrollmentByCourseId(int cid);
	
	@Query("select e from Enrollment e where e.student.id = :sid")
	List<Enrollment> findStudentEnrollments(int sid);
	
	@Query("select e from Enrollment e where e.course.faculty.id = :fid")
	List<Enrollment> findAllEnrollmentsByFaculty(int fid);

}
