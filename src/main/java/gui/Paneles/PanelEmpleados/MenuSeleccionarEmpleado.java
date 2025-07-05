package gui.Paneles.PanelEmpleados;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.M_AgregarEmpleado;
import Logica.Enums.Cargo;
import gui.Paneles.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MenuSeleccionarEmpleado extends JFrame implements Publicador {
    static private final int ANCHO = 600;
    static private final int ALTO = 250;
    private EventHandler handler;
    private final OpcionEmpleado opcionEmpleado1;
    private final OpcionEmpleado opcionEmpleado2;
    private final OpcionEmpleado opcionEmpleado3;

    public MenuSeleccionarEmpleado() {
        setTitle("Contratar empleado");
        setSize(new Dimension(ANCHO, ALTO));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBackground(Color.GRAY);
        setLayout(new GridLayout(1, 3));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();

                AgregarEmpleado.menuAbierto = false;
            }
        });

        Box box1 = Box.createVerticalBox();
        Box box2 = Box.createVerticalBox();
        Box box3 = Box.createVerticalBox();

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        panel1.setMaximumSize(new Dimension(300, 75));
        JLabel label1 = new JLabel("Cuidador $" + Cargo.Cuidador.getSalario(), SwingConstants.CENTER);
        label1.setForeground(Color.WHITE);
        panel1.add(label1);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.GRAY);
        panel2.setMaximumSize(new Dimension(300, 35));
        JLabel label2 = new JLabel("Recepcionista: $" + Cargo.Recepcionista.getSalario(), SwingConstants.CENTER);
        label2.setForeground(Color.WHITE);
        panel2.add(label2);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.GRAY);
        panel3.setMaximumSize(new Dimension(300, 35));
        JLabel label3 = new JLabel("Gerente: $" + Cargo.Gerente.getSalario(), SwingConstants.CENTER);
        label3.setForeground(Color.WHITE);
        panel3.add(label3);

        opcionEmpleado1 = new OpcionEmpleado(Cargo.Cuidador);
        opcionEmpleado2 = new OpcionEmpleado(Cargo.Recepcionista);
        opcionEmpleado3 = new OpcionEmpleado(Cargo.Gerente);

        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.GRAY);
        panel4.setMaximumSize(new Dimension(300, 75));
        JLabel label4 = new JLabel("Cuida a los animales", SwingConstants.CENTER);
        label4.setForeground(Color.WHITE);
        panel4.add(label4);

        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.GRAY);
        panel5.setMaximumSize(new Dimension(300, 75));
        JLabel label5 = new JLabel("Atiende a los clientes", SwingConstants.CENTER);
        label5.setForeground(Color.WHITE);
        panel5.add(label5);

        JPanel panel6 = new JPanel();
        panel6.setBackground(Color.GRAY);
        panel6.setMaximumSize(new Dimension(300, 75));
        JLabel label6 = new JLabel("Aumenta las ganancias", SwingConstants.CENTER);
        label6.setForeground(Color.WHITE);
        panel6.add(label6);

        box1.add(panel1);
        box1.add(opcionEmpleado1);
        box1.add(panel4);

        box2.add(panel2);
        box2.add(opcionEmpleado2);
        box2.add(panel5);

        box3.add(panel3);
        box3.add(opcionEmpleado3);
        box3.add(panel6);

        add(box1);
        add(box2);
        add(box3);
    }

    @Override
    public void enviarHandler(EventHandler handler) {
        opcionEmpleado1.enviarHandler(handler);
        opcionEmpleado2.enviarHandler(handler);
        opcionEmpleado3.enviarHandler(handler);

        this.handler = handler;
    }

    public void mostrar() {
        setVisible(true);
    }

    private class OpcionEmpleado extends JPanel implements Publicador {
        private final static Color COLOR_DE_FONDO = Color.GRAY;
        private final static int ANCHO = 150;
        private final static int ALTO = 150;
        private EventHandler handler;
        private final Cargo cargo;

        public OpcionEmpleado(Cargo cargo) {
            this.cargo = cargo;
            setBackground(COLOR_DE_FONDO);
            setLayout(new OverlayLayout(this));
            setVisible(true);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            addMouseListener(new MyMouseListener());

            try {
                add(ImageLoader.getInstancia().cargarImagenEscalada(ANCHO, ALTO, "/empleado.png"));
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

                handler.enviar(new M_AgregarEmpleado(cargo));

                AgregarEmpleado.menuAbierto = false;
                dispose();
            }
        }
    }
}
