package gui.Paneles.PanelMascotas;

import Controladores.Estado.JaulaState;
import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirMascotas;
import Controladores.Eventos.Tipos.V_ActualizarIndicadoresMascotas;
import Controladores.Eventos.Tipos.V_ActualizarMascotas;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Menu principal que muestra a las mascotas que posee la tienda al igual que sus indicadores
 * respectivos
 */
public class AlmacenMascotas extends JPanel implements Suscriptor, Publicador {
    private final HashMap<Integer, PanelMascota> mascotas;
    private final AgregarJaula botonAgregar;
    private EventHandler handler;

    public AlmacenMascotas() {
        mascotas = new HashMap<>();
        botonAgregar = new AgregarJaula();
        setBackground(Color.GRAY);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(PanelMascota.ANCHO, PanelMascota.ALTO * 3));
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
        mascotas.forEach((k, v) -> v.enviarHandler(handler));
        botonAgregar.enviarHandler(handler);
        handler.enviar(new M_PedirMascotas(M_PedirMascotas.WILD));

    }


    /**
     * Maneja los eventos recibidos
     * @param e: Evento enviado
     */
    @Override
    public void recibir(Evento e) {
        switch (e.getTipo()) {
            case ActualizarMascotas -> actualizarMascotas((V_ActualizarMascotas) e);
            case ActualizarIndicadores -> actualizarIndicadores((V_ActualizarIndicadoresMascotas) e);
        }
    }

    /**
     * Actualiza los indicadores de las mascotas indicadas
     * @param evento: Evento sobre actualizar indicadores de las mascotas
     */
    private void actualizarIndicadores(V_ActualizarIndicadoresMascotas evento) {
        HashMap<Integer, int[]> mapaIndicadores = evento.getIndicadores();
        mascotas.forEach((k, v) -> v.actualizarIndicadores(mapaIndicadores.getOrDefault(k, new int[]{})));
    }

    /**
     * Indica los tipos de eventos que maneja esta clase
     * @return: Array de los tipos de eventos que recibe esta clase
     */
    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }

    /**
     * Actualiza las mascotas indicadas
     * @param evento: Evento para actualizar mascotas
     */
    public void actualizarMascotas(V_ActualizarMascotas evento) {
        remove(botonAgregar);

        JaulaState[] estados = evento.getEstados();
        for (JaulaState estado : estados) {
            if (mascotas.containsKey(estado.id())) {
                mascotas.get(estado.id()).modificarPanel(estado);
            } else {
                agregarMascota(estado);
            }
        }

        setPreferredSize(new Dimension(PanelMascota.ANCHO * 3, PanelMascota.ALTO * (mascotas.size() / 2 + 1)));
        add(botonAgregar);

        revalidate();
        repaint();
    }

    /**
     * Agrega una nueva mascota al panel
     * @param estado: Información de la mascota nueva
     */
    public void agregarMascota(JaulaState estado) {
        PanelMascota mascota = new PanelMascota(estado);
        mascotas.put(estado.id(), mascota);
        add(mascota);
    }
}
