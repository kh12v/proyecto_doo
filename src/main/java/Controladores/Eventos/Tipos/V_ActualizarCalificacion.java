package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class V_ActualizarCalificacion extends Evento {
    private double nuevaCalificacion;
    public V_ActualizarCalificacion(double nuevaCalificacion) {
        super(TipoEvento.ActualizarCalificacion, DestinoEvento.Vista);
        this.nuevaCalificacion = nuevaCalificacion;
    }

    public double getNuevaCalificacion() {
        return nuevaCalificacion;
    }
}
