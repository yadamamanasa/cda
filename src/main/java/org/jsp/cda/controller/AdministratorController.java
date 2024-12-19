package org.jsp.cda.controller;

import org.jsp.cda.entity.Administrator;
import org.jsp.cda.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

	@Autowired
	private AdministratorService admistratorService;
	
	
	@PostMapping("/{id}")
	public ResponseEntity<?>  saveAdministrator( @PathVariable int id){
		return admistratorService.saveAdministrator(id);
	}
	
	
	
//	@PostMapping
//	public ResponseEntity<?>  saveAdministrator(@RequestBody Administrator administrator){
//		return admistratorService.saveAdministrator(administrator);
//	}
	
	
//	@GetMapping
//	public ResponseEntity<?> findAlAdministrator(){
//		return service.findAllAdministrator();
//	}

}
