package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.Enums.Especie;
import Logica.Enums.TipoProducto;

public class M_ConsumirProducto extends Evento {
    public int id;
    public Especie especie;
    public TipoProducto tipoProducto;

    public M_ConsumirProducto(int id, Especie especie, TipoProducto tipoProducto) {
        super(TipoEvento.ConsumirProducto, DestinoEvento.Controlador);
        System.out.println(id);
        this.id = id;
        this.especie = especie;
        this.tipoProducto = tipoProducto;
    }
}
