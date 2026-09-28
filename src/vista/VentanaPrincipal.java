package vista;

import controlador.ControladorEntrega;
import controlador.ControladorPedido;
import controlador.ControladorRepartidores;
import modelo.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentanaPrincipal extends JFrame {
    private JButton btnRegistrarPedido;
    private JButton btnListarPedido;
    private JButton btnSimularEntrega;
    private JPanel ventanaPrincipal;
    private JButton btnCerrar;
    private JTabbedPane tbpVentanas;
    private JTextField txtDireccion;
    private JTextField txtNombreRepartidor;
    private JButton btnAgregarRepartidor;
    private JLabel lblDireccion;
    private JLabel lblTipoPedido;
    private JLabel lblEstado;
    private JLabel lblNombreRepartidos;
    private JComboBox cmbTipoPedido;
    private JComboBox cmbEstadoPedido;
    private JTable tblPedidos;
    private JTable tblRepartidores;
    private JComboBox cmbPedidoEntrega;
    private JComboBox cmbRepartidorEntrega;
    private JButton btnRegistrarEntrega;
    private JTable tblEntregas;
    private JLabel lblSeleccionarPedido;
    private JLabel lblSeleccionarRepartidor;

    private ControladorPedido controladorPedidos;
    private ControladorRepartidores controladorRepartidores;
    private ControladorEntrega controladorEntregas;

    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloTablaRepartidores;
    private DefaultTableModel modeloTablaEntregas;


    public VentanaPrincipal(ControladorPedido controladorPedidos, ControladorRepartidores controladorRepartidores, ControladorEntrega controladorEntregas) {
        this.controladorPedidos = controladorPedidos;
        this.controladorRepartidores = controladorRepartidores;
        this.controladorEntregas = controladorEntregas;
        configurarVentana();
        configurarComponentes();
        configurarEventos();
        inicializarTabla();
        actualizarTabla();
        inicializarTablaRepartidores();
        inicializarTablaEntregas();
    }

    /**
     * Configuración general de la ventana JFrame.
     */
    private void configurarVentana() {
        setTitle("Sistema Speed Fast");
        setContentPane(ventanaPrincipal);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550, 500);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void configurarComponentes(){
        for (EstadoPedido estado : EstadoPedido.values()){
            cmbEstadoPedido.addItem(estado.toString());
        }

        for (TipoPedido tipo : TipoPedido.values()){
            cmbTipoPedido.addItem(tipo.toString());
        }
    }

    /**
     * Conecta los botones visuales con el código que se ejecutará al hacer clic.
     */
    private void configurarEventos() {
        btnRegistrarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPedido();
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnAgregarRepartidor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarRepartidor();
            }
        });

        btnRegistrarEntrega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEntrega();
            }
        });

        tbpVentanas.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = tbpVentanas.getSelectedIndex();
                String ventana = tbpVentanas.getTitleAt(selectedIndex);
                if (ventana.equalsIgnoreCase("repartidores")) {
                    actualizarTablaRepartidores();
                }
                if (ventana.equalsIgnoreCase("entregas")) {
                    actualizarEntregas();
                }
            }
        });
    }

    private void agregarPedido(){
        try{
            String direccion = txtDireccion.getText().trim();
            String tipoPedido = cmbTipoPedido.getSelectedItem().toString();
            String estado = cmbEstadoPedido.getSelectedItem().toString();

            boolean resultado = controladorPedidos.registrarPedido(direccion, tipoPedido, estado);
            if (resultado){
                JOptionPane.showMessageDialog(
                        this,
                        "Pedido agregado correctamente.",
                        "Registro exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );
                actualizarTabla();
            }else{
                JOptionPane.showMessageDialog(
                        this,
                        "No se agrego el pedido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo registrar la entrega: " + ex.getMessage(),
                    "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        }

        //limpiarCampos();
        //this.dispose();
    }

    private void agregarRepartidor() {

        String nombre = txtNombreRepartidor.getText().trim();

        try{
            boolean resultado = controladorRepartidores.registrarRepartidor(nombre);
            if (resultado) {
                JOptionPane.showMessageDialog(
                        this,
                        "Repartidor agregado correctamente.",
                        "Registro exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );
                actualizarTablaRepartidores();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se agrego el repartidor.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo registrar la entrega: " + ex.getMessage(),
                    "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void registrarEntrega() {
        Pedido pedido = (Pedido) cmbPedidoEntrega.getSelectedItem();
        Repartidor repartidor = (Repartidor) cmbRepartidorEntrega.getSelectedItem();
        if (pedido == null || repartidor == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un pedido y un repartidor.");
            return;
        }
        try {
            if (controladorEntregas.registrarEntrega(pedido.getId(), repartidor.getId())) {
                actualizarEntregas();
                JOptionPane.showMessageDialog(this, "Entrega registrada correctamente.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo registrar la entrega: " + ex.getMessage(),
                    "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inicializarTabla(){
        String[] columnas = {"Numero", "Direccion", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tblPedidos.setModel(modeloTabla);
        tblPedidos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void inicializarTablaRepartidores(){
        String[] columnas = {"ID", "NOMBRE"};
        modeloTablaRepartidores = new DefaultTableModel(columnas,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tblRepartidores.setModel(modeloTablaRepartidores);
        tblRepartidores.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblRepartidores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    /**
     * Vuelve a cargar la tabla usando los pedidos almacenados.
     */
    private void actualizarTabla() {
        try{
            modeloTabla.setRowCount(0);
            for (Pedido pedido : controladorPedidos.obtenerPedidos()) {
                Object[] fila = {
                        pedido.getId(),
                        pedido.getDireccion(),
                        pedido.getTipo(),
                        pedido.getEstado()
                };

                modeloTabla.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los pedidos: " + ex.getMessage(),
                    "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTablaRepartidores() {
        try{
            modeloTablaRepartidores.setRowCount(0);
            for (Repartidor r : controladorRepartidores.obtenerRepartidores()) {
                Object[] fila = {
                        r.getId(),
                        r.getNombre()
                };

                modeloTablaRepartidores.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los repartidores: " + ex.getMessage(),
                    "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inicializarTablaEntregas() {
        modeloTablaEntregas = new DefaultTableModel(
                new String[]{"ID", "Pedido", "Dirección", "Repartidor", "Fecha", "Hora"}, 0) {
            @Override public boolean isCellEditable(int fila, int columna) { return false; }
        };
        tblEntregas.setModel(modeloTablaEntregas);
        tblEntregas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void actualizarEntregas() {
        try {
            List<Pedido> pedidos = controladorPedidos.obtenerPedidos();
            List<Repartidor> repartidores = controladorRepartidores.obtenerRepartidores();
            Map<Integer, Pedido> pedidosPorId = new HashMap<>();
            Map<Integer, Repartidor> repartidoresPorId = new HashMap<>();
            cmbPedidoEntrega.removeAllItems();
            cmbRepartidorEntrega.removeAllItems();
            for (Pedido pedido : pedidos) {
                pedidosPorId.put(pedido.getId(), pedido);
                cmbPedidoEntrega.addItem(pedido);
            }
            for (Repartidor repartidor : repartidores) {
                repartidoresPorId.put(repartidor.getId(), repartidor);
                cmbRepartidorEntrega.addItem(repartidor);
            }
            modeloTablaEntregas.setRowCount(0);
            for (Entrega entrega : controladorEntregas.obtenerEntregas()) {
                Pedido pedido = pedidosPorId.get(entrega.getIdPedido());
                Repartidor repartidor = repartidoresPorId.get(entrega.getIdRepartidor());
                modeloTablaEntregas.addRow(new Object[]{entrega.getId(), entrega.getIdPedido(),
                        pedido == null ? "" : pedido.getDireccion(),
                        repartidor == null ? entrega.getIdRepartidor() : repartidor.getNombre(),
                        entrega.getFecha(), entrega.getHora()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar las entregas: " + ex.getMessage(),
                    "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

}
