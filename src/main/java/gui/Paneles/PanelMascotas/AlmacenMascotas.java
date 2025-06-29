package gui.Paneles.PanelMascotas;

import Controladores.Estado.MascotaState;
import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirMascotasEvento;
import Controladores.Eventos.Tipos.V_ActualizarMascotasEvento;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class AlmacenMascotas extends JPanel implements Suscriptor, Publicador {
    private EventHandler handler;
    private final HashMap<Integer,PanelMascota> mascotas;

    public AlmacenMascotas() {
        mascotas = new HashMap<>();
        setBackground(Color.GRAY);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(PanelMascota.ANCHO, PanelMascota.ALTO*3));
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
        mascotas.forEach((k,v) -> v.enviarHandler(handler));
        handler.enviar(new M_PedirMascotasEvento(M_PedirMascotasEvento.WILD));
    }


    @Override
    public void recibir(Evento evento) {
        switch (evento.getTipo()){
            case ActualizarMascotas -> actualizarMascotas((V_ActualizarMascotasEvento) evento);
        }
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }

    public void actualizarMascotas(V_ActualizarMascotasEvento evento) {
        removeAll();

        MascotaState[] estados = evento.getEstados();
        for (MascotaState estadoActual : estados) {
            if (mascotas.containsKey(estadoActual.id())) {
                mascotas.get(estadoActual.id()).modificarPanel(estadoActual);
            } else {
                agregarMascota(estadoActual);
            }
        }

        AgregarJaula agregarJaula = new AgregarJaula();
        agregarJaula.enviarHandler(handler);
        add(agregarJaula);

        revalidate();
        repaint();
    }

    public void agregarMascota(MascotaState estado){
        PanelMascota mascota = new PanelMascota(estado);
        mascotas.put(estado.id(),mascota);
        add(mascota);
    }
}
