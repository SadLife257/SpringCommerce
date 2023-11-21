package com.midterm.springcommerce.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.midterm.springcommerce.Models.CartProduct;
import com.midterm.springcommerce.Models.CartProductResponse;
import com.midterm.springcommerce.Models.Product;
import com.midterm.springcommerce.Models.ProductResponse;
import com.midterm.springcommerce.Models.ShoppingCart;
import com.midterm.springcommerce.Repositories.CartProductRepository;
import com.midterm.springcommerce.Utilities.CartProductKey;

@Service
public class CartProductService implements GeneralService<CartProduct, CartProductKey> {

	@Autowired
	private CartProductRepository repo;
	
	public CartProductResponse findAll(ShoppingCart cart, int pageNo, int pageSize, String sortBy, String sortDirect) {
		Sort sort = sortDirect.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<CartProduct> cartProducts = repo.findByIdCart(cart, pageable);
        
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
	
	public Optional<CartProduct> findById(Product product) {
		// TODO Auto-generated method stub
		return repo.findByIdProduct(product);
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
	
	public CartProduct save(CartProductKey id, int quantity) {
		// TODO Auto-generated method stub
		if(repo.existsById(id))
		{
			Optional<CartProduct> t = repo.findById(id);
			return t.map(cp -> {
				cp.setQuantity(cp.getQuantity() + quantity);
				return repo.save(cp);
			}).orElseGet(() -> repo.save(new CartProduct(id, quantity)));
		}
		return repo.save(new CartProduct(id, quantity));
	}

	public void remove(CartProduct t)
	{
		repo.delete(t);
	}
	
	@Override
	public void remove(CartProductKey id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
