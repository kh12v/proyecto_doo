package Controladores.Eventos.Tipos;

import Controladores.Estado.JaulaState;
import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

/**
 * Anuncia un cambio en la información del modelo, para que la vista se encargue de manejarlo.
 */
public class V_ActualizarMascotas extends Evento {
    JaulaState[] mascotas;

    public V_ActualizarMascotas(JaulaState[] mascotas) {
        super(TipoEvento.ActualizarMascotas, DestinoEvento.Vista);
        this.mascotas = mascotas;
    }

    public JaulaState[] getEstados() {
        return mascotas;
    }
}
