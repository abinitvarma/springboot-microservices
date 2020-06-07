package com.microservice.stock.dbservice.repositories;

import com.microservice.stock.dbservice.entities.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quotes, Integer> {
    List<Quotes> findByUserName(String userName);
}
