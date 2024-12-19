package org.jsp.cda.serviceimpl;

import java.util.Optional;

import org.jsp.cda.dao.AdministratorDao;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.Administrator;
import org.jsp.cda.entity.User;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.jsp.cda.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private AdministratorDao administratorDao;

	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<?> saveAdministrator(Administrator administrator) {
		return null;

//		administrator = dao.saveAdministrator(administrator);
//		return   ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Administrator saved successfully").body(administrator).build());
	}

	@Override
	public ResponseEntity<?> findAdministratorById(int id) {
		Optional<Administrator> optinal = administratorDao.findAdministratorById(id);
//		if(optinal.isEmpty())
//			throw Exception();
		Administrator administrator = optinal.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Administrator Found Successfully...").body(administrator).build());
	}

	@Override
	public ResponseEntity<?> saveAdministrator(int id) {

		Optional<User> optional = userDao.findUserById(id);

//		if(optional.isEmpty())
//			throw 

		User user = optional.get();

		Administrator a = new Administrator(id, user, null, null);

		a = administratorDao.saveAdministrator(a);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Administartor Created Successfully").body(a).build());

	}

//	@Override
//	public ResponseEntity<?> findAllAdministrator() {
//		List<Administrator> administrator = dao.findAllAdministrator();
//		if(administrator.isEmpty())
//			throw NoAdministratorFoundException.builder().message("No Administrator found").build();
//		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Course Found Successfully").body(administrator).build());
//		
//	}

}
