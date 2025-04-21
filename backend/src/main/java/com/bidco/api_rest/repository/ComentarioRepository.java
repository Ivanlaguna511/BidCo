package com.bidco.api_rest.repository;

import com.bidco.api_rest.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    @Query(value = "SELECT * FROM comentario C WHERE C.subasta_id = ?1", nativeQuery = true)
    List<Comentario> findComentariosBySubastaId(Long id);
}