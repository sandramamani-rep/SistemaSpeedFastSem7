package dao;

import modelo.Entrega;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Acceso a la tabla entrega. Las conexiones se cierran con try-with-resources. */
public class EntregaDAO {
    public boolean guardar(Entrega entrega) throws SQLException {
        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());
            stmt.setDate(3, java.sql.Date.valueOf(entrega.getFecha()));
            stmt.setTime(4, java.sql.Time.valueOf(entrega.getHora()));
            return stmt.executeUpdate() == 1;
        }
    }

    public List<Entrega> listarTodos() throws SQLException {
        String sql = "SELECT id, id_pedido, id_repartidor, fecha, hora FROM entrega ORDER BY id DESC";
        List<Entrega> entregas = new ArrayList<>();
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                entregas.add(new Entrega(rs.getInt("id"),
                        rs.getInt("id_pedido"), rs.getInt("id_repartidor"),
                        rs.getDate("fecha").toLocalDate(), rs.getTime("hora").toLocalTime()));
            }
        }
        return entregas;
    }
}
