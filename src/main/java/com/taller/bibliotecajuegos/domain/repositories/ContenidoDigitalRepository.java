package com.taller.bibliotecajuegos.domain.repositories;

import com.taller.bibliotecajuegos.domain.models.Contenido_Digital;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContenidoDigitalRepository extends JpaRepository<Contenido_Digital, Long> {
    // Spring Boot detecta el nombre del método y crea la consulta SQL automáticamente 
    // para traernos solo los juegos donde "disponible" sea true.
    List<Contenido_Digital> findByDisponibleTrue();
}