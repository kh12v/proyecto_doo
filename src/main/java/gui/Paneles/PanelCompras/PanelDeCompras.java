package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;

import javax.swing.*;
import java.awt.*;

public class PanelDeCompras extends JPanel {
    private EventHandler handler;
    private final PanelCompraMascotas panelCompraMascotas;
    private final PanelCompraSuministros panelCompraSuministros;

    public PanelDeCompras(Color colorDeFondo) {
        setBackground(colorDeFondo);
        setLayout(new GridLayout(1, 2));

        panelCompraMascotas = new PanelCompraMascotas();
        JScrollPane pane1 = new JScrollPane(panelCompraMascotas);

        panelCompraSuministros = new PanelCompraSuministros();
        JScrollPane pane2 = new JScrollPane(panelCompraSuministros);

        add(pane1);
        add(pane2);
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
        panelCompraMascotas.enviarHandler(handler);
        panelCompraSuministros.enviarHandler(handler);
    }
}
