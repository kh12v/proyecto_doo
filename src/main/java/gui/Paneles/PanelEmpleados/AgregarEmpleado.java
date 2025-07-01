package gui.Paneles.PanelEmpleados;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import gui.Paneles.PanelMascotas.MenuSeleccionarJaula;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AgregarEmpleado extends JPanel implements Publicador {
    private EventHandler handler;
    private final static Color COLOR_DE_FONDO = Color.GRAY;
    private final static int ANCHO = 150;
    private final static int ALTO = 150;

    public static boolean menuAbierto = false;

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

    private JPanel cargarImagen(int ancho, int alto, String ruta) {
        JPanel panel = new JPanel();

        try {
            BufferedImage imagenOriginal = ImageIO.read(new File(ruta));

            if (imagenOriginal != null) {
                Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

                JLabel labelImagen = new JLabel(new ImageIcon(imagenEscalada));
                labelImagen.setBackground(COLOR_DE_FONDO);

                labelImagen.setPreferredSize(new Dimension(ancho, alto));
                labelImagen.setMinimumSize(new Dimension(ancho, alto));
                labelImagen.setMaximumSize(new Dimension(ancho, alto));
                labelImagen.setBorder(new EmptyBorder(0, 0, 0, 0));

                panel.add(labelImagen, BorderLayout.CENTER);
                panel.setBackground(COLOR_DE_FONDO);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen: " + ruta, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return panel;
    }

    public AgregarEmpleado() {
        setBackground(COLOR_DE_FONDO);
        setLayout(new OverlayLayout(this));
        setVisible(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MyMouseListener());

        add(cargarImagen(ANCHO, ALTO, "recursos/agregarEmpleado.png"));
    }

    @Override
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
    }
}
