package com.taller.bibliotecajuegos.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.bibliotecajuegos.records.PrestamoRequest;
import com.taller.bibliotecajuegos.services.PrestamoService;

/**
 * Controlador REST encargado de gestionar las operaciones de alquiler de videojuegos.
 * Expone los endpoints para procesar los préstamos aplicando las reglas de negocio.
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    /** Servicio con la lógica de validación y cálculo del préstamo. */
    private final PrestamoService prestamoService;

    /**
     * Constructor para la inyección de dependencias del servicio de préstamos.
     *
     * @param prestamoService servicio de préstamos
     */
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    /**
     * POST /api/prestamos/alquilar
     * Procesa el alquiler de un juego para un cliente específico.
     *
     * @param request objeto tipo record que contiene clienteId, juegoId y la cantidad de días
     * @return ResponseEntity con el detalle del préstamo (éxito) o el mensaje de error
     */
    @PostMapping("/alquilar")
    public ResponseEntity<Map<String, Object>> alquilarJuego(@RequestBody PrestamoRequest request) {
        return prestamoService.alquilar(request);
    }
}
