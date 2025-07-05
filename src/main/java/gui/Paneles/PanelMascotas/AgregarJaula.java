package gui.Paneles.PanelMascotas;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import gui.Paneles.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AgregarJaula extends JPanel implements Publicador {
    private final static Color COLOR_DE_FONDO = Color.GRAY;
    private final static int ANCHO = 150;
    private final static int ALTO = 150;
    public static boolean menuAbierto = false;
    private EventHandler handler;

    public AgregarJaula() {
        setBackground(COLOR_DE_FONDO);
        setLayout(new OverlayLayout(this));
        setVisible(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MyMouseListener());

        add(cargarImagen(ANCHO, ALTO, "/jaulas/agregarJaula.png"));
    }

    private JPanel cargarImagen(int ancho, int alto, String ruta) {
        JPanel panel = new JPanel();
        try {
            panel = ImageLoader.getInstancia().cargarImagenEscalada(ancho, alto, ruta);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return panel;
    }

    @Override
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
    }

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (menuAbierto) return;
            menuAbierto = true;

            MenuSeleccionarJaula menuSeleccionarJaula = new MenuSeleccionarJaula();
            menuSeleccionarJaula.enviarHandler(handler);

            menuSeleccionarJaula.mostrar();
        }
    }
}
