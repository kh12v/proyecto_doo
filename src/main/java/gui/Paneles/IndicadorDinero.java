package gui.Paneles;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirDinero;
import Controladores.Eventos.Tipos.V_MostrarDinero;

import javax.swing.*;

public class IndicadorDinero extends JLabel implements Suscriptor, Publicador {
    EventHandler handler;

    public IndicadorDinero() {
        setText("Dinero: $");

        setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void mostrarDinero(V_MostrarDinero evento) {
        setText("Dinero: $" + evento.dinero);
    }

    @Override
    public void recibir(Evento evento) {
        switch (evento.getTipo()) {
            case MostrarDinero -> mostrarDinero((V_MostrarDinero) evento);
        }
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }

    @Override
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
        handler.enviar(new M_PedirDinero());
    }
}
