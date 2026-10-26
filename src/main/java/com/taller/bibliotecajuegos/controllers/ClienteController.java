package com.taller.bibliotecajuegos.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.bibliotecajuegos.domain.models.Cliente;
import com.taller.bibliotecajuegos.services.ClienteService;

/**
 * Controlador REST encargado de registrar clientes en el sistema.
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    /** Servicio con la lógica de registro de clientes. */
    private final ClienteService clienteService;

    /**
     * Inyección del servicio mediante el constructor.
     *
     * @param clienteService servicio de clientes
     */
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * POST /api/clientes
     * Registra un nuevo cliente en la base de datos.
     *
     * @param cliente datos del cliente en formato JSON
     * @return el cliente guardado con su ID generado
     */
    @PostMapping
    public Cliente registrarCliente(@RequestBody Cliente cliente) {
        return clienteService.registrarCliente(cliente);
    }
}
