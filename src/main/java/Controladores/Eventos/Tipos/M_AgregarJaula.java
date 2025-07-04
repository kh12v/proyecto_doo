package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.Enums.TipoContenedor;

public class M_AgregarJaula extends Evento {
    public TipoContenedor tipoContenedor;

    public M_AgregarJaula(TipoContenedor tipoContenedor) {
        super(TipoEvento.AgregarJaula, DestinoEvento.Controlador);

        this.tipoContenedor = tipoContenedor;
    }
}
