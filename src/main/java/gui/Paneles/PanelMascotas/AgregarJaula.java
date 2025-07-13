package gui.Paneles.PanelMascotas;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import gui.Paneles.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Panel que permite añadir una jaula cuando el usuario la selecciona
 */
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

    /**
     * Renderiza una imagen
     * @param ancho: Ancho de la imagen
     * @param alto: Alto de la imagen
     * @param ruta: Ruta de la imagen
     * @return Panel con un JLabel que contiene a la imagen
     */
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

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    @Override
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
    }

    /**
     * Muestra un menu que permite al usuario seleccionar el tipo de jaula que desea agregar
     */
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
