package Controladores;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.V_ActualizarCalificacion;
import Logica.Tienda;

public class ControladorPrincipal implements Controlador {
    private final ControladorMascotas mascotas;
    private final ControladorEmpleados empleados;
    private final ControladorSuministros suministros;
    private EventHandler handler;
    private final Tienda tienda;

    public ControladorPrincipal(Tienda tienda) {
        mascotas = new ControladorMascotas(tienda);
        empleados = new ControladorEmpleados(tienda);
        suministros = new ControladorSuministros(tienda);
        this.tienda = tienda;
    }

    public void enviarHandler(EventHandler handler) {
        mascotas.enviarHandler(handler);
        empleados.enviarHandler(handler);
        suministros.enviarHandler(handler);
        this.handler = handler;
        handler.suscribir(this);
    }

    @Override
    public void recibir(Evento evento) {
        switch (evento.getTipo()) {
            case PedirCalificacion -> actualizarCalificacion();
        }
    }

    private void actualizarCalificacion() {
        handler.enviar(new V_ActualizarCalificacion(tienda.getCalificacion()));
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Controlador};
    }
}
