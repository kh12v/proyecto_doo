package gui.Paneles.PanelEmpleados;

import Controladores.Estado.EmpleadoState;
import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirEmpleados;
import Controladores.Eventos.Tipos.V_ActualizarEmpleados;
import Logica.Enums.Cargo;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PlanillaEmpleados extends JPanel implements Suscriptor, Publicador {
    private EventHandler handler;
    private final HashMap<Integer,PanelEmpleado> empleados;
    private final AgregarEmpleado botonAgregar;

    private final static Color COLOR_DE_FONDO = Color.GRAY;

    public PlanillaEmpleados() {
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
        }
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }

    private void actualizarEmpleados(V_ActualizarEmpleados evento) {
        remove(botonAgregar);

        EmpleadoState[] estados = evento.getEstados();
        for(EmpleadoState estado: estados){
            if(empleados.containsKey(estado.id())){
                if(estado.cargo() == Cargo.DESPEDIDO){
                    quitarEmpleado(estado.id());
                } else {
                    empleados.get(estado.id()).modificarPanel(estado);
                }
            } else if (estado.cargo() != Cargo.DESPEDIDO){
                agregarEmpleado(estado);
            }
        }

        setPreferredSize(new Dimension(PanelEmpleado.ANCHO*3, PanelEmpleado.ALTO * (empleados.size()/2 + 1)));
        add(botonAgregar);

        revalidate();
        repaint();
    }

    private void agregarEmpleado(EmpleadoState estado){
        PanelEmpleado empleado = new PanelEmpleado(COLOR_DE_FONDO, estado);
        empleado.enviarHandler(handler);
        empleados.put(estado.id(),empleado);
        add(empleado);
    }

    private void quitarEmpleado(int id){
        PanelEmpleado panelEmpleado = empleados.remove(id);

        remove(panelEmpleado);

        revalidate();
        repaint();
    }
}
