package org.jsp.cda.controller;

import org.jsp.cda.entity.Course;
import org.jsp.cda.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService service;
	
	@PostMapping
	public ResponseEntity<?> saveCourse(@RequestBody Course course){
		return service.saveCourse(course);
	}
	@GetMapping
	public ResponseEntity<?> findAllCourse(){
		return service.findAllCourse();
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findCourseById(@PathVariable int id){
		return service. findCourseById(id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourseById(@PathVariable int id){
		return service.deleteCourseById(id);
	}
	@PatchMapping("/{cid}/{fid}")
	public ResponseEntity<?> assignFacultyToCourse(@PathVariable int cid, @PathVariable int fid){
		return service.assignFacultyToCourse(cid, fid);
	}
	

}
