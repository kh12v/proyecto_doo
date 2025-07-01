package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.Cargo;

public class M_AgregarEmpleado extends Evento {
    public Cargo cargo;

    public M_AgregarEmpleado(Cargo cargo) {
        super(TipoEvento.AgregarEmpleado, DestinoEvento.Controlador);

        this.cargo = cargo;
    }
}
