package gui.Paneles.PanelMascotas;

import Controladores.Estado.JaulaState;
import Logica.Enums.Especies;
import Logica.Enums.TipoContenedor;
import gui.Paneles.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class JaulaPanel extends JPanel {
    private final static int ANCHO = 150;
    private final static int ALTO = 150;
    public static boolean menuAbierto = false;
    private Especies especie = Especies.Null;
    private int id = -1;
    private String nombre = "";

    public JaulaPanel(Especies especie, TipoContenedor tipoContenedor) {
        setBackground(new Color(255, 0, 0, 0));
        setLayout(new OverlayLayout(this));
        setVisible(true);
        this.especie = especie;
        ImageLoader loader = ImageLoader.getInstancia();
        if (especie == Especies.Null && tipoContenedor == TipoContenedor.JaulaGrande) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            add(cargarImagen(ANCHO, ALTO, "/espacioDisponible.png"));
        } else if (especie == Especies.Null && tipoContenedor == TipoContenedor.JaulaPequena) {
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

    public void modificarJaula(JaulaState estado) {
        removeAll();
        especie = estado.mascotaState().especie();
        id = estado.id();
        nombre = estado.mascotaState().nombre();
        if (especie == Especies.Null && estado.tipo() == TipoContenedor.JaulaGrande) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            add(cargarImagen(ANCHO, ALTO, "/espacioDisponible.png"));
        } else if (especie == Especies.Null && estado.tipo() == TipoContenedor.JaulaPequena) {
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

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (especie == Especies.Null || id == -1) return;
            if (JaulaPanel.menuAbierto) return;
            JaulaPanel.menuAbierto = true;

            PanelMascotas.mostrarMenuInteractuar(id, nombre, especie);
        }
    }
}
