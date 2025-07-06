package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class M_EntregarMascota extends Evento {
    private final int id;
    public M_EntregarMascota(int id) {
        super(TipoEvento.EntregarMascota, DestinoEvento.Controlador);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
