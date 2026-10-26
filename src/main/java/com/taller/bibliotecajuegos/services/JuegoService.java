package com.taller.bibliotecajuegos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taller.bibliotecajuegos.domain.models.ContenidoDigital;
import com.taller.bibliotecajuegos.domain.models.JuegoDigital;
import com.taller.bibliotecajuegos.domain.models.JuegoFisico;
import com.taller.bibliotecajuegos.domain.repositories.ContenidoDigitalRepository;

/**
 * Servicio de negocio para registrar juegos y listar los que están disponibles para alquiler.
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
@Service
public class JuegoService {

    /** Repositorio del catálogo de contenidos digitales. */
    private final ContenidoDigitalRepository juegoRepository;

    /**
     * Inyecta el repositorio de juegos.
     *
     * @param juegoRepository repositorio JPA del catálogo
     */
    public JuegoService(ContenidoDigitalRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    /**
     * Registra un juego físico y lo deja disponible para alquiler.
     *
     * @param juegoFisico datos del juego físico recibidos en la petición
     * @return el juego físico persistido con su identificador asignado
     */
    public JuegoFisico registrarJuegoFisico(JuegoFisico juegoFisico) {
        juegoFisico.setDisponible(true);
        return juegoRepository.save(juegoFisico);
    }

    /**
     * Registra un juego digital y lo deja disponible para alquiler.
     *
     * @param juegoDigital datos del juego digital recibidos en la petición
     * @return el juego digital persistido con su identificador asignado
     */
    public JuegoDigital registrarJuegoDigital(JuegoDigital juegoDigital) {
        juegoDigital.setDisponible(true);
        return juegoRepository.save(juegoDigital);
    }

    /**
     * Lista todos los juegos registrados que están disponibles para alquiler.
     *
     * @return lista de contenidos con disponibilidad activa
     */
    public List<ContenidoDigital> listarDisponibles() {
        return juegoRepository.findByDisponibleTrue();
    }
}
