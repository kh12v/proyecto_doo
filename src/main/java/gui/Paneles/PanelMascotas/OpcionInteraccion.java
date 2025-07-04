package gui.Paneles.PanelMascotas;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_ConsumirProducto;
import Controladores.Eventos.Tipos.M_SolicitarProductos;
import Controladores.Eventos.Tipos.V_MostrarProductos;
import Logica.Enums.Especies;
import Logica.Enums.TipoProducto;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OpcionInteraccion extends JPanel implements Publicador {
    EventHandler handler;
    String[] rutasDeImagenes;
    JPanel panelImagen;
    JLabel labelImagen;
    CantidadDeProducto cantidadDeProducto;
    MyMouseListener myMouseListener;

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

    private class MyMouseListener extends MouseAdapter {
        private int id = -1;
        private Especies especie;
        private final int indice;

        public MyMouseListener(int indice) {
            this.indice = indice;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setEspecie(Especies especie) {
            this.especie = especie;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (id == -1) return;
            TipoProducto tipoProducto;
            if (indice == MenuInteractuar.I_COMIDA) tipoProducto = TipoProducto.Comida;
            else if (indice == MenuInteractuar.I_MEDICAMENTO) tipoProducto = TipoProducto.Medicamento;
            else tipoProducto = TipoProducto.Juguete;
            PanelMascotas.ocultarMenuInteractuar();
            handler.enviar(new M_ConsumirProducto(id, especie, tipoProducto));
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

    public OpcionInteraccion(int indice) {
        Box box = Box.createVerticalBox();

        panelImagen = new JPanel();

        labelImagen = new JLabel();

        myMouseListener = new MyMouseListener(indice);
        labelImagen.addMouseListener(myMouseListener);

        labelImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelImagen.setBackground(new Color(0,0,0,0));
        labelImagen.setBorder(new EmptyBorder(0, 0, 0, 0));

        panelImagen.add(labelImagen, BorderLayout.CENTER);
        panelImagen.setBackground(new Color(0,0,0,0));

        cantidadDeProducto = new CantidadDeProducto(indice, "");

        String texto = (indice == MenuInteractuar.I_COMIDA) ? "Comida" : (indice == MenuInteractuar.I_MEDICAMENTO) ? "Medicamento" : "Juguete";
        JPanel panelTexto = new JPanel();
        panelTexto.add(new JLabel(texto, SwingConstants.CENTER));
        box.add(panelTexto);
        box.add(labelImagen);
        box.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel panelTexto2 = new JPanel();
        panelTexto2.add(cantidadDeProducto);
        box.add(panelTexto2);

        add(box);
    }

    public void cargar(int id, Especies especie, int indiceImagen) {
        cargarRutas(especie);

        myMouseListener.setId(id);
        myMouseListener.setEspecie(especie);
        cargarImagen(140, 140, rutasDeImagenes[indiceImagen]);
        handler.enviar(new M_SolicitarProductos(id, especie));
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