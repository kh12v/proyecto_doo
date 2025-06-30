package gui.Paneles.PanelMascotas;

import Controladores.Estado.JaulaState;
import Controladores.Estado.MascotaState;
import Logica.Especies;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JaulaPanel extends JPanel {
    private final static int ANCHO = 150;
    private final static int ALTO = 150;
    private Especies especie;

    private JPanel cargarImagen(int ancho, int alto, String ruta) {
        JPanel panel = new JPanel();

        try {
            BufferedImage imagenOriginal = ImageIO.read(new File(ruta));

            if (imagenOriginal != null) {
                Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

                JLabel labelImagen = new JLabel(new ImageIcon(imagenEscalada));
                labelImagen.setBackground(new Color(0,0,0,0));

                labelImagen.setPreferredSize(new Dimension(ancho, alto));
                labelImagen.setMinimumSize(new Dimension(ancho, alto));
                labelImagen.setMaximumSize(new Dimension(ancho, alto));
                labelImagen.setBorder(new EmptyBorder(0, 0, 0, 0));

                panel.add(labelImagen, BorderLayout.CENTER);
                panel.setBackground(new Color(0,0,0,0));
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen: " + ruta, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return panel;
    }

    public JaulaPanel(Especies especie) {
        setBackground(new Color(255, 0, 0,0));
        setLayout(new OverlayLayout(this));
        setVisible(true);
        this.especie = especie;
        if (especie == Especies.NullGrande){
            add(cargarImagen(ANCHO, ALTO, "recursos/espacioDisponible.png"));
        } else if (especie == Especies.NullPequeno) {
            add(cargarImagen((int)(ANCHO*0.75), (int)(ALTO*0.75), "recursos/espacioDisponible.png"));
        }
        else {
            if (especie.getEsAnimalGrande()) add(cargarImagen(ANCHO, ALTO, "recursos/jaulas/" + especie.toString().toLowerCase() + ".png"));
            else add(cargarImagen((int)(ANCHO*0.75), (int)(ALTO*0.75), "recursos/jaulas/" + especie.toString().toLowerCase() + ".png"));
        }
    }

    public void modificarJaula(JaulaState estado){
        removeAll();
        especie = estado.mascotaState().especie();
        if (especie == Especies.NullGrande){
            add(cargarImagen(ANCHO, ALTO, "recursos/espacioDisponible.png"));
        } else if (especie == Especies.NullPequeno) {
            add(cargarImagen((int)(ANCHO*0.75), (int)(ALTO*0.75), "recursos/espacioDisponible.png"));
        } else {
            if (especie.getEsAnimalGrande()) add(cargarImagen(ANCHO, ALTO, "recursos/jaulas/" + especie.toString().toLowerCase() + ".png"));
            else add(cargarImagen((int)(ANCHO*0.75), (int)(ALTO*0.75), "recursos/jaulas/" + especie.toString().toLowerCase() + ".png"));
        }
    }
}
