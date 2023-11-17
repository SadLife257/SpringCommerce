package com.midterm.springcommerce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midterm.springcommerce.Models.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
