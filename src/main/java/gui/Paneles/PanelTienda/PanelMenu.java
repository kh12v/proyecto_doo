package gui.Paneles.PanelTienda;

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

        public BotonOpcion(String texto, VentanaPrincipal ventanaPrincipal) {
            setPreferredSize(new Dimension(ANCHO, ALTO));
            setMinimumSize(new Dimension(ANCHO, ALTO));
            setMaximumSize(new Dimension(ANCHO, ALTO));

            addActionListener((ActionEvent e) -> {
                ventanaPrincipal.setPanelPrincipal(new PanelMascotas());
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
        box.add(new BotonOpcion("Mascotas", ventanaPrincipal));
        box.add(Box.createHorizontalGlue());
        box.add(new BotonOpcion("Empleados", ventanaPrincipal));
        box.add(Box.createHorizontalGlue());
        box.add(new BotonOpcion("Comprar", ventanaPrincipal));
        box.add(Box.createHorizontalGlue());

        add(box, BorderLayout.CENTER);
    }
}
