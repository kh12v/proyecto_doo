package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import gui.Paneles.BotonVentana;
import gui.Paneles.IndicadorDinero;
import gui.Paneles.Ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel intermedio utilizado para mostrar el botón para volver y el indicador de dinero
 * @see BotonVentana
 * @see IndicadorDinero
 * @see PanelDeCompras
 */
public class PanelCompras extends JPanel implements Publicador {
    private final static Color COLOR_DE_FONDO = Color.GRAY;
    private final PanelDeCompras panelDeCompras;
    private final IndicadorDinero indicadorDinero;
    private final BotonVentana back;
    private EventHandler handler;


    public PanelCompras() {
        setLayout(new BorderLayout());
        back = new BotonVentana("<--- Volver", Ventanas.TIENDA);
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(back, BorderLayout.WEST);

        indicadorDinero = new IndicadorDinero();
        indicadorDinero.setBorder(new EmptyBorder(0, 0, 0, 100));
        panelNorte.add(indicadorDinero, BorderLayout.CENTER);

        add(panelNorte, BorderLayout.NORTH);
        panelDeCompras = new PanelDeCompras(COLOR_DE_FONDO);
        add(panelDeCompras);
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        panelDeCompras.enviarHandler(handler);
        indicadorDinero.enviarHandler(handler);
        back.enviarHandler(handler);
    }
}
