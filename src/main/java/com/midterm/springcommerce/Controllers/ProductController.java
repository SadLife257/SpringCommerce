package com.midterm.springcommerce.Controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.midterm.springcommerce.Models.Product;
import com.midterm.springcommerce.Models.ProductResponse;
import com.midterm.springcommerce.Models.ShoppingCart;
import com.midterm.springcommerce.Models.User;
import com.midterm.springcommerce.Repositories.UserRepository;
import com.midterm.springcommerce.Securities.JwtUtils;
import com.midterm.springcommerce.Services.CartProductService;
import com.midterm.springcommerce.Services.ProductService;
import com.midterm.springcommerce.Services.ShoppingCartService;
import com.midterm.springcommerce.Services.UserDetailsImpl;
import com.midterm.springcommerce.Services.UserService;
import com.midterm.springcommerce.Utilities.CartProductKey;
import com.midterm.springcommerce.Utilities.Constants;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private CartProductService cartProductService;

	@Autowired
	private ShoppingCartService cartService;

	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	// Create
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Product> createProduct(@RequestBody Product p) {
		return new ResponseEntity<>(service.save(p), HttpStatus.OK);
	}

	// Read
	@GetMapping
	public ResponseEntity<ProductResponse> readAllProduct(
			@RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return new ResponseEntity<>(service.findAll(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> readProductById(@PathVariable String id) {
		Optional<Product> optional = service.findById(id);
		return optional.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Update
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product p) {
		Optional<Product> optional = service.findById(id);
		return optional.map(e -> {
			p.setId(e.getId());
			p.setCreatedAt(e.getCreatedAt());
			return new ResponseEntity<>(service.save(p), HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Delete
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
		Optional<Product> optional = service.findById(id);
		return optional.map(e -> {
			service.remove(id);
			return new ResponseEntity<>(e, HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Add product to ShoppingCart
	@PostMapping("/{id}/cart")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<CartProduct> addProductToShoppingCart(@PathVariable(value = "id") String id,
			@RequestParam(value = "quantity", defaultValue = Constants.DEFAULT_CART_PRODUCT_QUANTITY, required = false) int quantity) {
		UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Optional<User> user = userService.findById(userDetail.getId());
		return user.map(e -> {
			Optional<Product> optional = service.findById(id);
			return optional.map(p -> {
				e.getCart().setTotal(e.getCart().getTotal() + quantity * p.getPrice());
				cartService.save(e.getCart());
				CartProductKey key = new CartProductKey(p, e.getCart());
				CartProduct cartProduct = cartProductService.save(key, quantity);
				return new ResponseEntity<>(cartProduct, HttpStatus.OK);
			}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
