package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class V_ActualizarIndicadoresMascotasEvento extends Evento {
    int[][] indicadores;
    public V_ActualizarIndicadoresMascotasEvento(int[][] indicadores) {
        super(TipoEvento.ActualizarIndicadores, DestinoEvento.Vista);
        this.indicadores = indicadores;
    }
    public int[][] getIndicadores() {
        return indicadores;
    }
}
