package com.taller.bibliotecajuegos.controllers;

import com.taller.bibliotecajuegos.domain.models.Cliente;
import com.taller.bibliotecajuegos.domain.repositories.ClienteRepository;
import org.springframework.web.bind.annotation.*;

@RestController // Habilita los endpoints REST
@RequestMapping("/api/clientes") // Ruta base para este controlador
public class ClienteController {

    private final ClienteRepository clienteRepository;

    // Inyección del repositorio mediante el constructor
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * POST /api/clientes
     * Registra un nuevo cliente en la base de datos.
     * 
     * @param cliente Datos del cliente en formato JSON
     * @return El cliente guardado con su ID generado
     */
    @PostMapping
    public Cliente registrarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}