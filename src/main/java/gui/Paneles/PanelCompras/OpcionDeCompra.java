package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.V_CambiarVentanaEvento;
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

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO: Crear evento para comprar producto y mostrar si la compra fue exitosa o no en pantalla
            // handler.enviar(new V_CambiarVentanaEvento(Ventanas.EMPLEADOS));
        }
    }

    public OpcionDeCompra(Color colorDelFondo, String rutaImagen, String nombre, int precio) {
        setBackground(colorDelFondo);
        setBorder(new BordeRedondo(COLOR_DE_FONDO, 25));

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MyMouseListener());

        Box box = Box.createHorizontalBox();

        box.add(new ImagenProducto(rutaImagen, COLOR_DE_FONDO));
        box.add(new JLabel(nombre + " $" + precio));

        add(box);
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
    }
}
