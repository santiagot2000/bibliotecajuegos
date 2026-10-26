package com.taller.bibliotecajuegos.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taller.bibliotecajuegos.domain.models.ContenidoDigital;

/**
 * Repositorio JPA para persistir y consultar el catálogo de juegos
 * (físicos y digitales) mediante la jerarquía {@link ContenidoDigital}.
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
public interface ContenidoDigitalRepository extends JpaRepository<ContenidoDigital, Long> {

    /**
     * Obtiene los juegos que están disponibles para alquiler.
     * Spring Data deriva la consulta a partir del nombre del método.
     *
     * @return lista de contenidos con {@code disponible = true}
     */
    List<ContenidoDigital> findByDisponibleTrue();
}
