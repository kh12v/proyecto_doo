package Controladores.Eventos;

public interface Suscriptor {
    void recibir(Evento evento);

    DestinoEvento[] getEventosEscuchados();
}
