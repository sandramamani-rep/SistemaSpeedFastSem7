package controlador;

import dao.PedidoDAO;
import dao.RepartidorDAO;
import modelo.Pedido;
import modelo.Repartidor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorRepartidores {
    private final List<Repartidor> repartidores;
    private RepartidorDAO repartidorDAO = new RepartidorDAO();

    public ControladorRepartidores() {
        repartidores = new ArrayList<>();
    }

    public boolean registrarRepartidor(String nombre) throws SQLException {
        if (nombre == null || nombre.isBlank()) {
            return false;
        }
        return repartidorDAO.guardar(new Repartidor(nombre));
    }

    /**
     * Entrega la lista de pedidos para que la vista pueda mostrarla en la tabla.
     */
    public List<Repartidor> obtenerRepartidores() throws SQLException {
        return this.repartidorDAO.listarTodos();
    }

}
