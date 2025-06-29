package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;

import javax.swing.*;
import java.awt.*;

public class PanelDeCompras extends JPanel implements Publicador {
    private EventHandler handler;
    private final PanelCompraMascotas panelCompraMascotas;
    private final PanelCompraSuministros panelCompraSuministros;

    public PanelDeCompras(Color colorDeFondo) {
        setBackground(colorDeFondo);
        setLayout(new GridLayout(1, 2));

        panelCompraMascotas = new PanelCompraMascotas();
        JScrollPane pane1 = new JScrollPane(panelCompraMascotas);
        pane1.getVerticalScrollBar().setUnitIncrement(16);

        panelCompraSuministros = new PanelCompraSuministros();
        JScrollPane pane2 = new JScrollPane(panelCompraSuministros);
        pane2.getVerticalScrollBar().setUnitIncrement(16);

        add(pane1);
        add(pane2);
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
        panelCompraMascotas.enviarHandler(handler);
        panelCompraSuministros.enviarHandler(handler);
    }
}
