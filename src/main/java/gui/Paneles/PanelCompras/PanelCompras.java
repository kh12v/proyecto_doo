package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.V_CambiarVentanaEvento;
import gui.Paneles.Ventanas;

import javax.swing.*;
import java.awt.*;

public class PanelCompras extends JPanel implements Publicador {
    private EventHandler handler;
    private final PanelDeCompras panelDeCompras;

    private final static Color COLOR_DE_FONDO = Color.GRAY;

    private class BackButton extends JButton {
        public BackButton() {
            setText("<--  Volver");
            setPreferredSize(new Dimension(125, 55));
            setMaximumSize(new Dimension(125, 55));
            setMinimumSize(new Dimension(125, 55));
            addActionListener(e -> handler.enviar(new V_CambiarVentanaEvento(Ventanas.TIENDA)));
        }
    }

    public PanelCompras() {
        setLayout(new BorderLayout());

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(new PanelCompras.BackButton(), BorderLayout.WEST);
        add(panelNorte, BorderLayout.NORTH);
        panelDeCompras = new PanelDeCompras(COLOR_DE_FONDO);
        add(panelDeCompras);
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
        panelDeCompras.enviarHandler(handler);
    }
}
