package com.taller.bibliotecajuegos.services;

import org.springframework.stereotype.Service;

import com.taller.bibliotecajuegos.domain.models.Cliente;
import com.taller.bibliotecajuegos.domain.repositories.ClienteRepository;

/**
 * Servicio de negocio para el registro de clientes.
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
@Service
public class ClienteService {

    /** Repositorio de clientes. */
    private final ClienteRepository clienteRepository;

    /**
     * Inyecta el repositorio de clientes.
     *
     * @param clienteRepository repositorio JPA de clientes
     */
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Registra un nuevo cliente inicializando su lista de préstamos vacía.
     *
     * @param cliente datos del cliente (nombre y correo)
     * @return el cliente guardado con su identificador asignado
     */
    public Cliente registrarCliente(Cliente cliente) {
        return clienteRepository.save(new Cliente(cliente.getNombre(), cliente.getEmail()));
    }
}
