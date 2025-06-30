package Controladores;

import Controladores.Estado.JaulaState;
import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Controladores.Eventos.Tipos.*;
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
        }
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Controlador};
    }

    public void contestarComprarProducto(M_ComprarProducto e) {
        if (!t.comprarProducto(e.producto)) {
            handler.enviar(new V_MostrarMensaje("Compra fallida"));
        } else {
            handler.enviar(new V_MostrarMensaje("Compra exitosa"));
            if (e.producto.getTipoProducto() == TipoProducto.Mascota) {
                // Se actualizarán todas las mascotas
                List<Jaula> mascotas = t.getJaulas();
                List<Integer> ids = mascotas.stream()
                        .map(Jaula::getID)
                        .toList();

                JaulaState[] filtrado = mascotas.stream()
                        .filter(m -> ids.contains(m.getID()))
                        .map(JaulaState::toState)
                        .toArray(JaulaState[]::new);

                handler.enviar(new V_ActualizarMascotas(filtrado));
            }
            handler.enviar(new V_MostrarDinero(t.getDinero()));
        }
    }
}
