package gui.Paneles.PanelMascotas;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.V_CambiarVentanaEvento;
import gui.Paneles.BotonVentana;
import gui.Paneles.PanelEmpleados.PlanillaEmpleados;
import gui.Paneles.Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PanelMascotas extends JPanel implements Publicador {
    private EventHandler handler;
    private final AlmacenMascotas almacen;
    private BotonVentana back;

    public PanelMascotas() {
        setLayout(new BorderLayout());

        back = new BotonVentana("<--- Volver", Ventanas.TIENDA);
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(back, BorderLayout.WEST);

        almacen = new AlmacenMascotas();
        JScrollPane pane = new JScrollPane(almacen);

        add(panelNorte, BorderLayout.NORTH);
        add(pane);
    }
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        almacen.enviarHandler(handler);
        back.enviarHandler(handler);
    }
}
