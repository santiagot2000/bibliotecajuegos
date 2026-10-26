package com.taller.bibliotecajuegos.controllers;

import com.taller.bibliotecajuegos.domain.models.Cliente;
import com.taller.bibliotecajuegos.domain.models.Contenido_Digital;
import com.taller.bibliotecajuegos.domain.repositories.ClienteRepository;
import com.taller.bibliotecajuegos.domain.repositories.ContenidoDigitalRepository;
import com.taller.bibliotecajuegos.records.PrestamoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador REST encargado de gestionar las operaciones de alquiler de videojuegos.
 * Expone los endpoints para procesar los préstamos aplicando las reglas de negocio.
 */
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final ClienteRepository clienteRepository;
    private final ContenidoDigitalRepository juegoRepository;

    /**
     * Constructor para la inyección de dependencias de los repositorios.
     * 
     * @param clienteRepository Repositorio para acceder a los datos de los clientes.
     * @param juegoRepository Repositorio para acceder a los datos de los juegos (físicos y digitales).
     */
    public PrestamoController(ClienteRepository clienteRepository, ContenidoDigitalRepository juegoRepository) {
        this.clienteRepository = clienteRepository;
        this.juegoRepository = juegoRepository;
    }

    /**
     * POST /api/prestamos/alquilar
     * Procesa el alquiler de un juego para un cliente específico.
     * 
     * @param request Objeto tipo record que contiene clienteId, juegoId y la cantidad de días.
     * @return ResponseEntity con el detalle del préstamo (éxito) o el mensaje de error (bad request).
     */
    @PostMapping("/alquilar")
    public ResponseEntity<Map<String, Object>> alquilarJuego(@RequestBody PrestamoRequest request) {
        Map<String, Object> response = new HashMap<>();

        // 1. Buscar el cliente y el juego en la base de datos usando los datos del record
        Optional<Cliente> clienteOpt = clienteRepository.findById(request.clienteId());
        Optional<Contenido_Digital> juegoOpt = juegoRepository.findById(request.juegoId());

        // Validar que ambos existan en la base de datos
        if (clienteOpt.isEmpty() || juegoOpt.isEmpty()) {
            response.put("error", "Cliente o Juego no encontrado.");
            return ResponseEntity.badRequest().body(response);
        }

        Cliente cliente = clienteOpt.get();
        Contenido_Digital juego = juegoOpt.get();

        // 2. Validación de regla de negocio: Verificar que el juego esté disponible para alquiler
        if (!juego.isDisponible()) {
            response.put("error", "El juego no está disponible en este momento.");
            return ResponseEntity.badRequest().body(response);
        }

        // 3. Validación de regla de negocio: El cliente no puede superar el límite de 3 juegos
        boolean prestamoExitoso = cliente.prestarJuego(juego);
        if (!prestamoExitoso) {
            response.put("error", "El cliente ya alcanzó el límite de 3 juegos prestados.");
            return ResponseEntity.badRequest().body(response);
        }

        // 4. Actualizar el estado del juego a 'ocupado' y guardar los cambios en la BD
        juego.setDisponible(false);
        juegoRepository.save(juego);
        clienteRepository.save(cliente); // Guarda la relación del juego en la lista del cliente

        // 5. Cálculo del precio mediante POLIMORFISMO
        // Dependiendo de si 'juego' es instancia de Juego_Fisico o Juego_Digital, 
        // ejecutará su propia versión sobrescrita de 'calcularPrecioRenta'.
        double costoTotal = juego.calcularPrecioRenta(request.dias());

        // 6. Construir la respuesta JSON exitosa con los detalles del alquiler
        response.put("mensaje", "Préstamo realizado con éxito.");
        response.put("cliente", cliente.getNombre());
        response.put("juego", juego.getTitulo());
        response.put("dias", request.dias());
        response.put("costoTotal", costoTotal);

        return ResponseEntity.ok(response);
    }
}