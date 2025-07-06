package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.Enums.Especies;
import Logica.Enums.TipoProducto;

public class M_ConsumirProducto extends Evento {
    public int id;
    public Especies especie;
    public TipoProducto tipoProducto;

    public M_ConsumirProducto(int id, Especies especie, TipoProducto tipoProducto) {
        super(TipoEvento.ConsumirProducto, DestinoEvento.Controlador);
        System.out.println(id);
        this.id = id;
        this.especie = especie;
        this.tipoProducto = tipoProducto;
    }
}
