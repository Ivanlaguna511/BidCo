package com.bidco.api_rest.repository;

import com.bidco.api_rest.model.Puja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PujaRepository extends JpaRepository<Puja, Long> {
}
