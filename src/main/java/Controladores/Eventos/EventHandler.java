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
            if (Arrays.stream(suscriptor.getEventosEscuchados()).anyMatch(m -> m == evento.getDestino())) {
                suscriptor.recibir(evento);
            }
    }

    public void suscribir(Suscriptor suscriptor) {
        eventos.add(suscriptor);
    }
}
