package gui.Paneles.PanelTienda;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCliente extends JPanel implements Publicador {
    private static final Color COLOR_DE_FONDO = new Color(87, 177, 230);


    public PanelCliente() {
        setLayout(new BorderLayout());
        setBackground(COLOR_DE_FONDO);
        setBorder(new EmptyBorder(0, 0, 0, 0));

        IconoCliente iconoCliente = new IconoCliente("recursos/cliente.png", 300, 300, COLOR_DE_FONDO);

        add(iconoCliente, BorderLayout.SOUTH);
    }

    public void enviarHandler(EventHandler handler) {}
}
