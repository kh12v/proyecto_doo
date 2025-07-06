package Controladores.Eventos;

public interface Suscriptor {
    void recibir(Evento e);

    DestinoEvento[] getEventosEscuchados();
}
