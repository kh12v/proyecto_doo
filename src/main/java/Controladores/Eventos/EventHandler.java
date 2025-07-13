package Controladores.Eventos;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase encargada de recibir eventos de instancias de {@code Publicador} y entregarlos a cada {@code Suscriptor} suscrito a este.
 * @see Evento
 * @see Suscriptor
 */
public class EventHandler {
    public ArrayList<Suscriptor> suscriptores;

    public EventHandler() {
        suscriptores = new ArrayList<>();
    }

    /**
     * Este método le ordena al {@code EventHandler} publicar un {@code Evento} a todos los {@code Suscriptor} que conoce.
     * @param evento el {@code Evento} a enviar
     */
    public void enviar(Evento evento) {
        for (Suscriptor suscriptor : suscriptores)
            if (Arrays.stream(suscriptor.getEventosEscuchados()).anyMatch(m -> m == evento.getDestino())) {
                suscriptor.recibir(evento);
            }
    }

    /**
     * Agrega un {@code Suscriptor} a la lista que guarda el {@code EventHandler}
     * @param suscriptor el {@code Suscriptor} a añadir
     */
    public void suscribir(Suscriptor suscriptor) {
        if(suscriptor == null){return;}
        suscriptores.add(suscriptor);
    }
}
