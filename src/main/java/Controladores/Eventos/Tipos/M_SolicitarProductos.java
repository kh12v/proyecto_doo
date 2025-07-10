package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.Enums.Especie;

public class M_SolicitarProductos extends Evento {
    public int id;
    public Especie especie;

    public M_SolicitarProductos(int id, Especie especie) {
        super(TipoEvento.SolicitarProductos, DestinoEvento.Controlador);
        this.id = id;
        this.especie = especie;
    }
}
