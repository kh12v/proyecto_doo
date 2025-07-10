package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.Enums.TipoJaula;

public class M_AgregarJaula extends Evento {
    public TipoJaula tipoJaula;

    public M_AgregarJaula(TipoJaula tipoJaula) {
        super(TipoEvento.AgregarJaula, DestinoEvento.Controlador);

        this.tipoJaula = tipoJaula;
    }
}
