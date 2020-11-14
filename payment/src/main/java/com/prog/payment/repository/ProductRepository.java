package com.prog.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prog.payment.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
