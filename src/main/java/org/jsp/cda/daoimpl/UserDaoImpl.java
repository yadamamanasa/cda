package org.jsp.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.User;
import org.jsp.cda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private UserRepository repository;

	@Override
	public User saveUser(User user) {

    return repository.save(user);
		
	}

	@Override
	public List<User> findAllUser() {
		return repository.findAll();
	}

	@Override
	public Optional<User> findByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username , password);
	}

	@Override
	public Optional<User> findUserById(int id) {
		return repository.findById(id);
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		return repository.findUserByEmail(email);
	}

}
