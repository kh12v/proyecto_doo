package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCompraSuministros extends JPanel {
    private EventHandler handler;

    public PanelCompraSuministros(Color colorDeFondo) {
        setBackground(colorDeFondo);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        add(new JLabel("COMPRAR SUMINISTROS AQUÍ"));
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
    }
}
