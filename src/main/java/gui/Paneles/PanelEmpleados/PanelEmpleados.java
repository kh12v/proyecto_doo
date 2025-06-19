package gui.Paneles.PanelEmpleados;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.V_CambiarVentanaEvento;
import gui.Paneles.Ventanas;

import javax.swing.*;
import java.awt.*;

public class PanelEmpleados extends JPanel {
    private EventHandler handler;
    private PlanillaEmpleados planillaEmpleados;

    private class BackButton extends JButton {
        public BackButton() {
            setText("<--  Volver");
            setPreferredSize(new Dimension(125, 55));
            setMaximumSize(new Dimension(125, 55));
            setMinimumSize(new Dimension(125, 55));
            addActionListener(e -> handler.enviar(new V_CambiarVentanaEvento(Ventanas.TIENDA)));
        }
    }

    public PanelEmpleados() {
        setLayout(new BorderLayout());

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(new PanelEmpleados.BackButton(), BorderLayout.WEST);
        add(panelNorte, BorderLayout.NORTH);
        planillaEmpleados = new PlanillaEmpleados();
        add(planillaEmpleados);
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        planillaEmpleados.enviarHandler(handler);
    }
}
