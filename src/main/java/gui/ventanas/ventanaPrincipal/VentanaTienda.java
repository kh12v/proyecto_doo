package gui.ventanas.ventanaPrincipal;

import gui.ventanas.Ventana;

import java.awt.*;

/**
 * Esta ventana es con la que comienza la simulación de la tienda aquí el usuario
 * puede interactuar con clientes y acceder a otros menus
 */
public class VentanaTienda extends Ventana {
    static private final int ANCHO = 950;
    static private final int ALTO = 950;

    public VentanaTienda(String titulo) {
        super(titulo, ANCHO, ALTO, true);

        PanelCliente panelCliente = new PanelCliente();
        PanelMenu panelMenu = new PanelMenu();

        add(panelCliente, BorderLayout.NORTH);
        add(panelMenu, BorderLayout.SOUTH);
    }
}
