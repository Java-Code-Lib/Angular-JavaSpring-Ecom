package com.stradtkt.ecommercejavaangular.dao;

import com.stradtkt.ecommercejavaangular.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
