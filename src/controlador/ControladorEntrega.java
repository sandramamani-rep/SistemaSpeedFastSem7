package controlador;

import dao.EntregaDAO;
import modelo.Entrega;
import java.sql.SQLException;
import java.util.List;

/** Valida la selección y coordina el registro de entregas. */
public class ControladorEntrega {
    private final EntregaDAO entregaDAO = new EntregaDAO();

    public boolean registrarEntrega(int idPedido, int idRepartidor) throws SQLException {
        if (idPedido <= 0 || idRepartidor <= 0) {
            return false;
        }
        return entregaDAO.guardar(new Entrega(idPedido, idRepartidor));
    }

    public List<Entrega> obtenerEntregas() throws SQLException {
        return entregaDAO.listarTodos();
    }
}
