package com.taller.bibliotecajuegos.domain.models;

import jakarta.persistence.Entity;

/**
 * Representa un juego digital dentro de la biblioteca.
 *
 * <p>Esta clase hereda de {@link ContenidoDigital} y añade propiedades específicas
 * de los juegos digitales, como el tamaño en gigabytes. Implementa su propia
 * regla de negocio para calcular el costo de la renta, aplicando un descuento
 * si el tamaño del juego supera los 50 GB.</p>
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
@Entity
public class JuegoDigital extends ContenidoDigital {

    /** Tamaño del juego digital en gigabytes (GB). */
    private double tamanoGB;

    /**
     * Constructor vacío obligatorio para JPA.
     */
    protected JuegoDigital() {
    }

    /**
     * Crea un nuevo juego digital con los detalles especificados.
     *
     * @param titulo título del videojuego
     * @param anioLanzamiento año de lanzamiento del videojuego
     * @param precioBase precio base por día del videojuego
     * @param disponible indica si el videojuego está disponible para renta
     * @param tamanoGB tamaño del juego en gigabytes (GB)
     */
    public JuegoDigital(String titulo, int anioLanzamiento, double precioBase, boolean disponible, double tamanoGB) {
        super(titulo, anioLanzamiento, precioBase, disponible);
        this.tamanoGB = tamanoGB;
    }

    /**
     * Calcula el precio de renta para la cantidad de días solicitada.
     * <p>Regla de negocio: El precio se calcula como (precioBase * dias).
     * Si el tamaño supera los 50 GB, se aplica un descuento del 10% al total.</p>
     *
     * @param dias cantidad de días de renta
     * @return precio total de la renta (con descuento si aplica)
     */
    @Override
    public double calcularPrecioRenta(int dias) {
        double total = getPrecioBase() * dias;
        if (tamanoGB > 50) {
            return total * 0.90;
        }
        return total;
    }

    /**
     * Identifica este contenido como un juego digital.
     *
     * @return {@code DIGITAL}
     */
    @Override
    public String getTipo() {
        return "DIGITAL";
    }

    /**
     * Obtiene el tamaño del juego digital.
     *
     * @return tamaño en GB
     */
    public double getTamanoGB() {
        return tamanoGB;
    }

    /**
     * Actualiza el tamaño del juego digital.
     *
     * @param tamanoGB nuevo tamaño en GB
     */
    public void setTamanoGB(double tamanoGB) {
        this.tamanoGB = tamanoGB;
    }
}
