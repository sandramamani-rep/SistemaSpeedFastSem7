package main;

import controlador.ControladorEntrega;
import controlador.ControladorPedido;
import controlador.ControladorRepartidores;
import vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ControladorPedido controlador = new ControladorPedido();
            ControladorRepartidores controladorRepartidores = new ControladorRepartidores();
            ControladorEntrega controladorEntrega = new ControladorEntrega();
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(controlador, controladorRepartidores, controladorEntrega);
            ventanaPrincipal.setVisible(true);
        });
    }
}
