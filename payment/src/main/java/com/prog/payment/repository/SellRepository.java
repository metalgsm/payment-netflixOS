package com.prog.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog.payment.entity.Sell;

public interface SellRepository extends JpaRepository<Sell, Long>{

}
