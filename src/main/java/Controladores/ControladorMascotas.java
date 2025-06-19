package Controladores;

import Controladores.Estado.MascotaState;
import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirMascotasEvento;
import Controladores.Eventos.Tipos.V_ActualizarMascotasEvento;
import Logica.Mascota;
import Logica.Tienda;

import java.util.ArrayList;
import java.util.Arrays;

public class ControladorMascotas implements Suscriptor, Publicador {
    EventHandler handler;
    Tienda t;

    public ControladorMascotas(Tienda t) {
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
            case PedirMascotas -> contestarPeticionMascotas((M_PedirMascotasEvento) e);
        }
    }

    public void contestarPeticionMascotas(M_PedirMascotasEvento e) {
        ArrayList<Mascota> mascotas = t.getMascotas();
        int[] indices;
        //no me gusta este if
        if (e.getIndices() == M_PedirMascotasEvento.WILD){
            indices = new int[mascotas.size()];
            for (int i = 0; i < mascotas.size(); i++){
                indices[i] = i;
            }
        } else {
            indices = Arrays.stream(e.getIndices())
                    .filter(i -> i >= 0 && i < mascotas.size())
                    .toArray();
        }
        ArrayList<Mascota> mascotasFiltro = new ArrayList<>(Arrays.stream(indices).mapToObj(mascotas::get).toList());
        handler.enviar(new V_ActualizarMascotasEvento(mascotasFiltro,indices,e.isForzar()));
    }
}
