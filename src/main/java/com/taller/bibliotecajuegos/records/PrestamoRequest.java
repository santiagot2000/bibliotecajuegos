package com.taller.bibliotecajuegos.records;

/**
 * DTO inmutable usado para transferir los datos de un alquiler en las peticiones HTTP.
 *
 * @param clienteId identificador del cliente que solicita el préstamo
 * @param juegoId identificador del juego a alquilar
 * @param dias cantidad de días de renta
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
public record PrestamoRequest(Long clienteId, Long juegoId, int dias) {
}
