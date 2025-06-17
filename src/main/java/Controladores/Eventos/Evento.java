package Controladores.Eventos;

public abstract class Evento {
    TipoEvento tipo;
    public Evento(TipoEvento tipo) {
        this.tipo = tipo;
    }
    public TipoEvento getTipo() {
        return tipo;
    }
}
