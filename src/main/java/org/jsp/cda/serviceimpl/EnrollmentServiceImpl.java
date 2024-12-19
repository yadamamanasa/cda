package org.jsp.cda.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.CourseDao;
import org.jsp.cda.dao.EnrollmentDao;
import org.jsp.cda.dao.StudentDao;
import org.jsp.cda.entity.Course;
import org.jsp.cda.entity.Enrollment;
import org.jsp.cda.entity.Student;
import org.jsp.cda.exceptionclasses.InvalidEnrollmentIdException;
import org.jsp.cda.exceptionclasses.NoCourseFoundException;
import org.jsp.cda.exceptionclasses.NoEnrollmentFoundException;
import org.jsp.cda.exceptionclasses.NoFacultyFoundException;
import org.jsp.cda.exceptionclasses.NoStudentFoundException;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.jsp.cda.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	@Autowired
	private EnrollmentDao enrollmentDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private CourseDao courseDao;

	@Override
	public ResponseEntity<?> saveEnrollment(int cid, int sid) {
		Optional<Course> optional1 = courseDao.findCourseById(cid);
		Optional<Student> optional2 = studentDao.findStudentById(sid);
		if(optional1.isEmpty()) 
			throw NoCourseFoundException.builder().message("Invalid Course Id: " + cid).build();
		if(optional2.isEmpty())
			throw NoStudentFoundException.builder().message("Invalid Student id" + sid).build();
		Course course = optional1.get();
		Student student = optional2.get();
		Enrollment enrollment = Enrollment.builder().course(course).student(student).build();
		enrollment = enrollmentDao.saveEnrollment(enrollment);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Enrollement Saved Successfully").body(enrollment).build());		
	}

	@Override
	public ResponseEntity<?> findEnrollmentByCourseId(int cid) {
		List<Enrollment> enrollments = enrollmentDao.findEnrollmentByCourseId(cid);
		if(enrollments.isEmpty())
			throw new RuntimeException("No Enrollment Found");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()));
	}

	@Override
	public ResponseEntity<?> findEnrollmentById(int id) {
		Optional<Enrollment> optional = enrollmentDao.findEnrollmentById(id);
		if(optional.isEmpty())
			throw InvalidEnrollmentIdException.builder().message("No enrollment found by this id").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()));
	}

	@Override
	public ResponseEntity<?> findAllEnrollments() {
		List<Enrollment> enrollment = enrollmentDao.findAllEnrollment();
		if(enrollment.isEmpty())
			throw NoEnrollmentFoundException.builder().message("No any enrollement found in database").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()));
	}

	@Override
	public ResponseEntity<?> findStudentEnrollments(int sid) {
		List<Enrollment> enrollmentList = enrollmentDao.findStudentEnrollments(sid);
		if(enrollmentList.isEmpty()) 
			throw NoEnrollmentFoundException.builder().message("No student enrollements found ").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Student enrollment found Successfully").body(enrollmentList).build());
	
	}

	@Override
	public ResponseEntity<?> findAllEnrollmentsByFaculty(int fid) {
		List<Enrollment> enrollments = enrollmentDao.findAllEnrollmentsByFaculty(fid);
		if(enrollments.isEmpty())
			throw NoFacultyFoundException.builder().message("No Faculty Enrollments found").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Find All Enrollments of faculty...").body(enrollments).build());
	}

	

	
	
	
		
	}
	

	


