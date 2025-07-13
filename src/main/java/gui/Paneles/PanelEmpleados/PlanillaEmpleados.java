package gui.Paneles.PanelEmpleados;

import Controladores.Estado.EmpleadoState;
import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirEmpleados;
import Controladores.Eventos.Tipos.V_ActualizarEmpleados;
import Logica.Enums.Cargo;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Muestra la información de todos los empleados de la tienda
 */
public class PlanillaEmpleados extends JPanel implements Suscriptor, Publicador {
    private final static Color COLOR_DE_FONDO = Color.GRAY;
    private final HashMap<Integer, PanelEmpleado> empleados;
    private final AgregarEmpleado botonAgregar;
    private EventHandler handler;

    public PlanillaEmpleados() {
        empleados = new HashMap<>();
        botonAgregar = new AgregarEmpleado();
        setBackground(COLOR_DE_FONDO);
        setLayout(new GridLayout(0, 3));
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
        empleados.forEach((k, v) -> v.enviarHandler(handler));
        botonAgregar.enviarHandler(handler);
        handler.enviar(new M_PedirEmpleados(M_PedirEmpleados.WILD));
    }

    /**
     * Maneja los eventos recibidos
     * @param e: Evento enviado
     */
    @Override
    public void recibir(Evento e) {
        switch (e.getTipo()) {
            case ActualizarEmpleados -> actualizarEmpleados((V_ActualizarEmpleados) e);
        }
    }

    /**
     * Indica el tipo de evento que recibe esta clase
     * @return Un array con los tipos de evento que recibe esta clase
     */
    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }

    /**
     * Actualiza la información de los empleados si se emitió un evento tipo V_ActualizarEmpleados
     * @param evento: Evento para actualizar empleados
     */
    private void actualizarEmpleados(V_ActualizarEmpleados evento) {
        remove(botonAgregar);

        EmpleadoState[] estados = evento.getEstados();
        for (EmpleadoState estado : estados) {
            if (empleados.containsKey(estado.id())) {
                if (estado.cargo() == Cargo.DESPEDIDO) {
                    quitarEmpleado(estado.id());
                } else {
                    empleados.get(estado.id()).modificarPanel(estado);
                }
            } else if (estado.cargo() != Cargo.DESPEDIDO) {
                agregarEmpleado(estado);
            }
        }

        setPreferredSize(new Dimension(PanelEmpleado.ANCHO * 3, PanelEmpleado.ALTO * (empleados.size() / 2 + 1)));
        add(botonAgregar);

        revalidate();
        repaint();
    }

    /**
     * Agregar un nuevo empleado al panel
     * @param estado: Información del nuevo empleado
     */
    private void agregarEmpleado(EmpleadoState estado) {
        PanelEmpleado empleado = new PanelEmpleado(COLOR_DE_FONDO, estado);
        empleado.enviarHandler(handler);
        empleados.put(estado.id(), empleado);
        add(empleado);
    }

    /**
     * Elimina el panel del empleado a eliminar
     * @param id: id del empleado que se desea eliminar
     */
    private void quitarEmpleado(int id) {
        PanelEmpleado panelEmpleado = empleados.remove(id);

        remove(panelEmpleado);

        revalidate();
        repaint();
    }
}
