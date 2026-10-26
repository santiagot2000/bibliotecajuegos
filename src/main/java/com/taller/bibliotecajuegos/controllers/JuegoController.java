package com.taller.bibliotecajuegos.controllers;

import com.taller.bibliotecajuegos.domain.models.Contenido_Digital;
import com.taller.bibliotecajuegos.domain.models.Juego_Digital;
import com.taller.bibliotecajuegos.domain.models.Juego_Fisico;
import com.taller.bibliotecajuegos.domain.repositories.ContenidoDigitalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase expone endpoints REST
@RequestMapping("/api/juegos") // Ruta base para todos los métodos de este controlador
public class JuegoController {

    private final ContenidoDigitalRepository juegoRepository;

    // Inyección de dependencias a través del constructor
    public JuegoController(ContenidoDigitalRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    /**
     * POST /api/juegos/fisico
     * Registra un juego físico en el sistema.
     */
    @PostMapping("/fisico")
    public Juego_Fisico registrarJuegoFisico(@RequestBody Juego_Fisico juegoFisico) {
        return juegoRepository.save(juegoFisico);
    }

    /**
     * POST /api/juegos/digital
     * Registra un juego digital en el sistema.
     */
    @PostMapping("/digital")
    public Juego_Digital registrarJuegoDigital(@RequestBody Juego_Digital juegoDigital) {
        return juegoRepository.save(juegoDigital);
    }

    /**
     * GET /api/juegos
     * Lista todos los juegos registrados que están disponibles para alquiler.
     */
    @GetMapping
    public List<Contenido_Digital> listarJuegosDisponibles() {
        return juegoRepository.findByDisponibleTrue();
    }
}