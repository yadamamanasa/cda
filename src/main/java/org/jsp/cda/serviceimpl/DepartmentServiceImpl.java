package org.jsp.cda.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.entity.Department;
import org.jsp.cda.exceptionclasses.InvalidIdException;
import org.jsp.cda.exceptionclasses.NoDepartmentFoundException;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.jsp.cda.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao dao;

	@Override
	public ResponseEntity<?> saveDepartment(Department department) {
		
		department = dao.saveDepartment(department);
		return   ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Department saved successfully").body(department).build());
	}

	@Override
	public ResponseEntity<?> findAllDepartment() {
		List<Department> department = dao.findAllDepartment();
		if(department.isEmpty())
			throw NoDepartmentFoundException.builder().message("NO Departments Found in database Table...").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("All Departments Fetched Successfully").body(department).build());
		
	}

	@Override
	public ResponseEntity<?> findDepartmentById(int id) {
		Optional<Department> optional = dao.findDepartmentById(id);
		if(optional.isEmpty()) 
			throw InvalidIdException.builder().message("Invalid Department Id.." + id).build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Department  Found Successfull").body(optional.get()).build());	
		
	}

	@Override
	public ResponseEntity<?> deleteDepartmentById(int id) {
		 Optional<Department> optional = dao.findDepartmentById(id);
		 if(optional.isEmpty()) 
			 throw InvalidIdException.builder().message("Invalid Department Id.." + id).build();
		 return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Department Deleted Successfully").body("Deleted Successfully").build());
	}

}
