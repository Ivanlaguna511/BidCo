package com.bidco.api_rest.repository;

import com.bidco.api_rest.dto.subasta.SubastaCreateDTO;
import com.bidco.api_rest.dto.subasta.SubastaResponseDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;
import com.bidco.api_rest.model.Puja;
import com.bidco.api_rest.model.PujaSorteo;
import com.bidco.api_rest.model.Subasta;
import com.bidco.api_rest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface SubastaRepository extends JpaRepository<Subasta, Long> {

    @Query(value = "select P.* from puja P inner join subasta S on P.subasta_id=S.subasta_id where S.subasta_id = ?1 and P.importe >= ALL ( select P1.importe from puja P1 inner join subasta S1 on P1.subasta_id=S1.subasta_id where P.importe and S1.subasta_id = ?1)",nativeQuery = true )
    Optional<Puja> findGanadorSubastaBySubastaId(Long id);

    @Query(value = "select P.importe from puja P inner join subasta S on P.subasta_id=S.subasta_id where S.subasta_id = ?1 and P.importe >= ALL ( select P1.importe from puja P1 inner join subasta S1 on P1.subasta_id=S1.subasta_id where P.importe and S1.subasta_id = ?1)",nativeQuery = true )
    Optional<BigDecimal> findImporteMayorBySubastaId(Long id);
}
