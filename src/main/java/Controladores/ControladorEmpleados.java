package Controladores;

import Controladores.Estado.EmpleadoState;
import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Controladores.Eventos.Tipos.M_PedirEmpleados;
import Controladores.Eventos.Tipos.V_ActualizarEmpleados;
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
}
