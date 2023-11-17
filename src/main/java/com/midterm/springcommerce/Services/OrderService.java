package com.midterm.springcommerce.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midterm.springcommerce.Models.Order;
import com.midterm.springcommerce.Repositories.OrderRepository;

@Service
public class OrderService implements GeneralService<Order, String> {

	@Autowired
	private OrderRepository repo;
	
	@Override
	public Iterable<Order> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Optional<Order> findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Order save(Order t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
