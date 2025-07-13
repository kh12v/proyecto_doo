package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.Enums.Producto;

public class M_ComprarProducto extends Evento {
    public  final String nombre;
    public  final Producto producto;

    public M_ComprarProducto(Producto producto, String nombre) {
        super(TipoEvento.ComprarProducto, DestinoEvento.Controlador);
        this.producto = producto;
        this.nombre = nombre;
    }
    public M_ComprarProducto(Producto producto) {
        this(producto,null);
    }
}
