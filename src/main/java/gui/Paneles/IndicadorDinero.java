package gui.Paneles;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirDinero;
import Controladores.Eventos.Tipos.V_MostrarDinero;

import javax.swing.*;

/**
 * Muestra la cantidad de dinero que posee la tienda
 */
public class IndicadorDinero extends JLabel implements Suscriptor, Publicador {
    EventHandler handler;

    public IndicadorDinero() {
        setText("Dinero: $");

        setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void mostrarDinero(V_MostrarDinero evento) {
        setText("Dinero: $" + evento.dinero);
    }

    /**
     * Maneja los eventos recibidos
     * @param e: Evento enviado
     */
    @Override
    public void recibir(Evento e) {
        switch (e.getTipo()) {
            case MostrarDinero -> mostrarDinero((V_MostrarDinero) e);
        }
    }

    /**
     * Indica los tipos de datos que escucha la clase
     * @return un arreglo que india el tipo de evento que escucha la clase
     */
    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    @Override
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
        handler.enviar(new M_PedirDinero());
    }
}
