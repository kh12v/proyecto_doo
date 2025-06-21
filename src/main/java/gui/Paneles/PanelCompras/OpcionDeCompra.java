package gui.Paneles.PanelCompras;

import gui.Paneles.BordeRedondo;

import javax.swing.*;
import java.awt.*;

public class OpcionDeCompra extends JPanel {
    private static final Color COLOR_DE_FONDO = new Color(220, 220,220);

    public OpcionDeCompra(Color colorDelFondo, String rutaImagen, String nombre, int precio) {
        setBackground(colorDelFondo);
        setBorder(new BordeRedondo(COLOR_DE_FONDO, 25));

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        Box box = Box.createHorizontalBox();

        box.add(new ImagenProducto(rutaImagen, COLOR_DE_FONDO));
        box.add(new JLabel(nombre + " $" + precio));

        add(box);
    }
}
