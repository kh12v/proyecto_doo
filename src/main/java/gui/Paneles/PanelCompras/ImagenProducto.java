package gui.Paneles.PanelCompras;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagenProducto extends JPanel {
    private final static int ANCHO = 150;
    private final static int ALTO = 150;

    public ImagenProducto(String ruta, Color colorDeFondo) {
        setBackground(colorDeFondo);
        setVisible(true);

        try {
            BufferedImage imagenOriginal = ImageIO.read(new File(ruta));

            if (imagenOriginal != null) {
                Image imagenEscalada = imagenOriginal.getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH);

                JLabel labelImagen = new JLabel(new ImageIcon(imagenEscalada));
                labelImagen.setBackground(colorDeFondo);

                Dimension tamanio = new Dimension(ANCHO, ALTO);
                labelImagen.setPreferredSize(tamanio);
                labelImagen.setMinimumSize(tamanio);
                labelImagen.setMaximumSize(tamanio);
                labelImagen.setBorder(new EmptyBorder(0, 0, 0, 0));

                add(labelImagen, BorderLayout.CENTER);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen: " + ruta, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
