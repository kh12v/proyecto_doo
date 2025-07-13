package gui.Paneles.PanelTienda;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.V_ActualizarCalificacion;
import Controladores.Eventos.Tipos.V_ActualizarCliente;
import Controladores.Eventos.Tipos.V_ActualizarVentanaClientes;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * Panel principal de la tienda
 */
public class PanelTienda extends JPanel implements Publicador, Suscriptor {
    private VentanaPrincipal ventanaPrincipal;
    private EventHandler handler;
    private final PanelCliente panelCliente;
    private final PanelMenu panelMenu;

    public PanelTienda(String nombre) {
        setLayout(new GridLayout(2, 1, 0, 0));

        panelCliente = new PanelCliente(nombre);
        panelMenu = new PanelMenu();

        add(panelCliente);
        add(panelMenu);
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        panelCliente.enviarHandler(handler);
        panelMenu.enviarHandler(handler);

        handler.suscribir(this);
    }

    /**
     * Maneja los eventos recibidos
     * @param e: Evento enviado
     */
    @Override
    public void recibir(Evento e) {
        switch (e.getTipo()) {
            case ActualizarVentanaClientes -> actualizarVentanaClientes((V_ActualizarVentanaClientes) e);
        }
    }

    void actualizarVentanaClientes(V_ActualizarVentanaClientes e) {
        panelCliente.actualizar();
    }

    /**
     * Indica el tipo de eventos que maneja esta clase
     * @return
     */
    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }
}
