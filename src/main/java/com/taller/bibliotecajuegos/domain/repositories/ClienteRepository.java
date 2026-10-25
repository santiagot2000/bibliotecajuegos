package com.taller.bibliotecajuegos.domain.repositories;

import com.taller.bibliotecajuegos.domain.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}