package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.V_CambiarVentanaEvento;
import gui.Paneles.BotonVentana;
import gui.Paneles.Ventanas;

import javax.swing.*;
import java.awt.*;

public class PanelCompras extends JPanel implements Publicador {
    private EventHandler handler;
    private final PanelDeCompras panelDeCompras;
    private final BotonVentana back;
    private final static Color COLOR_DE_FONDO = Color.GRAY;


    public PanelCompras() {
        setLayout(new BorderLayout());
        back = new BotonVentana("<--- Volver",Ventanas.TIENDA);
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(back, BorderLayout.WEST);
        add(panelNorte, BorderLayout.NORTH);
        panelDeCompras = new PanelDeCompras(COLOR_DE_FONDO);
        add(panelDeCompras);
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
        panelDeCompras.enviarHandler(handler);
        back.enviarHandler(handler);
    }
}
