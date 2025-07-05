package Controladores;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Controladores.Eventos.Tipos.M_PedirIndicadoresMascotas;
import Controladores.Eventos.Tipos.V_ActualizarCalificacion;
import Controladores.Eventos.Tipos.V_MostrarDinero;
import Logica.Actualizable;
import Logica.Tienda;

public class ControladorPrincipal implements Controlador, Actualizable {
    private final ControladorMascotas mascotas;
    private final ControladorEmpleados empleados;
    private final ControladorSuministros suministros;
    private final Tienda tienda;
    private EventHandler handler;

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
            case PedirCalificacion -> handler.enviar(new V_ActualizarCalificacion(tienda.getCalificacion()));
            case PedirDinero -> handler.enviar(new V_MostrarDinero(tienda.getDinero()));
        }
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Controlador};
    }

    @Override
    public void actualizar() {
        tienda.actualizar();
        handler.enviar(new V_MostrarDinero(tienda.getDinero()));
        handler.enviar(new M_PedirIndicadoresMascotas(M_PedirIndicadoresMascotas.WILD));
    }
}
