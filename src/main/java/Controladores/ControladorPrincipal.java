package Controladores;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Controladores.Eventos.Tipos.*;
import Logica.Actualizable;
import Logica.Cliente;
import Logica.Tienda;

/**
 * Instancia de {@code Controlador} encargada de manejar eventos misceláneos de la {@code Tienda}, y delegar sus funciones a controladores más específicos
 */
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
    public void recibir(Evento e) {
        switch (e.getTipo()) {
            case PedirCalificacion -> handler.enviar(new V_ActualizarCalificacion(tienda.getCalificacion()));
            case PedirDinero -> handler.enviar(new V_MostrarDinero(tienda.getDinero()));
            case PedirCliente -> handler.enviar(new V_ActualizarCliente(tienda.getEspeciePedida()));
        }
    }


    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Controlador};
    }

    @Override
    public void actualizar() {
        if(debeCrearCliente()){
            tienda.agregarCliente(Cliente.clienteAleatorio());
            handler.enviar(new M_PedirCliente());
        }

        tienda.actualizar();

        handler.enviar(new V_MostrarDinero(tienda.getDinero()));
        handler.enviar(new M_PedirMascotas(M_PedirMascotas.WILD));

        handler.enviar(new V_ActualizarVentanaClientes());
    }

    private boolean debeCrearCliente() {
        double calificacion = tienda.getCalificacion();
        double random = Math.random();
        double probabilidad = (2 + calificacion)/10;
        return random < probabilidad;
    }
}
