package com.banco.blood.repository;

import com.banco.blood.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    List<Candidato> findByEstado(String estado);
}
