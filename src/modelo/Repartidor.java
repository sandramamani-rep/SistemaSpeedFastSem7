package modelo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representa a un repartidor de SpeedFast que ejecuta las entregas
 * de forma concurrente.
 *
 * <p>La clase implementa {@link Runnable}, por lo que cada instancia
 * puede ser ejecutada por un hilo. Los repartidores comparten una
 * instancia de {@link ZonaDeCarga}, desde la cual retiran los pedidos
 * disponibles de manera segura.</p>
 */
public class Repartidor {

    private int id;
    private String nombre;

    /**
     * Construye un repartidor asociado a una zona de carga compartida.
     *
     * @param nombre nombre del repartidor.
     */
    public Repartidor(String nombre) {
        this.nombre = nombre;
    }
    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "id=" + id + ", nombre=" + nombre;
    }
}
