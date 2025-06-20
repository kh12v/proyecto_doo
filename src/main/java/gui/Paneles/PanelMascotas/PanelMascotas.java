package gui.Paneles.PanelMascotas;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.V_CambiarVentanaEvento;
import gui.Paneles.Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PanelMascotas extends JPanel implements Publicador {
    private EventHandler handler;
    private final AlmacenMascotas almacen;

    private class BackButton extends JButton {
        public BackButton() {
            setText("<--  Volver");
            setPreferredSize(new Dimension(125, 55));
            setMaximumSize(new Dimension(125, 55));
            setMinimumSize(new Dimension(125, 55));
            setMnemonic(KeyEvent.VK_ESCAPE);
            addActionListener(e -> handler.enviar(new V_CambiarVentanaEvento(Ventanas.TIENDA)));
        }
    }

    public PanelMascotas() {
        setLayout(new BorderLayout());

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(new BackButton(), BorderLayout.WEST);
        add(panelNorte, BorderLayout.NORTH);
        almacen = new AlmacenMascotas();
        JScrollPane pane = new JScrollPane(almacen);
        add(pane);
    }
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        almacen.enviarHandler(handler);
    }
}
