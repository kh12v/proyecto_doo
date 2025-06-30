package gui.Paneles.PanelMascotas;

import Controladores.Estado.JaulaState;
import Controladores.Estado.MascotaState;
import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirMascotas;
import Controladores.Eventos.Tipos.V_ActualizarMascotas;
import Logica.Especies;
import Logica.Tienda;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class AlmacenMascotas extends JPanel implements Suscriptor, Publicador {
    private EventHandler handler;
    private final HashMap<Integer,PanelMascota> mascotas;

    private Tienda t;

    public AlmacenMascotas(Tienda tienda) {
        this.t = tienda;
        mascotas = new HashMap<>();
        setBackground(Color.GRAY);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(PanelMascota.ANCHO, PanelMascota.ALTO*3));
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
        mascotas.forEach((k,v) -> v.enviarHandler(handler));
        handler.enviar(new M_PedirMascotas(M_PedirMascotas.WILD));
    }


    @Override
    public void recibir(Evento evento) {
        switch (evento.getTipo()){
            case ActualizarMascotas -> actualizarMascotas((V_ActualizarMascotas) evento);
        }
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }

    public void actualizarMascotas(V_ActualizarMascotas evento) {
        removeAll();

        int jaulasGrandes = 0;
        int jaulasPequenas = 0;

        JaulaState[] estados = evento.getEstados();
        for (JaulaState estadoActual : estados) {
            if (mascotas.containsKey(estadoActual.id())) {
                if (estadoActual.especie().getEsAnimalGrande()) {
                    jaulasGrandes++;
                } else {
                    jaulasPequenas++;
                }
                mascotas.get(estadoActual.id()).modificarPanel(estadoActual);
            } else {
                agregarMascota(estadoActual);
            }
        }

        // Muestra las jaulas vacias
        for (int i = 0; i < t.getJaulasGrandes() - jaulasGrandes; i++) {
            add(new JaulaPanel(Especies.NullGrande));
        }
        for (int i = 0; i < t.getJaulasPequenas() - jaulasPequenas; i++) {
            add(new JaulaPanel(Especies.NullPequeno));
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
