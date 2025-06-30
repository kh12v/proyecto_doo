package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.Producto;
import Logica.TipoContenedor;

public class M_ComprarProducto extends Evento {
    public Producto producto;

    public M_ComprarProducto(Producto producto) {
        super(TipoEvento.ComprarProducto, DestinoEvento.Controlador);
        this.producto = producto;
    }
}
