package gui.Paneles.PanelMascotas;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Logica.Enums.Especie;
import gui.Paneles.BotonVentana;
import gui.Paneles.IndicadorDinero;
import gui.Paneles.Ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel intermediario que muestra el boton de regreso, indicador de dinero y las mascotas
 * @see AlmacenMascotas
 * @see IndicadorDinero
 * @see BotonVentana
 */
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

    /**
     * Muestra el menu para interactuar
     * @param id: Id de la mascota a interactuar
     * @param nombre: Nombre de la mascota
     * @param especie: Especie de la mascota
     */
    public static void mostrarMenuInteractuar(int id, String nombre, Especie especie) {
        menuInteractuar.mostrar(id, nombre, especie);
    }

    /**
     * Oculta el menu de interacción pero no es eliminado
     */
    public static void ocultarMenuInteractuar() {
        JaulaPanel.menuAbierto = false;
        menuInteractuar.ocultar();
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        almacen.enviarHandler(handler);
        indicadorDinero.enviarHandler(handler);
        back.enviarHandler(handler);
        menuInteractuar.enviarHandler(handler);
    }
}
