package com.prog.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prog.payment.entity.Sell;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long>{

}
