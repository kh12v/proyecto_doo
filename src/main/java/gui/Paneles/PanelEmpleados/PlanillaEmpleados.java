package gui.Paneles.PanelEmpleados;

import Controladores.Estado.EmpleadoState;
import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirEmpleados;
import Controladores.Eventos.Tipos.V_ActualizarEmpleados;
import Controladores.Eventos.Tipos.V_QuitarEmpleado;
import Logica.Tienda;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PlanillaEmpleados extends JPanel implements Suscriptor, Publicador {
    private EventHandler handler;
    private final HashMap<Integer,PanelEmpleado> empleados;
    private final AgregarEmpleado botonAgregar;
    private Tienda t;

    private final static Color COLOR_DE_FONDO = Color.GRAY;

    public PlanillaEmpleados(Tienda tienda) {
        this.t = tienda;
        empleados = new HashMap<>();
        botonAgregar = new AgregarEmpleado();
        setBackground(COLOR_DE_FONDO);
        setLayout(new GridLayout(0, 3));
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
        empleados.forEach((k,v) -> v.enviarHandler(handler));
        botonAgregar.enviarHandler(handler);
        handler.enviar(new M_PedirEmpleados(M_PedirEmpleados.WILD));
    }

    @Override
    public void recibir(Evento evento) {
        switch (evento.getTipo()){
            case ActualizarEmpleados -> actualizarEmpleados((V_ActualizarEmpleados) evento);
            case QuitarEmpleado -> quitarEmpleado((V_QuitarEmpleado) evento);
        }
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }

    public void actualizarEmpleados(V_ActualizarEmpleados evento) {
        remove(botonAgregar);

        EmpleadoState[] estados = evento.getEstados();
        for(EmpleadoState estado: estados){
            if(empleados.containsKey(estado.id())){
                empleados.get(estado.id()).modificarPanel(estado);
            } else {
                agregarEmpleado(estado);
            }
        }

        setPreferredSize(new Dimension(PanelEmpleado.ANCHO*3, PanelEmpleado.ALTO * (empleados.size()/2 + 1)));
        add(botonAgregar);

        revalidate();
        repaint();
    }

    public void agregarEmpleado(EmpleadoState estado){
        PanelEmpleado empleado = new PanelEmpleado(COLOR_DE_FONDO, estado);
        empleado.enviarHandler(handler);
        empleados.put(estado.id(),empleado);
        add(empleado);
    }

    public void quitarEmpleado(V_QuitarEmpleado evento){
        PanelEmpleado panelEmpleado = empleados.remove(evento.id);

        remove(panelEmpleado);

        revalidate();
        repaint();
    }
}
