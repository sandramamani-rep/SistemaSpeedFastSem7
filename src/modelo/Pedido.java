package modelo;

import java.util.ArrayList;

/**
 * Representa un pedido genérico dentro del sistema de reparto SpeedFast.
 *
 * Esta clase corresponde a la superclase de los distintos tipos de pedidos
 * que maneja la empresa.
 */
public class Pedido {
    private int id;
    private String direccion;
    private String tipo;
    private String estado;



    /**
     * Construye un pedido con sus datos principales.
     *
     *  id identificador único del pedido.
     * @param direccion dirección donde se debe entregar el pedido.
     * @param tipo distancia del pedido en kilómetros.
     * @param  estado estado del pedido
     */
    public Pedido(String direccion, String tipo, String estado) {
        //this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Pedido(int id, String direccion, String tipo, String estado) {
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Asigna un repartidor al pedido.
     *
     * Este método está diseñado para ser sobrescrito por las subclases,
     * permitiendo que cada tipo de pedido implemente su propia lógica
     * de asignación.
     */
    public void asignarRepartidor() {
        System.out.println("=== PEDIDO #"+getId()+" ====");
        System.out.println("-> Asignando repartidor al pedido numero: " + id);
    }
    /**
    * Muestra un resumen del pedido con su identificador,
    * dirección de entrega y distancia.
    */
    public void mostrarResumen(){
        System.out.println("Pedido #" + id);
        System.out.println("Dirección: " + direccion);
        System.out.println("Tipo: " + tipo );
        System.out.println("Estado: " + estado);
    }

    /**
     * Devuelve una representación textual del pedido.
     * @return información del pedido.
     */
    @Override
    public String toString() {
        return "id:" + id + ",direccion:" + direccion + ",tipo:" + tipo + ", estado:" + estado;
    }
}
