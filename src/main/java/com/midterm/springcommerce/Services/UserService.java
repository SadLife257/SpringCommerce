package com.midterm.springcommerce.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midterm.springcommerce.Models.User;
import com.midterm.springcommerce.Repositories.UserRepository;

@Service
public class UserService implements GeneralService<User, String> {

	@Autowired
	private UserRepository repo;
	
	@Override
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Optional<User> findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public User save(User t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
