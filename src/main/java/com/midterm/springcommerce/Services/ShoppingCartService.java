package com.midterm.springcommerce.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midterm.springcommerce.Models.ShoppingCart;
import com.midterm.springcommerce.Repositories.ShoppingCartRepository;

@Service
public class ShoppingCartService implements GeneralService<ShoppingCart, String> {

	@Autowired
	private ShoppingCartRepository repo;
	
	@Override
	public Iterable<ShoppingCart> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Optional<ShoppingCart> findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public ShoppingCart save(ShoppingCart t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
