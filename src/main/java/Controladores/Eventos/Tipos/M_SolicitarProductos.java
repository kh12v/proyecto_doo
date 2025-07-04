package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.Enums.Especies;

public class M_SolicitarProductos extends Evento {
    public int id;
    public Especies especie;

    public M_SolicitarProductos(int ind, Especies especie) {
        super(TipoEvento.SolicitarProductos, DestinoEvento.Controlador);

        this.especie = especie;
    }
}
