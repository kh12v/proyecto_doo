package gui.Paneles.PanelTienda;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.V_CambiarVentanaEvento;
import gui.Paneles.Ventanas;

import javax.swing.*;
import java.awt.*;

/**
 * En este panel el usuario puede acceder a los otros menus de la simulación
 */
public class PanelMenu extends JPanel implements Publicador {
    private static final Color COLOR_DE_FONDO = Color.GRAY;
    public EventHandler handler;

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
    }

    private class BotonOpcion extends JButton {
        private static final int ANCHO = 150;
        private static final int ALTO = 100;

        public BotonOpcion(String texto, Ventanas ventana) {
            setPreferredSize(new Dimension(ANCHO, ALTO));
            setMinimumSize(new Dimension(ANCHO, ALTO));
            setMaximumSize(new Dimension(ANCHO, ALTO));

            addActionListener(e -> handler.enviar(new V_CambiarVentanaEvento(ventana)));

            setText(texto);
        }
    }

    public PanelMenu() {
        setLayout(new BorderLayout());
        setBackground(COLOR_DE_FONDO);

        Box box = Box.createHorizontalBox();

        box.add(Box.createHorizontalGlue());
        box.add(new BotonOpcion("Mascotas", Ventanas.MASCOTAS));
        box.add(Box.createHorizontalGlue());
        box.add(new BotonOpcion("Empleados", Ventanas.PERSONAL));
        box.add(Box.createHorizontalGlue());
        box.add(new BotonOpcion("Comprar", Ventanas.COMPRAS));
        box.add(Box.createHorizontalGlue());

        add(box, BorderLayout.CENTER);
    }
}
