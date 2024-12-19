package org.jsp.cda.controller;

import org.jsp.cda.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
	
	@Autowired
	private EnrollmentService service;
	
	@PostMapping("/{cid}/{sid}")
	public ResponseEntity<?> saveEnrollment(@PathVariable int cid , @PathVariable int sid){
		return service.saveEnrollment(cid,sid);
	}
	@GetMapping("/student/course/{cid}")
	public ResponseEntity<?> findEnrollmentByCourseId(int cid){
		return service.findEnrollmentByCourseId(cid);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findEnrollmentById(int id){
		return service. findEnrollmentById(id);
	}
	@GetMapping
	public ResponseEntity<?> findAllEnrollments(){
		return service.findAllEnrollments();
	}
	@GetMapping("/student/{sid}")
	public ResponseEntity<?> findStudentEnrollments(int sid){
		return service.findStudentEnrollments(sid);
	}
	@GetMapping("/faculty/{fid}")
	public ResponseEntity<?> findAllEnrollmentsByFaculty(int fid){
		return service.findAllEnrollmentsByFaculty(fid);
	}
	

}
