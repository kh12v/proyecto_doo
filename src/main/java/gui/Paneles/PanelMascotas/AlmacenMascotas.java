package gui.Paneles.PanelMascotas;

import Controladores.Estado.MascotaState;
import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirMascotasEvento;
import Controladores.Eventos.Tipos.V_ActualizarMascotasEvento;
import Logica.Especies;

import javax.swing.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.util.ArrayList;

public class AlmacenMascotas extends JPanel implements Suscriptor, Publicador {
    private EventHandler handler;
    private final ArrayList<PanelMascota> mascotas;

    public AlmacenMascotas() {
        mascotas = new ArrayList<>();
        setBackground(Color.GRAY);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(PanelMascota.ANCHO, PanelMascota.ALTO*3));
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
        mascotas.forEach(m -> m.enviarHandler(handler));
        handler.enviar(new M_PedirMascotasEvento(M_PedirMascotasEvento.WILD,true));
    }


    @Override
    public void recibir(Evento evento) {
        switch (evento.getTipo()){
            case ActualizarMascotas -> {
                actualizarMascotas((V_ActualizarMascotasEvento) evento);
            }
        }
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }

    public void actualizarMascotas(V_ActualizarMascotasEvento evento) {
        int[] indices = evento.getIndices();
        MascotaState[] estados = evento.getEstados();
        for(int i = 0; i < estados.length; i++){
            if(indices[i] == mascotas.size()){
                agregarMascota(estados[i]);
            } else if(indices[i] < mascotas.size()) {
                mascotas.get(indices[i]).modificarPanel(estados[i]);
            }
        }
        setPreferredSize(new Dimension(PanelMascota.ANCHO, PanelMascota.ALTO*Math.ceilDiv(mascotas.size(),3)));
        revalidate();
        repaint();
    }

    public void agregarMascota(MascotaState estado){
        PanelMascota mascota = new PanelMascota(Color.GRAY,estado);
        mascotas.add(mascota);
        add(mascota);
    }
}
