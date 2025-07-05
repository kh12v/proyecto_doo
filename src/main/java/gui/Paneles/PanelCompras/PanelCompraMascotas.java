package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Logica.Enums.Producto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PanelCompraMascotas extends JPanel implements Publicador {
    private static final Color COLOR_DE_FONDO = new Color(200, 200, 200);
    ArrayList<OpcionDeCompra> opciones = new ArrayList<>();
    private EventHandler handler;

    public PanelCompraMascotas() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(COLOR_DE_FONDO);
        setLayout(new FlowLayout());

        Box box = Box.createVerticalBox();

        box.add(crearOpcion(Producto.Perro, "/animales/perro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.Gato, "/animales/gato.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.Loro, "/animales/loro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.Hamster, "/animales/hamster.png"));

        add(box);
    }

    public OpcionDeCompra crearOpcion(Producto producto, String rutaImagen) {
        OpcionDeCompra op = new OpcionDeCompra(COLOR_DE_FONDO, producto, rutaImagen);
        opciones.add(op);
        return op;
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        for (OpcionDeCompra op : opciones) {
            op.enviarHandler(handler);
        }
    }
}
