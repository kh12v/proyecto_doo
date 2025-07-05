package gui.Paneles.PanelMascotas;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.M_AgregarJaula;
import Logica.Enums.TipoContenedor;
import gui.Paneles.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MenuSeleccionarJaula extends JFrame implements Publicador {
    static private final int ANCHO = 400;
    static private final int ALTO = 250;
    private EventHandler handler;
    private final OpcionJaula opcionJaula1;
    private final OpcionJaula opcionJaula2;

    public MenuSeleccionarJaula() {
        setTitle("Comprar jaula");
        setSize(new Dimension(ANCHO, ALTO));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBackground(Color.GRAY);
        setLayout(new GridLayout(1, 2));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();

                AgregarJaula.menuAbierto = false;
            }
        });

        Box box1 = Box.createVerticalBox();
        Box box2 = Box.createVerticalBox();

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        panel1.setMaximumSize(new Dimension(300, 75));
        JLabel label1 = new JLabel("Comprar jaula grande: $" + TipoContenedor.JaulaGrande.getPrecio(), SwingConstants.CENTER);
        label1.setForeground(Color.WHITE);
        panel1.add(label1);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.GRAY);
        panel2.setMaximumSize(new Dimension(300, 35));
        JLabel label2 = new JLabel("Comprar jaula pequeña: $" + TipoContenedor.JaulaPequena.getPrecio(), SwingConstants.CENTER);
        label2.setForeground(Color.WHITE);
        panel2.add(label2);

        opcionJaula1 = new OpcionJaula(true);
        opcionJaula2 = new OpcionJaula(false);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.GRAY);
        panel3.setMaximumSize(new Dimension(300, 75));
        JLabel label3 = new JLabel("Admite perros o gatos", SwingConstants.CENTER);
        label3.setForeground(Color.WHITE);
        panel3.add(label3);

        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.GRAY);
        panel4.setMaximumSize(new Dimension(300, 75));
        JLabel label4 = new JLabel("Admite loros o hamsters", SwingConstants.CENTER);
        label4.setForeground(Color.WHITE);
        panel4.add(label4);

        box1.add(panel1);
        box1.add(opcionJaula1);
        box1.add(panel3);

        box2.add(panel2);
        box2.add(opcionJaula2);
        box2.add(panel4);

        add(box1);
        add(box2);
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

    private class OpcionJaula extends JPanel implements Publicador {
        private final static Color COLOR_DE_FONDO = Color.GRAY;
        private final static int ANCHO = 150;
        private final static int ALTO = 150;
        private final boolean jaulaGrande;
        private EventHandler handler;

        public OpcionJaula(boolean jaulaGrande) {
            this.jaulaGrande = jaulaGrande;
            setBackground(COLOR_DE_FONDO);
            setLayout(new OverlayLayout(this));
            setVisible(true);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            addMouseListener(new MyMouseListener());

            ImageLoader loader = ImageLoader.getInstancia();
            try {
                JPanel panel = jaulaGrande
                        ? loader.cargarImagenEscalada(ANCHO, ALTO, "/espacioDisponible.png")
                        : loader.cargarImagenEscalada((int) (ANCHO * 0.75), (int) (ALTO * 0.75), "/espacioDisponible.png");
                add(panel);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    }
}