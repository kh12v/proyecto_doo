package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

import java.util.HashMap;

public class V_ActualizarIndicadoresMascotas extends Evento {
    HashMap<Integer,int[]> indicadores;
    public V_ActualizarIndicadoresMascotas(HashMap<Integer,int[]> indicadores) {
        super(TipoEvento.ActualizarIndicadores, DestinoEvento.Vista);
        this.indicadores = indicadores;
    }
    public HashMap<Integer, int[]> getIndicadores() {
        return indicadores;
    }
}
