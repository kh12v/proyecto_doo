package Controladores.Eventos;

public abstract class Evento {
    TipoEvento tipo;
    DestinoEvento destino;
    public Evento(TipoEvento tipo, DestinoEvento destino) {
        this.tipo = tipo;
        this.destino = destino;
    }
    public TipoEvento getTipo() {
        return tipo;
    }
    public DestinoEvento getDestino() {
        return destino;
    }
}

