package gui.Paneles.PanelEmpleados;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import gui.Paneles.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AgregarEmpleado extends JPanel implements Publicador {
    private final static Color COLOR_DE_FONDO = Color.GRAY;
    private final static int ANCHO = 150;
    private final static int ALTO = 150;
    public static boolean menuAbierto = false;
    private EventHandler handler;

    public AgregarEmpleado() {
        setBackground(COLOR_DE_FONDO);
        setLayout(new OverlayLayout(this));
        setVisible(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MyMouseListener());
        ImageLoader loader = ImageLoader.getInstancia();
        try {
            JPanel imagen = loader.cargarImagenEscalada(ANCHO, ALTO, "/agregarEmpleado.png");
            add(imagen);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            MenuSeleccionarEmpleado menuSeleccionarEmpleado = new MenuSeleccionarEmpleado();
            menuSeleccionarEmpleado.enviarHandler(handler);

            menuSeleccionarEmpleado.mostrar();
        }
    }
}
