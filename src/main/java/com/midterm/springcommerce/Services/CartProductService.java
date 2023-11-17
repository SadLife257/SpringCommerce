package com.midterm.springcommerce.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.midterm.springcommerce.Models.CartProduct;
import com.midterm.springcommerce.Models.CartProductResponse;
import com.midterm.springcommerce.Models.Product;
import com.midterm.springcommerce.Models.ProductResponse;
import com.midterm.springcommerce.Repositories.CartProductRepository;
import com.midterm.springcommerce.Utilities.CartProductKey;

public class CartProductService implements GeneralService<CartProduct, CartProductKey> {

	@Autowired
	private CartProductRepository repo;
	
	public CartProductResponse findAll(int pageNo, int pageSize, String sortBy, String sortDirect) {
		Sort sort = sortDirect.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<CartProduct> cartProducts = repo.findAll(pageable);
        
        List<CartProduct> CartProductList = cartProducts.getContent();
        
        CartProductResponse cartProductResponse = new CartProductResponse();
        cartProductResponse.setContent(CartProductList);
        cartProductResponse.setPageNo(cartProducts.getNumber());
        cartProductResponse.setPageSize(cartProducts.getSize());
        cartProductResponse.setTotalElements(cartProducts.getTotalElements());
        cartProductResponse.setTotalPages(cartProducts.getTotalPages());
        cartProductResponse.setLast(cartProducts.isLast());

        return cartProductResponse;
	}
	
	@Override
	public Iterable<CartProduct> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Optional<CartProduct> findById(CartProductKey id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public CartProduct save(CartProduct t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}

	@Override
	public void remove(CartProductKey id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
