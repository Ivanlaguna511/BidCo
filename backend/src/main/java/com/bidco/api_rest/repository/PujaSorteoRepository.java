package com.bidco.api_rest.repository;

import com.bidco.api_rest.model.PujaSorteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PujaSorteoRepository extends JpaRepository<PujaSorteo, Long> {
}
