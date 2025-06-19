package Controladores.Eventos;

import java.util.ArrayList;

public interface Suscriptor {
    void recibir(Evento evento);

    DestinoEvento[] getEventosEscuchados();
}
