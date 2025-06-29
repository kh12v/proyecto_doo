package gui.Paneles.PanelMascotas;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.V_ActualizarMascotasEvento;
import Controladores.Eventos.Tipos.V_CambiarVentanaEvento;
import Logica.Tienda;
import gui.Paneles.BotonVentana;
import gui.Paneles.IndicadorDinero;
import gui.Paneles.PanelEmpleados.PlanillaEmpleados;
import gui.Paneles.Ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PanelMascotas extends JPanel implements Publicador {
    private EventHandler handler;
    private final AlmacenMascotas almacen;
    private final IndicadorDinero indicadorDinero;
    private BotonVentana back;

    public PanelMascotas(Tienda tienda) {
        setLayout(new BorderLayout());

        back = new BotonVentana("<--- Volver", Ventanas.TIENDA);
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(back, BorderLayout.WEST);

        indicadorDinero = new IndicadorDinero(tienda.getDinero());
        indicadorDinero.setBorder(new EmptyBorder(0, 0, 0, 100));
        panelNorte.add(indicadorDinero, BorderLayout.CENTER);

        almacen = new AlmacenMascotas();
        JScrollPane pane = new JScrollPane(almacen);

        add(panelNorte, BorderLayout.NORTH);
        add(pane);
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        almacen.enviarHandler(handler);
        indicadorDinero.enviarHandler(handler);
        back.enviarHandler(handler);
    }
}
