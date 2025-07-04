package gui.Paneles.PanelMascotas;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Logica.Especies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuInteractuar extends JFrame implements Publicador {
    private EventHandler handler;

    static private final int ANCHO = 600;
    static private final int ALTO = 250;

    OpcionInteraccion opcionInteraccion1 = new OpcionInteraccion(I_COMIDA);
    OpcionInteraccion opcionInteraccion2 = new OpcionInteraccion(I_MEDICAMENTO);
    OpcionInteraccion opcionInteraccion3 = new OpcionInteraccion(I_JUGUETE);

    public static int I_COMIDA = 0;
    public static int I_MEDICAMENTO = 1;
    public static int I_JUGUETE = 2;

    private enum Opcion {
        HAMBRE, SALUD, FELICIDAD, HIGIENE;
    }

    public void mostrar(int id, String nombre, Especies especie) {
        setTitle("Interactuar con [" + nombre + "]");

        opcionInteraccion1.cargar(especie, I_COMIDA);
        opcionInteraccion2.cargar(especie, I_MEDICAMENTO);
        opcionInteraccion3.cargar(especie, I_JUGUETE);

        invalidate();
        repaint();

        setVisible(true);
    }

    public MenuInteractuar() {
        setTitle("Interactuar con []");
        setSize(new Dimension(ANCHO, ALTO));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setBackground(Color.GRAY);
        setLayout(new GridLayout(1, 4));
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

        add(box);
    }

    @Override
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;

        opcionInteraccion1.enviarHandler(handler);
        opcionInteraccion2.enviarHandler(handler);
        opcionInteraccion3.enviarHandler(handler);
    }
}
