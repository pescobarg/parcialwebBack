package com.web.parcial.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.parcial.modelo.Contrato;


@Repository
public interface ContratoRepositorio extends JpaRepository<Contrato, Long> {
}
