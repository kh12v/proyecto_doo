package Controladores;

import Controladores.Estado.MascotaState;
import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirMascotasEvento;
import Controladores.Eventos.Tipos.V_ActualizarIndicadoresMascotasEvento;
import Controladores.Eventos.Tipos.V_ActualizarMascotasEvento;
import Logica.Indicador;
import Logica.Mascota;
import Logica.Tienda;

import java.util.List;

public class ControladorMascotas implements Controlador {
    EventHandler handler;
    Tienda t;

    public ControladorMascotas(Tienda t) {
        this.t = t;
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
        handler.suscribir(this);
    }
    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Controlador};
    }

    @Override
    public void recibir(Evento e) {
        switch (e.getTipo()) {
            case PedirMascotas -> contestarPeticionMascotas((M_PedirMascotasEvento) e);
            case PedirIndicadores -> contestarPeticionIndicadores();
        }
    }

    private void contestarPeticionIndicadores() {
        int[][] indicadores = t.getMascotas().stream()
                .map(Mascota::getIndicadores)
                .map(ind -> ind.stream().mapToInt(Indicador::getValor).toArray())
                .toArray(int[][]::new);
        handler.enviar(new V_ActualizarIndicadoresMascotasEvento(indicadores));
    }

    public void contestarPeticionMascotas(M_PedirMascotasEvento e) {
        List<Mascota> mascotas = t.getMascotas();
        //filtra todas las ids pedidas que tiene la tienda, o entrega todas en caso de WILD
        List<Integer> ids = (e.getIDs() == M_PedirMascotasEvento.WILD)
                ? mascotas.stream()
                .map(Mascota::getID)
                .toList()
                : mascotas.stream()
                .map(Mascota::getID)
                .filter(t::encontrarIDMascotas)
                .toList();

        //filtra todas las mascotas con las ids pedidas
        MascotaState[] filtrado = mascotas.stream()
                .filter(m -> ids.contains(m.getID()))
                .map(MascotaState::toState)
                .toArray(MascotaState[]::new);

        handler.enviar(new V_ActualizarMascotasEvento(filtrado));
    }
}
