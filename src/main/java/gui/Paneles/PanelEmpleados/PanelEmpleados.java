package gui.Paneles.PanelEmpleados;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.V_CambiarVentanaEvento;
import gui.Paneles.BotonVentana;
import gui.Paneles.Ventanas;

import javax.swing.*;
import java.awt.*;

public class PanelEmpleados extends JPanel implements Publicador {
    private EventHandler handler;
    private final PlanillaEmpleados planillaEmpleados;
    private final BotonVentana back;

    public PanelEmpleados() {
        setLayout(new BorderLayout());
        back = new BotonVentana("<--- Volver", Ventanas.TIENDA);
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(back, BorderLayout.WEST);
        add(panelNorte, BorderLayout.NORTH);
        planillaEmpleados = new PlanillaEmpleados();
        add(planillaEmpleados);
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        planillaEmpleados.enviarHandler(handler);
        back.enviarHandler(handler);
    }
}
