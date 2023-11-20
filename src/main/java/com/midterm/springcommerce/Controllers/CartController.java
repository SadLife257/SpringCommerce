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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.midterm.springcommerce.Models.CartProduct;
import com.midterm.springcommerce.Models.CartProductResponse;
import com.midterm.springcommerce.Models.ShoppingCart;
import com.midterm.springcommerce.Services.CartProductService;
import com.midterm.springcommerce.Services.ProductService;
import com.midterm.springcommerce.Services.ShoppingCartService;
import com.midterm.springcommerce.Utilities.CartProductKey;
import com.midterm.springcommerce.Utilities.Constants;
import com.midterm.springcommerce.Utilities.Payloads.Requests.CartProductRequest;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private ShoppingCartService service;
	
	@Autowired
	private CartProductService cartProductService;
	
	//Create
	@PostMapping
	public ResponseEntity<ShoppingCart> createCart(@RequestBody ShoppingCart p){
		return new ResponseEntity<>(service.save(p), HttpStatus.OK);
	}
	//Read
	@GetMapping("/products")
	public ResponseEntity<CartProductResponse> readAllCartProduct(
			@RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return new ResponseEntity<>(cartProductService.findAll(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
	}
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
	@PutMapping("/products/{id}")
	public ResponseEntity<CartProduct> updateCartProduct(@PathVariable CartProductKey id, @RequestBody CartProductRequest p) {
		Optional<CartProduct> optional = cartProductService.findById(id);
		return optional.map(e -> {
			e.setQuantity(p.getQuantity());
			return new ResponseEntity<>(cartProductService.save(e), HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PutMapping("/{id}")
    public ResponseEntity<ShoppingCart> updateCart(@PathVariable String id, @RequestBody ShoppingCart p) {
        Optional<ShoppingCart> optional = service.findById(id);
        return optional.map(e -> {
            p.setId(e.getId());
            p.setCreatedAt(e.getCreatedAt());
            return new ResponseEntity<>(service.save(p), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	//Delete
	@DeleteMapping("/products/{id}")
	public ResponseEntity<CartProduct> deleteCartProduct(@PathVariable CartProductKey id) {
		Optional<CartProduct> optional = cartProductService.findById(id);
		return optional.map(e -> {
			cartProductService.remove(id);
			return new ResponseEntity<>(e, HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@DeleteMapping("/{id}")
    public ResponseEntity<ShoppingCart> deleteCart(@PathVariable String id) {
        Optional<ShoppingCart> optional = service.findById(id);
        return optional.map(e -> {
            service.remove(id);
            return new ResponseEntity<>(e, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
