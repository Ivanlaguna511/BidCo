package com.bidco.api_rest.repository;

import com.bidco.api_rest.model.PujaSorteo;
import com.bidco.api_rest.model.Sorteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SorteoRepository extends JpaRepository<Sorteo, Long> {
    @Query(value = "select P.* from puja_sorteo P inner join sorteo S on P.sorteo_id=S.sorteo_id where S.sorteo_id = ?1 and P.puntos >= ALL ( select P1.puntos from puja_sorteo P1 inner join sorteo S1 on P1.sorteo_id=S1.sorteo_id where P.puntos and S1.sorteo_id = ?1)",nativeQuery = true )
    Optional<PujaSorteo> findGanadorSorteoBySorteoId(Long id);

    @Query(value = "select P.puntos from puja_sorteo P inner join sorteo S on P.sorteo_id=S.sorteo_id where S.sorteo_id = ?1 and P.puntos >= ALL ( select P1.puntos from puja_sorteo P1 inner join sorteo S1 on P1.sorteo_id=S1.sorteo_id where P.puntos and S1.sorteo_id = ?1)",nativeQuery = true )
    Optional<Integer> findImporteMayorBySorteoId(Long id);
    
    @SuppressWarnings("null")
    List<Sorteo> findAll();
}
