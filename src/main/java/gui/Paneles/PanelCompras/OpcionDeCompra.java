package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.V_CambiarVentanaEvento;
import Logica.Producto;
import gui.Paneles.BordeRedondo;
import gui.Paneles.Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OpcionDeCompra extends JPanel implements Publicador {
    private EventHandler handler;
    private static final Color COLOR_DE_FONDO = new Color(220, 220,220);
    private Producto producto;

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
//            handler.enviar(new V_ComprarProducto(producto));
        }
    }

    public OpcionDeCompra(Color colorDelFondo, Producto producto, String rutaImagen) {
        this.producto = producto;

        setBackground(colorDelFondo);
        setBorder(new BordeRedondo(COLOR_DE_FONDO, 25));

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MyMouseListener());

        Box box = Box.createHorizontalBox();

        box.add(new ImagenProducto(rutaImagen, COLOR_DE_FONDO));
        box.add(new JLabel(producto.getNombre() + " $" + producto.getPrecio()));

        add(box);
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
    }
}
