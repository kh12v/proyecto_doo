package gui.Paneles.PanelTienda;

import gui.Paneles.PanelEmpleados.PanelEmpleados;
import gui.Paneles.PanelMascotas.PanelMascotas;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * En este panel el usuario puede acceder a los otros menus de la simulación
 */
public class PanelMenu extends JPanel {
    private static final Color COLOR_DE_FONDO = Color.GRAY;

    private VentanaPrincipal ventanaPrincipal;

    private static class BotonOpcion extends JButton {
        private static final int ANCHO = 150;
        private static final int ALTO = 100;

        public enum PanelAAbrir {
            PANEL_TIENDA, PANEL_MASCOTAS, PANEL_EMPLEADOS, PANEL_COMPRAS
        }

        public BotonOpcion(String texto, VentanaPrincipal ventanaPrincipal, PanelAAbrir panelAAbrir) {
            setPreferredSize(new Dimension(ANCHO, ALTO));
            setMinimumSize(new Dimension(ANCHO, ALTO));
            setMaximumSize(new Dimension(ANCHO, ALTO));

            addActionListener((ActionEvent e) -> {
                switch (panelAAbrir) {
                    case PANEL_TIENDA: {
                        ventanaPrincipal.setPanelPrincipal(new PanelTienda(ventanaPrincipal));
                        break;
                    } case PANEL_MASCOTAS: {
                        ventanaPrincipal.setPanelPrincipal(new PanelMascotas(ventanaPrincipal));
                        break;
                    } case PANEL_EMPLEADOS: {
                        ventanaPrincipal.setPanelPrincipal(new PanelEmpleados(ventanaPrincipal));
                        break;
                    } case PANEL_COMPRAS: {
                        // TODO: Implementar PanelCompras
                        // ventanaPrincipal.setPanelPrincipal(new PanelCompras(ventanaPrincipal));
                        break;
                    }
                }
            });

            setText(texto);
        }
    }

    public PanelMenu(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(COLOR_DE_FONDO);

        Box box = Box.createHorizontalBox();

        box.add(Box.createHorizontalGlue());
        box.add(new BotonOpcion("Mascotas", ventanaPrincipal, BotonOpcion.PanelAAbrir.PANEL_MASCOTAS));
        box.add(Box.createHorizontalGlue());
        box.add(new BotonOpcion("Empleados", ventanaPrincipal, BotonOpcion.PanelAAbrir.PANEL_EMPLEADOS));
        box.add(Box.createHorizontalGlue());
        box.add(new BotonOpcion("Comprar", ventanaPrincipal, BotonOpcion.PanelAAbrir.PANEL_COMPRAS));
        box.add(Box.createHorizontalGlue());

        add(box, BorderLayout.CENTER);
    }
}
