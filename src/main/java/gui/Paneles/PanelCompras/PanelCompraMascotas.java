package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import gui.Paneles.BordeRedondo;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelCompraMascotas extends JPanel {
    private EventHandler handler;

    private static final Color COLOR_DE_FONDO = new Color(200, 200, 200);

    public PanelCompraMascotas() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(COLOR_DE_FONDO);
        setLayout(new FlowLayout());

        Box box = Box.createVerticalBox();

        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/animales/gato.png", "Gato", 1000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/animales/perro.png", "Perro", 2000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/animales/loro.png", "Loro", 3000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/animales/hamster.png", "hamster", 4000));

        add(box);
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
    }
}
