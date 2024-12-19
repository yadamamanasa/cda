package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Enrollment;




public interface EnrollmentDao {

	Enrollment saveEnrollment(Enrollment enrollment);

	List<Enrollment> findAllEnrollment();

	List<Enrollment> findEnrollmentByCourseId(int cid);

	Optional<Enrollment> findEnrollmentById(int id);

	List<Enrollment> findStudentEnrollments(int sid);

	List<Enrollment> findAllEnrollmentsByFaculty(int fid);

}
