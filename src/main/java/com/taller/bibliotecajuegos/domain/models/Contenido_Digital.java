package com.taller.bibliotecajuegos.domain.models;

import java.util.UUID;

/**
 * Representa el contenido digital que puede formar parte de la biblioteca,
 * como un videojuego disponible para renta.
 *
 * <p>Las clases concretas heredan sus datos comunes e implementan el cálculo
 * del precio de renta según la cantidad de días. El identificador se asigna
 * automáticamente al guardar una entidad concreta en la base de datos.</p>
 * 
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
public abstract class Contenido_Digital {
    /** Identificador único del id (UUID). */
    private final Long id;
    /** Título del videojuego. */
    private String titulo;
    /** Año de lanzamiento del videojuego. */
    private int anioLanzamiento;
    /** Precio base utilizado por las clases concretas para calcular la renta. */
    private double precioBase;
    /** Indica si el contenido está disponible para renta. */
    private boolean disponible;

    /**
     * Crea contenido digital. El identificador se asignará al guardar la
     * entidad concreta en la base de datos.
     *
     * @param titulo título del videojuego
     * @param anioLanzamiento año de lanzamiento del videojuego
     * @param precioBase precio base del videojuego
     * @param disponible indica si el videojuego está disponible para renta
     */
    public Contenido_Digital(String titulo, int anioLanzamiento, double precioBase, boolean disponible) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE; // Genera un ID único
        this.titulo = titulo;
        this.anioLanzamiento = anioLanzamiento;
        this.precioBase = precioBase;
        this.disponible = disponible;
    }

    /**
     * Calcula el precio de renta para la cantidad de días solicitada.
     *
     * @param dias cantidad de días de renta
     * @return precio total de la renta
     */
    public abstract double calcularPrecioRenta(int dias);

    /** 
     * 
     * @return identificador asignado por la base de datos, o {@code null} antes de guardar 
     */
    public Long getId() {
        return id;
    }

    /** 
     * Obtiene el título del videojuego
     * @return título del videojuego 
     */
    public String getTitulo() {
        return titulo;
    }

    /** 
     * Actualiza el título del videojuego
     * @param titulo nuevo título del videojuego 
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el año lanzamiento del videojuego 
     * @return año de lanzamiento del videojuego 
     */
    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    /**
     * Actualiza el año de lanzamiento del videojuego
     * @param anioLanzamiento nuevo año de lanzamiento 
     */
    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    /** 
     * Obtiene el precio base del videojuego
     * @return precio base del videojuego 
     */
    public double getPrecioBase() {
        return precioBase;
    }

    /** 
     * Actualiza el precio base del videojuego
     * @param precioBase nuevo precio base del videojuego 
     */
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    /** 
     * Obtiene el estado de disponible del contenido digital
     * @return {@code true} si el contenido está disponible para renta 
     */
    public boolean isDisponible() {
        return disponible;
    }

    /** 
     * Actualiza el estado de disponobilidad del contenido digital
     * @param disponible nuevo estado de disponible 
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}