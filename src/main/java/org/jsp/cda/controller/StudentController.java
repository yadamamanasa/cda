package org.jsp.cda.controller;

import org.jsp.cda.entity.Student;
import org.jsp.cda.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
//	@PostMapping("/{uid}")
//	public ResponseEntity<?> saveStudent(@PathVariable int uid,@RequestParam MultipartFile file){
//		return studentService.saveStudent(uid,file);
//	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> saveStudent(@PathVariable int id, @RequestBody Student student){
		return studentService.saveStudent(id,student);
	}
	
	
	
	
	
	
	@GetMapping
	public ResponseEntity<?> findAllStudent(){
		return studentService.findAllStudent();
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findStudentById(@PathVariable int id){
		return studentService.findStudentById(id);
	}
	@PatchMapping("/student/{sid}/{year}")
	public ResponseEntity<?> setYearToStudent(@PathVariable int sid, @PathVariable String year){
		return studentService.setYearToStudent(sid,year);
	}
	@PatchMapping("/{sid}/{did}")
	public ResponseEntity<?> assignDepartmentToStudent(@PathVariable int sid,@PathVariable int did){
		return studentService.assignDepartmentToStudent(sid,did);
	}

}
  