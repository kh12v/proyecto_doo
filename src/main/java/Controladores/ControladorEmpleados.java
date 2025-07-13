package Controladores;

import Controladores.Estado.EmpleadoState;
import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Controladores.Eventos.Tipos.*;
import Logica.Empleado;
import Logica.Tienda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Instancia de {@code Controlador} encargada de manejar los eventos relacionados con un {@code Empleado} de la {@code Tienda}
 */
public class ControladorEmpleados implements Controlador {
    EventHandler handler;
    Tienda t;

    public ControladorEmpleados(Tienda t) {
        this.t = t;
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Controlador};
    }

    @Override
    public void recibir(Evento e) {
        switch (e.getTipo()) {
            case PedirEmpleados -> contestarPeticionEmpleados((M_PedirEmpleados) e);
            case AgregarEmpleado -> contestarAgregarEmpleado((M_AgregarEmpleado) e);
            case DespedirEmpleado -> contestarDespedirEmpleado((M_DespedirEmpleado) e);
        }
    }

    public void contestarPeticionEmpleados(M_PedirEmpleados evento) {
        int[] ids = (evento.getIDs() == M_PedirEmpleados.WILD)
                ? t.getEmpleados().stream().mapToInt(Empleado::getID).toArray()
                : evento.getIDs();

        ArrayList<Empleado> empleados = t.getEmpleados();

        ArrayList<EmpleadoState> empleadosEncontrados = new ArrayList<>(empleados.stream()
                .filter(e -> Arrays.stream(ids).anyMatch(i -> i == e.getID()))
                .map(EmpleadoState::toState).toList());

        List<EmpleadoState> empleadosBorrados = Arrays.stream(ids)
                .boxed()
                .filter(Predicate.not(t::encontrarIDEmpleados))
                .map(EmpleadoState::createDEL)
                .toList();

        empleadosEncontrados.addAll(empleadosBorrados);

        handler.enviar(new V_ActualizarEmpleados(empleadosEncontrados.toArray(new EmpleadoState[0])));
    }

    public void contestarAgregarEmpleado(M_AgregarEmpleado e) {
        int id = t.contratarEmpleado(e.cargo);
        if (id < 0) {
            handler.enviar(new V_MostrarMensaje("Error al intentar contratar empleado"));
        } else {
            handler.enviar(new V_MostrarMensaje("Empleado contratado exitosamente"));
            handler.enviar(new V_MostrarDinero(t.getDinero()));
            handler.enviar(new M_PedirEmpleados(new int[]{id}));
        }
    }

    public void contestarDespedirEmpleado(M_DespedirEmpleado e) {
        int resultado = t.despedirEmpleado(e.estado.id());
        if (resultado < 0) {
            handler.enviar(new V_MostrarMensaje("Error al intentar despedir empleado"));
        } else {
            handler.enviar(new V_MostrarMensaje("Empleado despedido exitosamente"));
            handler.enviar(new M_PedirEmpleados(new int[]{e.estado.id()}));
        }
    }
}
