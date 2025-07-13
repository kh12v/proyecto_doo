package gui.Paneles.PanelMascotas;

import Controladores.Estado.JaulaState;
import Logica.Enums.Especie;
import Logica.Enums.TipoJaula;
import gui.Paneles.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Panel que muestra una jaula y al animal que contiene si es que no está vacia
 */
public class JaulaPanel extends JPanel {
    private final static int ANCHO = 150;
    private final static int ALTO = 150;
    public static boolean menuAbierto = false;
    private Especie especie = Especie.Null;
    private int id = -1;
    private String nombre = "";

    public JaulaPanel(Especie especie, TipoJaula tipoJaula) {
        setBackground(new Color(255, 0, 0, 0));
        setLayout(new OverlayLayout(this));
        setVisible(true);
        this.especie = especie;
        ImageLoader loader = ImageLoader.getInstancia();
        if (especie == Especie.Null && tipoJaula == TipoJaula.JaulaGrande) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            add(cargarImagen(ANCHO, ALTO, "/espacioDisponible.png"));
        } else if (especie == Especie.Null && tipoJaula == TipoJaula.JaulaPequena) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            add(cargarImagen((int) (ANCHO * 0.75), (int) (ALTO * 0.75), "/espacioDisponible.png"));
        } else {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            if (especie.getEsAnimalGrande())
                add(cargarImagen(ANCHO, ALTO, "/jaulas/" + especie.toString().toLowerCase() + ".png"));
            else
                add(cargarImagen((int) (ANCHO * 0.75), (int) (ALTO * 0.75), "/jaulas/" + especie.toString().toLowerCase() + ".png"));
        }
    }

    /**
     * Carga la imagen de la jaula
     * @param ancho: Ancho de la imagen
     * @param alto: Alto de la imagen
     * @param ruta: Ruta de la imagen
     * @return: JPanel que incluye un JLabel con la imagen
     */
    private JPanel cargarImagen(int ancho, int alto, String ruta) {
        ImageLoader loader = ImageLoader.getInstancia();
        JPanel panel = new JPanel();
        try {
            panel = loader.cargarImagenEscalada(ancho, alto, ruta);
            panel.addMouseListener(new MyMouseListener());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return panel;
    }

    /**
     * Modifica una jaula
     * @param estado: La nueva información de la jaula a actualizar
     */
    public void modificarJaula(JaulaState estado) {
        removeAll();
        especie = estado.mascotaState().especie();
        id = estado.id();
        nombre = estado.mascotaState().nombre();
        if (especie == Especie.Null && estado.tipo() == TipoJaula.JaulaGrande) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            add(cargarImagen(ANCHO, ALTO, "/espacioDisponible.png"));
        } else if (especie == Especie.Null && estado.tipo() == TipoJaula.JaulaPequena) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            add(cargarImagen((int) (ANCHO * 0.75), (int) (ALTO * 0.75), "/espacioDisponible.png"));
        } else {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            if (especie.getEsAnimalGrande())
                add(cargarImagen(ANCHO, ALTO, "/jaulas/" + especie.toString().toLowerCase() + ".png"));
            else
                add(cargarImagen((int) (ANCHO * 0.75), (int) (ALTO * 0.75), "/jaulas/" + especie.toString().toLowerCase() + ".png"));
        }
    }

    /**
     * Muestra un menu que permite interactuar con la mascota
     */
    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (especie == Especie.Null || id == -1) return;
            if (JaulaPanel.menuAbierto) return;
            JaulaPanel.menuAbierto = true;

            PanelMascotas.mostrarMenuInteractuar(id, nombre, especie);
        }
    }
}
