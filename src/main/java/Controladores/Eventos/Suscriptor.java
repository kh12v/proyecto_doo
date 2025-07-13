package Controladores.Eventos;

/**
 * Interfaz que implementa cada clase que puede reaccionar a un {@code Evento} enviado, las implementaciones
 * de esta interfaz pueden suscribirse a un {@code EventHandler}, el cual proveerá un {@code Evento} cada vez que un
 * {@code Publicador} lo envíe, bajo ciertas condiciones.
 */
public interface Suscriptor {
    /**
     * Este método se encarga de manejar los distintos tipos de {@code Evento} que pueden ser enviados, idealmente
     * solo el {@code EventHandler} suscrito debe usar este método, para asegurar consistencia entre instancias de {@code Suscriptor}.
     * @param e Evento enviado por el {@code EventHandler}
     */
    void recibir(Evento e);

    /**
     * Este metodo especifica a qué canales del {@code EventHandler} él {@code Suscriptor} realmente escucha, él {@code Suscriptor}
     * no deberia recibir ningun {@code Evento} cuyo {@code DestinoEvento} no este aquí.
     * @return La lista de canales a las que él {@code Suscriptor} escucha
     */
    DestinoEvento[] getEventosEscuchados();
}
