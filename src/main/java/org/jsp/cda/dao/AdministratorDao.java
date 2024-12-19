package org.jsp.cda.dao;



import java.util.Optional;

import org.jsp.cda.entity.Administrator;



public interface AdministratorDao {

	Administrator saveAdministrator(Administrator administrator);
    
	
	Optional<Administrator> findAdministratorById(int id);
//	List<Administrator> findAllAdministrator();

//	List<Administrator> findAllAdministrator();

}
