package org.jsp.cda.controller;

import org.jsp.cda.entity.Department;
import org.jsp.cda.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@PostMapping
	public ResponseEntity<?> saveDepartment(@RequestBody Department department){
		return service.saveDepartment(department);
	}
	@GetMapping
	public ResponseEntity<?> findAllDepartment(){
		return service.findAllDepartment();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findDepartmntById(@PathVariable int id){
		return service.findDepartmentById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDepartmentById(@PathVariable int id){
		return service.deleteDepartmentById(id);
	}

}
