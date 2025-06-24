package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import gui.Paneles.BordeRedondo;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class PanelCompraMascotas extends JPanel implements Publicador {
    private EventHandler handler;
    ArrayList<OpcionDeCompra> opciones = new ArrayList<>();

    private static final Color COLOR_DE_FONDO = new Color(200, 200, 200);

    public PanelCompraMascotas() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(COLOR_DE_FONDO);
        setLayout(new FlowLayout());

        Box box = Box.createVerticalBox();

        box.add(crearOpcion(COLOR_DE_FONDO, "recursos/animales/gato.png", "Gato", 1000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(COLOR_DE_FONDO, "recursos/animales/perro.png", "Perro", 2000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(COLOR_DE_FONDO, "recursos/animales/loro.png", "Loro", 3000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(COLOR_DE_FONDO, "recursos/animales/hamster.png", "hamster", 4000));

        add(box);
    }

    public OpcionDeCompra crearOpcion(Color colorDeFondo, String rutaImagen, String nombre, int precio) {
        OpcionDeCompra op = new OpcionDeCompra(colorDeFondo, rutaImagen, nombre, precio);
        opciones.add(op);
        return op;
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
        for (OpcionDeCompra op : opciones) {
            op.enviarHandler(handler);
        }
    }
}
