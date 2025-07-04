package Controladores;

import Controladores.Estado.JaulaState;
import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Controladores.Eventos.Tipos.*;
import Logica.Especies;
import Logica.Jaula;
import Logica.Tienda;
import Logica.TipoProducto;

import java.util.List;

public class ControladorSuministros implements Controlador {
    private final Tienda t;
    private EventHandler handler;
    public ControladorSuministros(Tienda t) {
        this.t = t;
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
    }

    @Override
    public void recibir(Evento e) {
        switch (e.getTipo()) {
            case ComprarProducto -> contestarComprarProducto((M_ComprarProducto) e);
            case SolicitarProductos -> contestarSolicitarProductos((M_SolicitarProductos) e);
        }
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Controlador};
    }

    public void contestarComprarProducto(M_ComprarProducto e) {
        int codigoCompra = t.comprarProducto(e.producto);
        if (codigoCompra < 0) {
            if (codigoCompra == Tienda.C_DineroInsuficiente) {
                handler.enviar(new V_MostrarMensaje("No cuenta con dinero suficiente"));
            } else if (codigoCompra == Tienda.C_NoJaulaDisponible) {
                handler.enviar(new V_MostrarMensaje("No hay una jaula disponible"));
            } else {
                handler.enviar(new V_MostrarMensaje("Error en la compra"));
            }
        } else {
            handler.enviar(new V_MostrarMensaje("Compra exitosa"));
            if (e.producto.getTipoProducto() == TipoProducto.Mascota) {
                //dejemos al controlador encargarse de esto
                handler.enviar(new M_PedirMascotas(new int[]{codigoCompra}));
            }
            handler.enviar(new V_MostrarDinero(t.getDinero()));
        }
    }

    public void contestarSolicitarProductos(M_SolicitarProductos e) {
        int indice = -1;
        switch (e.especie) {
            case Perro -> indice = Tienda.I_Perro;
            case Gato -> indice = Tienda.I_Gato;
            case Loro -> indice = Tienda.I_Loro;
            case Hamster -> indice = Tienda.I_Hamster;
        }

        int alimentos = t.getStockAlimentos(indice);
        int medicamentos = t.getStockMedicamentos(indice);
        int juguetes = t.getStockJuguetes(indice);

        handler.enviar(new V_MostrarProductos(alimentos, medicamentos, juguetes));
    }
}
