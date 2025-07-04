package gui.Paneles.PanelMascotas;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_SolicitarProductos;
import Controladores.Eventos.Tipos.V_MostrarProductos;
import Logica.Especies;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OpcionInteraccion extends JPanel implements Publicador {
    EventHandler handler;
    String[] rutasDeImagenes;
    JPanel panelImagen;
    JLabel labelImagen;
    CantidadDeProducto cantidadDeProducto;

    private class CantidadDeProducto extends JLabel implements Publicador, Suscriptor {
        EventHandler handler;
        int indice;

        public CantidadDeProducto(int index, String texto) {
            this.indice = index;
            setText(texto);
        }

        private void mostrarProductos(V_MostrarProductos evento) {
            setText("Stock: " + evento.productos[indice]);
        }

        @Override
        public void enviarHandler(EventHandler handler) {
            this.handler = handler;
            handler.suscribir(this);
        }

        @Override
        public void recibir(Evento evento) {
            switch (evento.getTipo()){
                case MostrarProductos -> mostrarProductos((V_MostrarProductos) evento);
            }
        }

        @Override
        public DestinoEvento[] getEventosEscuchados() {
            return new DestinoEvento[]{DestinoEvento.Vista};
        }
    }

    private void cargarRutas(Especies especie) {
        String rc = "recursos/comida/";
        String rm = "recursos/medicamentos/";
        String rj = "recursos/juguetes/";

        switch (especie) {
            case Especies.Perro -> rutasDeImagenes = new String[]{
                    rc + "comida_perro.png",
                    rm + "medicamento_perro.png",
                    rj + "juguete_perro.png"};
            case Especies.Gato -> rutasDeImagenes = new String[]{
                    rc + "comida_gato.png",
                    rm + "medicamento_gato.png",
                    rj + "juguete_gato.png"};
            case Especies.Loro -> rutasDeImagenes = new String[]{
                    rc + "comida_loro.png",
                    rm + "medicamento_loro.png",
                    rj + "juguete_loro.png"};
            case Especies.Hamster -> rutasDeImagenes = new String[]{
                    rc + "comida_hamster.png",
                    rm + "medicamento_hamster.png",
                    rj + "juguete_hamster.png"};
        }
    }

    public OpcionInteraccion(int index) {
        Box box = Box.createVerticalBox();

        panelImagen = new JPanel();

        labelImagen = new JLabel();

        labelImagen.setBackground(new Color(0,0,0,0));
        labelImagen.setBorder(new EmptyBorder(0, 0, 0, 0));

        panelImagen.add(labelImagen, BorderLayout.CENTER);
        panelImagen.setBackground(new Color(0,0,0,0));

        cantidadDeProducto = new CantidadDeProducto(index, "");

        box.add(labelImagen);
        box.add(Box.createRigidArea(new Dimension(0, 10)));
        box.add(cantidadDeProducto);

        add(box);
    }

    public void cargar(Especies especie, int indiceImagen) {
        cargarRutas(especie);

        cargarImagen(140, 140, rutasDeImagenes[indiceImagen]);
        handler.enviar(new M_SolicitarProductos(especie));
    }

    private void cargarImagen(int ancho, int alto, String ruta) {
        try {
            BufferedImage imagenOriginal = ImageIO.read(new File(ruta));

            if (imagenOriginal != null) {
                Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

                labelImagen.setIcon(new ImageIcon(imagenEscalada));
                labelImagen.setPreferredSize(new Dimension(ancho, alto));
                labelImagen.setMinimumSize(new Dimension(ancho, alto));
                labelImagen.setMaximumSize(new Dimension(ancho, alto));
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen: " + ruta, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        cantidadDeProducto.enviarHandler(handler);
    }
}