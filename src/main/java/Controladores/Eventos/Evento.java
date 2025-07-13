package Controladores.Eventos;

/**
 * Representación de un evento al que algunos objetos reaccionarán, cada instancia de {@code Evento} guarda información sobre
 * lo ocurrido (especifica a la instancia), e información general sobre que ocurrió y a quienes les importa.
 */
public abstract class Evento {
    TipoEvento tipo;
    DestinoEvento destino;

    public Evento(TipoEvento tipo, DestinoEvento destino) {
        this.tipo = tipo;
        this.destino = destino;
    }

    /**
     * Entrega el {@code TipoEvento}, que proporciona información sobre el evento ocurrido.
     * @return el {@code TipoEvento}
     */
    public TipoEvento getTipo() {
        return tipo;
    }

    /**
     * Entrega el {@code DestinoEvento}, que proporciona información sobre quienes deben recibir el {@code Evento}
     * @return el {@code DestinoEvento}
     */
    public DestinoEvento getDestino() {
        return destino;
    }
}

