package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.Enums.Especies;

public class V_ActualizarCliente extends Evento {
    private final Especies especiePedida;
    public V_ActualizarCliente(Especies especiePedida) {
        super(TipoEvento.ActualizarCliente, DestinoEvento.Vista);
        this.especiePedida = especiePedida;
    }

    public Especies getEspeciePedida() {
        return especiePedida;
    }
}
