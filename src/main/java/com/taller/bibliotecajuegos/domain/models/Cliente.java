package com.taller.bibliotecajuegos.domain.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 * Representa a un cliente del sistema de la biblioteca de juegos.
 *
 * <p>Mantiene la información personal del cliente y la lista de juegos
 * que tiene actualmente prestados. Aplica la regla de negocio que impide
 * tener más de 3 juegos alquilados simultáneamente.</p>
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
@Entity
public class Cliente {

    /** Identificador único del cliente (clave primaria). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre del cliente. */
    private String nombre;

    /** Correo electrónico del cliente. */
    private String email;

    /** Lista de contenidos digitales prestados actualmente al cliente. */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cliente_juegos_prestados")
    private List<ContenidoDigital> juegosPrestados = new ArrayList<>();

    /**
     * Constructor vacío obligatorio para JPA.
     */
    protected Cliente() {
    }

    /**
     * Crea un nuevo cliente inicializando su lista de juegos prestados vacía.
     * El identificador se asigna automáticamente al guardar en la base de datos.
     *
     * @param nombre nombre del cliente
     * @param email correo electrónico del cliente
     */
    public Cliente(String nombre, String email) {
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
    public boolean prestarJuego(ContenidoDigital juego) {
        if (getJuegosPrestados().size() >= 3) {
            return false;
        }
        getJuegosPrestados().add(juego);
        return true;
    }

    /**
     * Devuelve un juego previamente prestado por el cliente.
     *
     * @param juego el contenido digital a devolver
     * @return {@code true} si se devolvió correctamente, {@code false} si el cliente no lo tenía
     */
    public boolean devolverJuego(ContenidoDigital juego) {
        return getJuegosPrestados().remove(juego);
    }

    /**
     * Obtiene el identificador del cliente.
     *
     * @return id del cliente
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualiza el nombre del cliente.
     *
     * @param nombre nuevo nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return email del cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Actualiza el correo electrónico del cliente.
     *
     * @param email nuevo correo electrónico del cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la lista de los juegos prestados actualmente.
     *
     * @return lista de juegos prestados
     */
    public List<ContenidoDigital> getJuegosPrestados() {
        if (juegosPrestados == null) {
            juegosPrestados = new ArrayList<>();
        }
        return juegosPrestados;
    }

    /**
     * Actualiza la lista completa de juegos prestados.
     *
     * @param juegosPrestados nueva lista de juegos
     */
    public void setJuegosPrestados(List<ContenidoDigital> juegosPrestados) {
        this.juegosPrestados = juegosPrestados;
    }
}
