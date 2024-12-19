package org.jsp.cda.daoimpl;


import java.util.Optional;

import org.jsp.cda.dao.AdministratorDao;
import org.jsp.cda.entity.Administrator;
import org.jsp.cda.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class AdministratorDaoImpl implements AdministratorDao{
	
	@Autowired
	private AdministratorRepository administratorRepository;

	@Override
	public Administrator saveAdministrator(Administrator administrator) {

    return administratorRepository.save(administrator);
		
	}
	@Override
	public Optional<Administrator> findAdministratorById(int id) {
		return administratorRepository.findById(id);
	}
	

//	@Override
//	public List<Administrator> findAllAdministrator() {
//		return administratorRepository.findAll();
//	}

}
