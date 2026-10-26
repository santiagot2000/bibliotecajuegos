package com.taller.bibliotecajuegos.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taller.bibliotecajuegos.domain.models.Cliente;

/**
 * Repositorio JPA para persistir y consultar clientes.
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
