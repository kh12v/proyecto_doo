package gui.Paneles.PanelMascotas;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.M_EntregarMascota;
import Logica.Enums.Especies;
import gui.Paneles.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class MenuInteractuar extends JFrame implements Publicador {
    static private final int ANCHO = 1000;
    static private final int ALTO = 250;
    public static int I_COMIDA = 0;
    public static int I_MEDICAMENTO = 1;
    public static int I_JUGUETE = 2;
    public static int I_HIGIENE = 3;
    OpcionInteraccion opcionInteraccion1 = new OpcionInteraccion(I_COMIDA);
    OpcionInteraccion opcionInteraccion2 = new OpcionInteraccion(I_MEDICAMENTO);
    OpcionInteraccion opcionInteraccion3 = new OpcionInteraccion(I_JUGUETE);
    OpcionInteraccion opcionInteraccion4 = new OpcionInteraccion(I_HIGIENE);
    OpcionEntrega     opcionEntrega = new OpcionEntrega();
    private EventHandler handler;

    public MenuInteractuar() {
        setTitle("Interactuar con []");
        setSize(new Dimension(ANCHO, ALTO));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setBackground(Color.GRAY);
        setLayout(new GridLayout(1, 0));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();

                JaulaPanel.menuAbierto = false;
            }
        });

        Box box = Box.createHorizontalBox();

        box.add(opcionInteraccion1);
        box.add(opcionInteraccion2);
        box.add(opcionInteraccion3);
        box.add(opcionInteraccion4);
        box.add(opcionEntrega);
        add(box);
    }

    public void mostrar(int id, String nombre, Especies especie) {
        setTitle("Interactuar con [" + nombre + "]");

        opcionInteraccion1.cargar(id, especie, I_COMIDA);
        opcionInteraccion2.cargar(id, especie, I_MEDICAMENTO);
        opcionInteraccion3.cargar(id, especie, I_JUGUETE);
        opcionInteraccion4.cargar(id, especie, I_HIGIENE);
        opcionEntrega.cargar(id, especie);

        invalidate();
        repaint();

        setVisible(true);
    }

    public void ocultar() {
        setVisible(false);
    }

    @Override
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;

        opcionInteraccion1.enviarHandler(handler);
        opcionInteraccion2.enviarHandler(handler);
        opcionInteraccion3.enviarHandler(handler);
        opcionInteraccion4.enviarHandler(handler);
        opcionEntrega.enviarHandler(handler);
    }
}

class OpcionEntrega extends JPanel implements Publicador{
    private EventHandler handler;
    private final AtomicReference<Integer> id;
    private final JButton botonImagen;
    public OpcionEntrega() {
        Box box = Box.createVerticalBox();

        JPanel panelImagen = new JPanel();
        id = new AtomicReference<>();
        botonImagen = new JButton();

        botonImagen.addActionListener(e -> {
            handler.enviar(new M_EntregarMascota(id.get()));
            id.set(null);
            System.out.println("AAAA");
        });
        botonImagen.setOpaque(false);
        botonImagen.setBorderPainted(false);
        botonImagen.setContentAreaFilled(false);
        botonImagen.setFocusPainted(false);
        botonImagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonImagen.setMnemonic(KeyEvent.VK_ENTER);

        panelImagen.add(botonImagen, BorderLayout.CENTER);
        panelImagen.setBackground(new Color(0, 0, 0, 0));


        JPanel panelTexto = new JPanel();
        panelTexto.add(new JLabel("Entregar", SwingConstants.CENTER));
        box.add(panelTexto);
        box.add(botonImagen);
        box.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel panelTexto2 = new JPanel();
        panelTexto2.add(new JLabel("", SwingConstants.CENTER));
        box.add(panelTexto2);

        add(box);
    }

    public void cargar(int id, Especies especie){
        String ruta = "/clientes/cliente_" + especie + ".png";
        this.id.set(id);
        cargarImagen(140,140,ruta);
    }

    private void cargarImagen(int ancho, int alto, String ruta) {
        try {
            Image imagenEscalada = ImageLoader.getInstancia().entregarImagen(ruta).getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

            botonImagen.setIcon(new ImageIcon(imagenEscalada));
            botonImagen.setPreferredSize(new Dimension(ancho, alto));
            botonImagen.setMinimumSize(new Dimension(ancho, alto));
            botonImagen.setMaximumSize(new Dimension(ancho, alto));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
    }
}
