package Controladores;

import Controladores.Estado.EmpleadoState;
import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Controladores.Eventos.Tipos.*;
import Logica.Empleado;
import Logica.Tienda;

import java.util.List;

public class ControladorEmpleados implements Controlador {
    EventHandler handler;
    Tienda t;

    public ControladorEmpleados(Tienda t) {
        this.t = t;
    }

    public void enviarHandler(EventHandler handler){
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

    public void contestarPeticionEmpleados(M_PedirEmpleados e) {
        List<Empleado> empleados = t.getEmpleados();
        //filtra todas las ids pedidas que tiene la tienda, o entrega todas en caso de WILD
        List<Integer> ids = (e.getIDs() == M_PedirEmpleados.WILD)
                ? empleados.stream()
                .map(Empleado::getID)
                .toList()
                : empleados.stream()
                .map(Empleado::getID)
                .filter(t::encontrarIDEmpleados)
                .toList();

        //filtra todos los empleados con las ids pedidas
        EmpleadoState[] filtrado = empleados.stream()
                .filter(p -> ids.contains(p.getID()))
                .map(EmpleadoState::toState)
                .toArray(EmpleadoState[]::new);

        handler.enviar(new V_ActualizarEmpleados(filtrado));
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
            handler.enviar(new V_QuitarEmpleado(e.estado.id()));
        }
    }
}
