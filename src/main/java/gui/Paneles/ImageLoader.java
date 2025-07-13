package gui.Paneles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Clase singleton que se utiliza para cargar imágenes
 */
public class ImageLoader {
    private static final ConcurrentMap<String, BufferedImage> imagenes = new ConcurrentHashMap<>();
    private static ImageLoader instancia;

    private ImageLoader() {
    }

    public static ImageLoader getInstancia() {
        if (instancia == null) {
            instancia = new ImageLoader();
        }
        return instancia;
    }

    public JPanel cargarImagenEscalada(int ancho, int alto, String direccion) throws IOException {
        return crearPanelEscalado(ancho, alto, entregarImagen(direccion));
    }

    public BufferedImage entregarImagen(String direccion) throws IOException {
        if (imagenes.containsKey(direccion)) {
            return imagenes.get(direccion);
        }

        try (InputStream stream = getClass().getResourceAsStream(direccion)) {
            if (stream == null) {
                throw new IOException("Recurso no encontrado: " + direccion);
            }

            BufferedImage imagen = ImageIO.read(stream);
            if (imagen == null) {
                throw new IOException("Formato de imagen no soportado: " + direccion);
            }

            // en caso de que se cargue la imagen mientras comprobamos todo (concurrencia :,,,)
            BufferedImage imagenExistente = imagenes.putIfAbsent(direccion, imagen);
            return imagenExistente != null ? imagenExistente : imagen;
        }
    }


    private JPanel crearPanelEscalado(int ancho, int alto, BufferedImage imagen) {
        JPanel panel = new JPanel();
        Image imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        JLabel labelImagen = new JLabel(new ImageIcon(imagenEscalada));
        labelImagen.setPreferredSize(new Dimension(ancho, alto));
        labelImagen.setBackground(new Color(0, 0, 0, 0));
        panel.add(labelImagen);
        panel.setOpaque(false);
        return panel;
    }

}
