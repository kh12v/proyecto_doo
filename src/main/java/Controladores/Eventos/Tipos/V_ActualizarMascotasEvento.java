package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Controladores.Estado.MascotaState;

/**
 * Anuncia un cambio en la información del modelo, para que la vista se encargue de manejarlo.
 */
public class V_ActualizarMascotasEvento extends Evento {
    MascotaState[] mascotas;
    public V_ActualizarMascotasEvento(MascotaState[] mascotas) {
        super(TipoEvento.ActualizarMascotas, DestinoEvento.Vista);
        this.mascotas = mascotas;
    }
    public MascotaState[] getMascotas() {
        return mascotas;
    }

    public MascotaState[] getEstados() {
        return mascotas;
    }
}
