package org.jsp.cda.controller;


import java.time.LocalTime;

import org.jsp.cda.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
	
	@Autowired
	private FacultyService facultyService;
	
	@PostMapping
	public ResponseEntity<?> saveFaculty(@RequestParam int uid,@RequestParam MultipartFile file){
		return facultyService.saveFaculty(uid,file);
	}
	@GetMapping
	public ResponseEntity<?> findAllFaculty(){
		return facultyService.findAllFaculty();
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findFacultyById(@PathVariable int fid){
		return facultyService.findFacultyById(fid);
	}
	@PatchMapping("/update")
	public ResponseEntity<?> updateInfo(@RequestParam int id,@RequestParam String email,@RequestParam String phone,@RequestParam LocalTime officeHours){
		return facultyService.updateInfo(id,email,phone,officeHours);
	}
	 @PatchMapping("/{uid}/{did}")
	 public ResponseEntity<?>  assignDepartmentToFacultyProfile(@PathVariable int uid,@PathVariable int did ){
		 return facultyService.assignDepartmentToFacultyProfile(uid,did);
	 }
	 @PatchMapping
	 public ResponseEntity<?> updatePhoto(@RequestParam int id,@RequestParam MultipartFile file){
		 return facultyService.updatePhoto(id,file);
	 }
	 

}
