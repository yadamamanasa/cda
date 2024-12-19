package org.jsp.cda.service;



import org.springframework.http.ResponseEntity;

public interface EnrollmentService {

	ResponseEntity<?> saveEnrollment(int cid, int sid);

	ResponseEntity<?> findEnrollmentByCourseId(int cid);
	
	ResponseEntity<?> findEnrollmentById(int id);

	ResponseEntity<?> findAllEnrollments();

	ResponseEntity<?> findStudentEnrollments(int sid);

	ResponseEntity<?> findAllEnrollmentsByFaculty(int fid);

}
