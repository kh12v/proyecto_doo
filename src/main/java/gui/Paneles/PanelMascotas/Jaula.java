package gui.Paneles.PanelMascotas;

import Controladores.Estado.MascotaState;
import Logica.Especies;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Jaula extends JPanel {
    private final static Color COLOR_DE_FONDO = Color.GRAY;
    private final static int ANCHO = 150;
    private final static int ALTO = 150;
    private boolean bloqueado;
    private Especies especie;

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

    public Jaula(boolean bloqueada, Especies especie) {
        setBackground(COLOR_DE_FONDO);
        setLayout(new OverlayLayout(this));
        setVisible(true);
        bloqueado = bloqueada;
        this.especie = especie;
        if (bloqueada) {
            add(cargarImagen(ANCHO, ALTO, "recursos/espacioBloqueado.png"));
        } else if (especie == Especies.Null){
            add(cargarImagen(ANCHO, ALTO, "recursos/espacioDisponible.png"));
        } else {
            add(cargarImagen(ANCHO, ALTO, "recursos/jaulas/" + especie.toString().toLowerCase() + ".png"));
        }
    }

    public void modificarJaula(MascotaState estado){
        removeAll();
        if (bloqueado) {
            add(cargarImagen(ANCHO, ALTO, "recursos/espacioBloqueado.png"));
        } else if (especie == Especies.Null){
            add(cargarImagen(ANCHO, ALTO, "recursos/espacioDisponible.png"));
        } else {
            add(cargarImagen(ANCHO, ALTO, "recursos/jaulas/" + especie.toString().toLowerCase() + ".png"));
        }
    }
}
