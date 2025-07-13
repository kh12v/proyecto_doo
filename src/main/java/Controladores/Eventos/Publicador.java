package Controladores.Eventos;

/**
 * Interfaz que debe implementar cada objeto que envía {@code Evento} a traves de {@code EventHandler} a implementaciones de {@code Suscriptor},
 * sea a traves de métodos propios o de objetos que lo componen.
 * @see EventHandler
 * @see Suscriptor
 */
public interface Publicador {
    /**
     * Entrega el {@code EventHandler} al que el objeto enviará {@code Evento}, puede ser llamado en cascada
     * para enviar el mismo {@code EventHandler} a las partes que lo componen.
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     */
    void enviarHandler(EventHandler handler);
}
