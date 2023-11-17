package com.midterm.springcommerce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midterm.springcommerce.Models.CartProduct;
import com.midterm.springcommerce.Utilities.CartProductKey;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProductKey> {

}
