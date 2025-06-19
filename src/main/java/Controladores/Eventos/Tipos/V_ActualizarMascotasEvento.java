package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Controladores.Estado.MascotaState;
import Logica.Mascota;

import java.util.ArrayList;

/**
 * Anuncia un cambio en la información del modelo, para que la vista se encargue de manejarlo.
 */
public class V_ActualizarMascotasEvento extends Evento {
    //TODO confirmar si se podría usar un hashmap
    //Esto devuelve estados de mascota y donde va cada uno en el panel
    MascotaState[] mascotas;
    int[] indices;
    boolean forzar;
    public V_ActualizarMascotasEvento(ArrayList<Mascota> mascotas, int[] indices, boolean forzar) {
        super(TipoEvento.EntregarMascotas, DestinoEvento.Vista);
        this.forzar = forzar;
        this.indices = indices;
        this.mascotas = mascotas
                .stream()
                .map(MascotaState::toState)
                .toArray(MascotaState[]::new);
    }
    public MascotaState[] getMascotas() {
        return mascotas;
    }

    public boolean isForzar() {
        return forzar;
    }
    public int[] getIndices() {
        return indices;
    }
    public MascotaState[] getEstados() {
        return mascotas;
    }
}
