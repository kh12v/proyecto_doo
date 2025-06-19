package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;

import javax.swing.*;
import java.awt.*;

public class PanelDeCompras extends JPanel {
    private EventHandler handler;
    private PanelCompraMascotas panelCompraMascotas;
    private PanelCompraSuministros panelCompraSuministros;

    public PanelDeCompras(Color colorDeFondo) {
        setBackground(colorDeFondo);
        setLayout(new GridLayout(1, 2));

        panelCompraMascotas = new PanelCompraMascotas(colorDeFondo);
        panelCompraSuministros = new PanelCompraSuministros(colorDeFondo);

        add(panelCompraMascotas);
        add(panelCompraSuministros);
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
        panelCompraMascotas.enviarHandler(handler);
        panelCompraSuministros.enviarHandler(handler);
    }
}
