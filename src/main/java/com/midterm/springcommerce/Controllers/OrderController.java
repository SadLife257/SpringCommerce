package com.midterm.springcommerce.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midterm.springcommerce.Models.Order;
import com.midterm.springcommerce.Models.Product;
import com.midterm.springcommerce.Services.OrderService;
import com.midterm.springcommerce.Services.ProductService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	//Create
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order p){
		return new ResponseEntity<>(service.save(p), HttpStatus.OK);
	}
	//Read
	@GetMapping
	public ResponseEntity<Iterable<Order>> readAllOrder() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	@GetMapping("/{id}")
    public ResponseEntity<Order> readOrderById(@PathVariable String id) {
        Optional<Order> optional = service.findById(id);
        return optional.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	//Update
	@PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order p) {
        Optional<Order> optional = service.findById(id);
        return optional.map(e -> {
            e.setId(e.getId());
            return new ResponseEntity<>(service.save(e), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	//Delete
	@DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable String id) {
        Optional<Order> optional = service.findById(id);
        return optional.map(e -> {
            service.remove(id);
            return new ResponseEntity<>(e, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
