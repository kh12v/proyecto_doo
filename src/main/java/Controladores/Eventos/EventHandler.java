package Controladores.Eventos;

import java.util.ArrayList;
import java.util.Arrays;

public class EventHandler {
    public ArrayList<Suscriptor> eventos;

    public EventHandler() {
        eventos = new ArrayList<>();
    }

    public void enviar(Evento evento) {
        for (Suscriptor suscriptor : eventos)
            for(DestinoEvento d : suscriptor.getEventosEscuchados())
                if (d == evento.getDestino()) {
                    suscriptor.recibir(evento);
                    break;
                }
    }

    public void suscribir(Suscriptor suscriptor) {
        eventos.add(suscriptor);
    }
}
