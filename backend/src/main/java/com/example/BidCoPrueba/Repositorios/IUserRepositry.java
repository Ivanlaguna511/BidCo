package com.example.BidCoPrueba.Repositorios;

import com.example.BidCoPrueba.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepositry extends JpaRepository<Usuario,Long> {
    //aqui cuando tengamos consultas que no esten en JPARepository, se pondrian aqui
}
