package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class M_PedirIndicadoresMascotas extends Evento {
    private final int[] ids;
    public static final int[] WILD = {};
    public M_PedirIndicadoresMascotas(int[] ids) {
        super(TipoEvento.PedirIndicadores, DestinoEvento.Controlador);
        this.ids = ids;
    }

    public int[] getIds() {
        return ids;
    }
}
