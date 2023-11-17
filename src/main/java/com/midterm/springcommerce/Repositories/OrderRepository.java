package com.midterm.springcommerce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midterm.springcommerce.Models.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}
