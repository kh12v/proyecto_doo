package Controladores.Eventos;

import java.util.ArrayList;

public class EventHandler {
    public ArrayList<Suscriptor> eventos;
    public void enviar(Evento evento){
        for(Suscriptor suscriptor : eventos)
            suscriptor.recibir(evento);
    }
    public void suscribir(Suscriptor suscriptor){
        eventos.add(suscriptor);
    }
}
