package com.taller.bibliotecajuegos.services;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller.bibliotecajuegos.domain.models.Cliente;
import com.taller.bibliotecajuegos.domain.models.ContenidoDigital;
import com.taller.bibliotecajuegos.domain.repositories.ClienteRepository;
import com.taller.bibliotecajuegos.domain.repositories.ContenidoDigitalRepository;
import com.taller.bibliotecajuegos.records.PrestamoRequest;

/**
 * Servicio de negocio encargado de procesar los préstamos de videojuegos.
 * Aplica las validaciones de disponibilidad y del límite de 3 juegos por cliente,
 * y calcula el costo total mediante el método polimórfico {@code calcularPrecioRenta}.
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
@Service
public class PrestamoService {

    /** Repositorio para acceder a los datos de los clientes. */
    private final ClienteRepository clienteRepository;

    /** Repositorio para acceder a los datos de los juegos (físicos y digitales). */
    private final ContenidoDigitalRepository juegoRepository;

    /**
     * Constructor para la inyección de dependencias de los repositorios.
     *
     * @param clienteRepository repositorio de clientes
     * @param juegoRepository repositorio de juegos
     */
    public PrestamoService(ClienteRepository clienteRepository, ContenidoDigitalRepository juegoRepository) {
        this.clienteRepository = clienteRepository;
        this.juegoRepository = juegoRepository;
    }

    /**
     * Procesa el alquiler de un juego para un cliente específico.
     *
     * <p>Validación 1: el juego debe existir y estar disponible.</p>
     * <p>Validación 2: el cliente no puede superar el límite de 3 juegos alquilados.</p>
     *
     * @param request datos del préstamo: clienteId, juegoId y días
     * @return respuesta con el detalle del préstamo (éxito) o el mensaje de error
     */
    @Transactional
    public ResponseEntity<Map<String, Object>> alquilar(PrestamoRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();

        Optional<Cliente> clienteOpt = clienteRepository.findById(request.clienteId());
        Optional<ContenidoDigital> juegoOpt = juegoRepository.findById(request.juegoId());

        if (clienteOpt.isEmpty() || juegoOpt.isEmpty()) {
            response.put("error", "Cliente o juego no encontrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Cliente cliente = clienteOpt.get();
        ContenidoDigital juego = juegoOpt.get();

        if (!juego.isDisponible()) {
            response.put("error", "El juego no esta disponible para alquiler.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        boolean prestamoExitoso = cliente.prestarJuego(juego);
        if (!prestamoExitoso) {
            response.put("error", "El cliente ya alcanzo el limite de 3 juegos alquilados.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        juego.setDisponible(false);
        juegoRepository.save(juego);
        clienteRepository.save(cliente);

        double costoTotal = juego.calcularPrecioRenta(request.dias());

        response.put("mensaje", "Prestamo realizado con exito.");
        response.put("cliente", cliente.getNombre());
        response.put("juego", juego.getTitulo());
        response.put("dias", request.dias());
        response.put("costoTotal", costoTotal);
        return ResponseEntity.ok(response);
    }
}
