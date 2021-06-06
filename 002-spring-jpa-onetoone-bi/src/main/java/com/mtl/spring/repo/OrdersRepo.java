package com.mtl.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtl.spring.entity.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer>{

}
