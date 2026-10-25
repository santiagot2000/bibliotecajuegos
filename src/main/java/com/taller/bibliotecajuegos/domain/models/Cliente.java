package com.taller.bibliotecajuegos.domain.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

/**
 * Representa a un cliente del sistema de la biblioteca de juegos.
 * 
 * <p>Mantiene la información personal del cliente y la lista de juegos
 * que tiene actualmente prestados. Aplica la regla de negocio que impide
 * tener más de 3 juegos alquilados simultáneamente.</p>
 * 
 * @author Sistema Biblioteca de video juegos
 * @version 1.0
 */

@Entity // 1. Indica que es una tabla en la BD
public class Cliente {
    /** Identificador único del cliente generado internamente (UUID a Long). */
    @Id // 2. Indica que este es el Primary Key
    private Long id;
    
    /** Nombre del cliente. */
    private String nombre;
    
    /** Correo electrónico del cliente. */
    private String email;
    
    /** Lista de contenidos digitales prestados actualmente al cliente. */
    // 3. Relación: Un cliente puede tener muchos juegos prestados
    @OneToMany(targetEntity = Contenido_Digital.class)
    private List<Contenido_Digital> juegosPrestados;

    // 4. Constructor vacío para JPA
    protected Cliente() {
    }

    /**
     * Crea un nuevo cliente inicializando su lista de juegos prestados vacía
     * y asignando un identificador único automáticamente.
     *
     * @param nombre nombre del cliente
     * @param email correo electrónico del cliente
     */
    public Cliente(String nombre, String email) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE; // Genera un ID único
        this.nombre = nombre;
        this.email = email;
        this.juegosPrestados = new ArrayList<>();
    }

    /**
     * Registra un nuevo préstamo para el cliente.
     * <p>Regla de negocio: Un cliente no puede tener más de 3 juegos alquilados simultáneamente.</p>
     *
     * @param juego el contenido digital que el cliente desea alquilar
     * @return {@code true} si se pudo realizar el préstamo, {@code false} si ya alcanzó el límite
     */
    public boolean prestarJuego(Contenido_Digital juego) {
        if (this.juegosPrestados.size() >= 3) {
            return false; // Se rechaza el préstamo por regla de negocio
        }
        
        this.juegosPrestados.add(juego);
        return true;
    }

    /**
     * Devuelve un juego previamente prestado por el cliente.
     * 
     * @param juego el contenido digital a devolver
     * @return {@code true} si se devolvió correctamente, {@code false} si el cliente no lo tenía
     */
    public boolean devolverJuego(Contenido_Digital juego) {
        return this.juegosPrestados.remove(juego);
    }

    /** 
     * Obtiene el identificador del cliente.
     * @return id del cliente 
     */
    public Long getId() {
        return id;
    }

    /** 
     * Obtiene el nombre del cliente.
     * @return nombre del cliente 
     */
    public String getNombre() {
        return nombre;
    }

    /** 
     * Actualiza el nombre del cliente.
     * @param nombre nuevo nombre del cliente 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** 
     * Obtiene el correo electrónico del cliente.
     * @return email del cliente 
     */
    public String getEmail() {
        return email;
    }

    /** 
     * Actualiza el correo electrónico del cliente.
     * @param email nuevo correo electrónico del cliente 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 
     * Obtiene la lista de los juegos prestados actualmente.
     * @return lista de juegos prestados 
     */
    public List<Contenido_Digital> getJuegosPrestados() {
        return juegosPrestados;
    }

    /** 
     * Actualiza la lista completa de juegos prestados.
     * @param juegosPrestados nueva lista de juegos 
     */
    public void setJuegosPrestados(List<Contenido_Digital> juegosPrestados) {
        this.juegosPrestados = juegosPrestados;
    }
}