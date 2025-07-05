package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class M_PedirIndicadoresMascotas extends Evento {
    public static final int[] WILD = {};
    private final int[] ids;

    public M_PedirIndicadoresMascotas(int[] ids) {
        super(TipoEvento.PedirIndicadores, DestinoEvento.Controlador);
        this.ids = ids;
    }

    public int[] getIds() {
        return ids;
    }
}
