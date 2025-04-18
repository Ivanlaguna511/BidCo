package com.bidco.api_rest.repository;

import com.bidco.api_rest.model.Puja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PujaRepository extends JpaRepository<Puja, Long> {
    @Query("SELECT COUNT(p) FROM Puja p WHERE p.pujador.usuarioID = :usuarioID")
    int countParticipatedBidsByUsuarioId(Long usuarioID);
    
    @Query("SELECT COUNT(p) FROM Puja p WHERE p.pujador.usuarioID = :usuarioID AND p.ganadora = true")
    int countWonBidsByUsuarioId(Long usuarioID);
}