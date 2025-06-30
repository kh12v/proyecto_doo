package gui.Paneles.PanelEmpleados;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Logica.Tienda;
import gui.Paneles.BotonVentana;
import gui.Paneles.IndicadorDinero;
import gui.Paneles.Ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelEmpleados extends JPanel implements Publicador {
    private EventHandler handler;
    private final PlanillaEmpleados planillaEmpleados;
    private final IndicadorDinero indicadorDinero;
    private final BotonVentana back;

    public PanelEmpleados(Tienda tienda) {
        setLayout(new BorderLayout());
        back = new BotonVentana("<--- Volver", Ventanas.TIENDA);
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(back, BorderLayout.WEST);

        indicadorDinero = new IndicadorDinero(tienda.getDinero());
        indicadorDinero.setBorder(new EmptyBorder(0, 0, 0, 100));
        panelNorte.add(indicadorDinero, BorderLayout.CENTER);

        add(panelNorte, BorderLayout.NORTH);
        planillaEmpleados = new PlanillaEmpleados();
        add(planillaEmpleados);
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        planillaEmpleados.enviarHandler(handler);
        indicadorDinero.enviarHandler(handler);
        back.enviarHandler(handler);
    }
}
