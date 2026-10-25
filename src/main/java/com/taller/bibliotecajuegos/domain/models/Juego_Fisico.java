package com.taller.bibliotecajuegos.domain.models;

/**
 * Representa un juego físico dentro de la biblioteca.
 * 
 * <p>Esta clase hereda de {@link Contenido_Digital} y añade propiedades específicas
 * de los juegos físicos, como el peso. Además, implementa su propia
 * regla de negocio para calcular el costo de la renta, el cual incluye un recargo fijo por envío.</p>
 * 
 * @author Sistema Biblioteca de video juegos
 * @version 1.0
 */
public class Juego_Fisico extends Contenido_Digital {
    /** Peso del juego físico en gramos. */
    private double pesoGramos;

    /**
     * Crea un nuevo juego físico con los detalles especificados.
     *
     * @param titulo título del videojuego
     * @param anioLanzamiento año de lanzamiento del videojuego
     * @param precioBase precio base por día del videojuego
     * @param disponible indica si el videojuego está disponible para renta
     * @param pesoGramos peso del juego físico en gramos
     */
    public Juego_Fisico(String titulo, int anioLanzamiento, double precioBase, boolean disponible, double pesoGramos) {
        super(titulo, anioLanzamiento, precioBase, disponible);
        this.pesoGramos = pesoGramos;
    }

    /**
     * Calcula el precio de renta para la cantidad de días solicitada.
     * <p>Regla de negocio: El precio se calcula como (precioBase * dias) + 2.00 
     * correspondientes a un recargo fijo por costo de envío.</p>
     *
     * @param dias cantidad de días de renta
     * @return precio total de la renta incluyendo el envío
     */
    @Override 
    public double calcularPrecioRenta(int dias) {
        return (getPrecioBase() * dias) + 2.00;
    }

    /**
     * Obtiene el peso del juego físico.
     * @return peso en gramos
     */
    public double getPesoGramos() {
        return pesoGramos;
    }

    /**
     * Actualiza el peso del juego físico.
     * @param pesoGramos nuevo peso en gramos
     */
    public void setPesoGramos(double pesoGramos) {
        this.pesoGramos = pesoGramos;
    }
}