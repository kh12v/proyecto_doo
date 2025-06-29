package gui.Paneles.PanelMascotas;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_AgregarJaula;
import Logica.TipoContenedor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuSeleccionarJaula extends JFrame implements Publicador {
    private EventHandler handler;

    static private final int ANCHO = 500;
    static private final int ALTO = 500;

    private OpcionJaula opcionJaula1;
    private OpcionJaula opcionJaula2;

    private class OpcionJaula extends JPanel implements Publicador {
        private final static Color COLOR_DE_FONDO = Color.GRAY;
        private final static int ANCHO = 150;
        private final static int ALTO = 150;
        private EventHandler handler;
        private final boolean jaulaGrande;

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

        @Override
        public void enviarHandler(EventHandler handler) {
            this.handler = handler;
        }

        private class MyMouseListener extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (jaulaGrande) handler.enviar(new M_AgregarJaula(TipoContenedor.JaulaGrande));
                else handler.enviar(new M_AgregarJaula(TipoContenedor.JaulaPequena));

                AgregarJaula.menuAbierto = false;
                dispose();
            }
        }

        public OpcionJaula(boolean jaulaGrande) {
            this.jaulaGrande = jaulaGrande;
            setBackground(COLOR_DE_FONDO);
            setLayout(new OverlayLayout(this));
            setVisible(true);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            addMouseListener(new MyMouseListener());

            if (jaulaGrande) add(cargarImagen(ANCHO, ALTO, "recursos/espacioDisponible.png"));
            else add(cargarImagen((int)(ANCHO*0.75), (int)(ALTO*0.75), "recursos/espacioDisponible.png"));
        }
    }

    public MenuSeleccionarJaula() {
        setTitle("Comprar jaula");
        setSize(new Dimension(ANCHO, ALTO));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBackground(Color.GRAY);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();

                AgregarJaula.menuAbierto = false;
            }
        });

        Box box = Box.createHorizontalBox();

        Box box1 = Box.createVerticalBox();
        box1.setBackground(Color.GRAY);
        box1.setAlignmentX(CENTER_ALIGNMENT);
        Box box2 = Box.createVerticalBox();
        box2.setBackground(Color.GRAY);
        box2.setAlignmentX(CENTER_ALIGNMENT);

        opcionJaula1 = new OpcionJaula(true);
        opcionJaula2 = new OpcionJaula(false);

        JLabel label1 = new JLabel("Comprar jaula grande: $" + TipoContenedor.JaulaGrande.getPrecio(), SwingConstants.CENTER);
        label1.setBackground(Color.GRAY);
        JLabel label2 = new JLabel("Comprar jaula pequeña: $" + TipoContenedor.JaulaPequena.getPrecio(), SwingConstants.CENTER);
        label2.setBackground(Color.GRAY);

        box1.add(label1);
        box2.add(label2);

        box1.add(Box.createRigidArea(new Dimension(0, 15)));
        box2.add(Box.createRigidArea(new Dimension(0, 15)));

        box1.add(opcionJaula1);
        box2.add(opcionJaula2);

        box.add(box1);
        box.add(Box.createRigidArea(new Dimension(0, 25)));
        box.add(box2);

        add(box);

        setLocationRelativeTo(null);
    }

    @Override
    public void enviarHandler(EventHandler handler) {
        opcionJaula1.enviarHandler(handler);
        opcionJaula2.enviarHandler(handler);

        this.handler = handler;
    }

    public void mostrar() {
        setVisible(true);
    }
}