package controlador;

import dao.PedidoDAO;
import modelo.Pedido;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorPedido {
    private final List<Pedido> pedidos;
    private PedidoDAO pedidoDAO = new PedidoDAO();

    public ControladorPedido() {
        pedidos = new ArrayList<>();
    }


    public boolean registrarPedido(String direccion, String tipo, String estado) throws SQLException {
        if (direccion == null || direccion.isBlank()
                || tipo == null || tipo.isBlank()
                || estado == null || estado.isBlank()) {
            return false;
        }
        return pedidoDAO.guardar(new Pedido(direccion.trim(), tipo, estado));
    }


    /**
     * Entrega la lista de pedidos para que la vista pueda mostrarla en la tabla.
     */
    public List<Pedido> obtenerPedidos() throws SQLException {
        return this.pedidoDAO.listarTodos();
    }

}




