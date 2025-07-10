package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.Enums.Especie;

public class V_ActualizarCliente extends Evento {
    private final Especie especiePedida;
    public V_ActualizarCliente(Especie especiePedida) {
        super(TipoEvento.ActualizarCliente, DestinoEvento.Vista);
        this.especiePedida = especiePedida;
    }

    public Especie getEspeciePedida() {
        return especiePedida;
    }
}
