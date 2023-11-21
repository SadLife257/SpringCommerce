package com.midterm.springcommerce.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midterm.springcommerce.Models.CartProduct;
import com.midterm.springcommerce.Models.Product;
import com.midterm.springcommerce.Models.ShoppingCart;
import com.midterm.springcommerce.Utilities.CartProductKey;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductKey> {
	Page<CartProduct> findByIdCart(ShoppingCart cart, Pageable pageable);
	Optional<CartProduct> findByIdProduct(Product product);
}
