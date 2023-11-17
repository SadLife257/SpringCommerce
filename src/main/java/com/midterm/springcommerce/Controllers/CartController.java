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

import com.midterm.springcommerce.Models.ShoppingCart;
import com.midterm.springcommerce.Services.ProductService;
import com.midterm.springcommerce.Services.ShoppingCartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private ShoppingCartService service;
	
	//Create
	@PostMapping
	public ResponseEntity<ShoppingCart> createCart(@RequestBody ShoppingCart p){
		return new ResponseEntity<>(service.save(p), HttpStatus.OK);
	}
	//Read
	@GetMapping
	public ResponseEntity<Iterable<ShoppingCart>> readAllCart() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	@GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> readCartById(@PathVariable String id) {
        Optional<ShoppingCart> optional = service.findById(id);
        return optional.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	//Update
	@PutMapping("/{id}")
    public ResponseEntity<ShoppingCart> updateCart(@PathVariable String id, @RequestBody ShoppingCart p) {
        Optional<ShoppingCart> optional = service.findById(id);
        return optional.map(e -> {
            e.setId(e.getId());
            return new ResponseEntity<>(service.save(e), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	//Delete
	@DeleteMapping("/{id}")
    public ResponseEntity<ShoppingCart> deleteCart(@PathVariable String id) {
        Optional<ShoppingCart> optional = service.findById(id);
        return optional.map(e -> {
            service.remove(id);
            return new ResponseEntity<>(e, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
