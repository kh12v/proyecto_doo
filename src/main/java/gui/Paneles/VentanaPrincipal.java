package gui.Paneles;

import gui.Paneles.PanelEmpleados.PanelEmpleados;
import gui.Paneles.PanelMascotas.PanelMascotas;
import gui.Paneles.PanelTienda.PanelTienda;

import javax.swing.*;
import java.awt.*;

/**
 * Esta es la ventana principal desde la que se mostrarán los paneles
 */
public class VentanaPrincipal extends JFrame {
    static private final int ANCHO = 950;
    static private final int ALTO = 950;

    private JPanel panelPrincipal = null;

    private final PanelTienda PANEL_TIENDA;
    private final PanelMascotas PANEL_MASCOTAS;
    private final PanelEmpleados PANEL_EMPLEADOS;
    // TODO: Crear panel compras
    // private final PanelCompras PANEL_COMPRAS;

    public enum PANELES {
        PANEL_TIENDA, PANEL_MASCOTAS, PANEL_EMPLEADOS, PANEL_COMPRAS
    }

    public VentanaPrincipal(String titulo) {
        setTitle(titulo);
        setSize(new Dimension(ANCHO, ALTO));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PANEL_TIENDA = new PanelTienda(this);
        PANEL_MASCOTAS = new PanelMascotas(this);
        PANEL_EMPLEADOS = new PanelEmpleados(this);
        // TODO: Crear panel compras
        // panelCompras = new PanelCompras(this);

        setPanelPrincipal(PANELES.PANEL_TIENDA);

        add(panelPrincipal);

        setLocationRelativeTo(null);
    }

    /**
     * Reemplaza el panelPrincipal por aquel que indica el argumento
     * @param panelAAbrir: Indica cual es el panel que se desea mostrar
     */
    public void setPanelPrincipal(PANELES panelAAbrir) {
        if (panelPrincipal != null) {
            remove(panelPrincipal);
        }

        switch (panelAAbrir) {
            case PANEL_TIENDA:
                panelPrincipal = PANEL_TIENDA;
                break;
            case PANEL_MASCOTAS:
                panelPrincipal = PANEL_MASCOTAS;
                break;
            case PANEL_EMPLEADOS:
                panelPrincipal = PANEL_EMPLEADOS;
                break;
            case PANEL_COMPRAS:
                // TODO: Crear panel compras
                // panelPrincipal = panelCompras;
                break;
        }

        add(panelPrincipal);

        revalidate();
        repaint();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void mostrar() {
        setVisible(true);
    }
}
