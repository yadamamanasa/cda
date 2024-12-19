package org.jsp.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.entity.Department;
import org.jsp.cda.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DepartmentDaoImpl implements DepartmentDao{
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department department) {

    return departmentRepository.save(department);
		
	}

	@Override
	public List<Department> findAllDepartment() {
		return departmentRepository.findAll();
	}

	@Override
	public Optional<Department> findDepartmentById(int id) {
		return departmentRepository.findById(id);
	}
	
	@Override
	public void deleteDepartmentById(int id) {
		 departmentRepository.deleteById(id);
		
	}

	

}
