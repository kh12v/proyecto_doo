package gui.Paneles.PanelMascotas;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Logica.Enums.Especies;
import gui.Paneles.BotonVentana;
import gui.Paneles.IndicadorDinero;
import gui.Paneles.Ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelMascotas extends JPanel implements Publicador {
    static private final MenuInteractuar menuInteractuar = new MenuInteractuar();
    private final AlmacenMascotas almacen;
    private final IndicadorDinero indicadorDinero;
    private EventHandler handler;
    private final BotonVentana back;

    public PanelMascotas() {
        setLayout(new BorderLayout());

        back = new BotonVentana("<--- Volver", Ventanas.TIENDA);
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(back, BorderLayout.WEST);

        indicadorDinero = new IndicadorDinero();
        indicadorDinero.setBorder(new EmptyBorder(0, 0, 0, 100));
        panelNorte.add(indicadorDinero, BorderLayout.CENTER);

        almacen = new AlmacenMascotas();
        JScrollPane pane = new JScrollPane(almacen);

        add(panelNorte, BorderLayout.NORTH);
        add(pane);
    }

    public static void mostrarMenuInteractuar(int id, String nombre, Especies especie) {
        menuInteractuar.mostrar(id, nombre, especie);
    }

    public static void ocultarMenuInteractuar() {
        JaulaPanel.menuAbierto = false;
        menuInteractuar.ocultar();
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        almacen.enviarHandler(handler);
        indicadorDinero.enviarHandler(handler);
        back.enviarHandler(handler);
        menuInteractuar.enviarHandler(handler);
    }
}
