package com.taller.bibliotecajuegos.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.bibliotecajuegos.domain.models.ContenidoDigital;
import com.taller.bibliotecajuegos.domain.models.JuegoDigital;
import com.taller.bibliotecajuegos.domain.models.JuegoFisico;
import com.taller.bibliotecajuegos.services.JuegoService;

/**
 * Controlador REST del catálogo de videojuegos.
 * Expone los endpoints para registrar juegos físicos y digitales
 * y para listar los que están disponibles para alquiler.
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
@RestController
@RequestMapping("/api/juegos")
public class JuegoController {

    /** Servicio con la lógica de registro y consulta de juegos. */
    private final JuegoService juegoService;

    /**
     * Inyección de dependencias a través del constructor.
     *
     * @param juegoService servicio de juegos
     */
    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    /**
     * POST /api/juegos/fisico
     * Registra un juego físico en el sistema.
     *
     * @param juegoFisico datos del juego físico en formato JSON
     * @return el juego físico guardado con su ID generado
     */
    @PostMapping("/fisico")
    public JuegoFisico registrarJuegoFisico(@RequestBody JuegoFisico juegoFisico) {
        return juegoService.registrarJuegoFisico(juegoFisico);
    }

    /**
     * POST /api/juegos/digital
     * Registra un juego digital en el sistema.
     *
     * @param juegoDigital datos del juego digital en formato JSON
     * @return el juego digital guardado con su ID generado
     */
    @PostMapping("/digital")
    public JuegoDigital registrarJuegoDigital(@RequestBody JuegoDigital juegoDigital) {
        return juegoService.registrarJuegoDigital(juegoDigital);
    }

    /**
     * GET /api/juegos
     * Lista todos los juegos registrados que están disponibles para alquiler.
     *
     * @return lista de contenidos digitales disponibles
     */
    @GetMapping
    public List<ContenidoDigital> listarJuegosDisponibles() {
        return juegoService.listarDisponibles();
    }
}
