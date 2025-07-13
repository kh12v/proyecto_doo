package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.M_ComprarProducto;
import Logica.Enums.Producto;
import gui.Paneles.BordeRedondo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * En este panel se muestran los objetos que se pueden comprar y emite el evento
 * de compra si el usuario lo pide
 * @see Publicador
 * @see M_ComprarProducto
 */
public class OpcionDeCompra extends JPanel implements Publicador {
    private static final Color COLOR_DE_FONDO = new Color(220, 220, 220);
    private EventHandler handler;
    private final Producto producto;

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

    @Override
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
    }

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(producto.esMascota()){
                String nombre = JOptionPane.showInputDialog(
                        OpcionDeCompra.this,
                        "Ingrese el nombre de su mascota",
                        "Nombre de mascota",
                        JOptionPane.PLAIN_MESSAGE
                );
                handler.enviar(new M_ComprarProducto(producto, nombre));
            } else {
                handler.enviar(new M_ComprarProducto(producto));
            }
        }
    }
}
