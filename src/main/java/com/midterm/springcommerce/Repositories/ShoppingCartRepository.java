package com.midterm.springcommerce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midterm.springcommerce.Models.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {

}
