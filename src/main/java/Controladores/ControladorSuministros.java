package Controladores;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Controladores.Eventos.Tipos.*;
import Logica.Enums.TipoProducto;
import Logica.Tienda;

import java.util.HashMap;

/**
 * Instancia de {@code Controlador} encargada de manejar los eventos relacionados con los suministros de la {@code Tienda}
 */
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
            case ConsumirProducto -> contestarConsumirProducto((M_ConsumirProducto) e);
        }
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Controlador};
    }

    public void contestarComprarProducto(M_ComprarProducto e) {
        int codigoCompra = t.comprarProducto(e.producto,e.nombre,e.cantidad);
        if (codigoCompra < 0) {
            if (codigoCompra == Tienda.C_DineroInsuficiente) {
                handler.enviar(new V_MostrarMensaje("No cuenta con dinero suficiente"));
            } else if (codigoCompra == Tienda.C_NoJaulaDisponible) {
                handler.enviar(new V_MostrarMensaje("No hay una jaula disponible"));
            } else {
                handler.enviar(new V_MostrarMensaje("Error en la compra"));
            }
        } else {
            handler.enviar(new V_MostrarMensaje("Compra exitosa (x" + e.cantidad + " "+ e.producto.getNombre()+")" ));
            if (e.producto.getTipoProducto() == TipoProducto.Mascota) {
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
        int jabones = t.getStockJabones();

        handler.enviar(new V_MostrarProductos(e.id, alimentos, medicamentos, juguetes, jabones));
    }

    public void contestarConsumirProducto(M_ConsumirProducto e) {
        int resultado = -1;
        switch (e.tipoProducto) {
            case TipoProducto.Comida -> resultado = t.consumirAlimento(e.id, e.especie);
            case TipoProducto.Medicamento -> resultado = t.consumirMedicamento(e.id, e.especie);
            case TipoProducto.Juguete -> resultado = t.consumirJuguete(e.id, e.especie);
            case TipoProducto.Higiene -> resultado = t.consumirHigiene(e.id);
        }

        if (resultado == Tienda.C_Error) {
            handler.enviar(new V_MostrarMensaje("Error al intentar interactuar"));
        } else if (resultado == Tienda.C_StockInsuficiente) {
            handler.enviar(new V_MostrarMensaje("No cuenta con stock del producto"));
        } else {
            int[] indicadores = t.getIndicadores(e.id);
            if (indicadores.length == 0) return;
            HashMap<Integer, int[]> indHash = new HashMap<>();
            indHash.put(e.id, indicadores);
            handler.enviar(new V_ActualizarIndicadoresMascotas(indHash));
        }
    }
}
