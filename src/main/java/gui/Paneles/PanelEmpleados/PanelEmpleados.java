package gui.Paneles.PanelEmpleados;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import gui.Paneles.BotonVentana;
import gui.Paneles.IndicadorDinero;
import gui.Paneles.Ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel intermedio que muestra el botón para devolverse, indicador de dinero y a los empleados
 * @see IndicadorDinero
 * @see BotonVentana
 * @see PlanillaEmpleados
 */
public class PanelEmpleados extends JPanel implements Publicador {
    private final PlanillaEmpleados planillaEmpleados;
    private final IndicadorDinero indicadorDinero;
    private final BotonVentana back;
    private EventHandler handler;

    public PanelEmpleados() {
        setLayout(new BorderLayout());
        back = new BotonVentana("<--- Volver", Ventanas.TIENDA);
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(back, BorderLayout.WEST);

        indicadorDinero = new IndicadorDinero();
        indicadorDinero.setBorder(new EmptyBorder(0, 0, 0, 100));
        panelNorte.add(indicadorDinero, BorderLayout.CENTER);

        add(panelNorte, BorderLayout.NORTH);
        planillaEmpleados = new PlanillaEmpleados();
        add(planillaEmpleados);
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        planillaEmpleados.enviarHandler(handler);
        indicadorDinero.enviarHandler(handler);
        back.enviarHandler(handler);
    }
}
