package com.taller.bibliotecajuegos.records;

public record PrestamoRequest(Long clienteId, Long juegoId, int dias) {
}