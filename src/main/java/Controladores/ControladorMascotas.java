package Controladores;

import Controladores.Estado.JaulaState;
import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.*;
import Logica.Indicador;
import Logica.Jaula;
import Logica.Tienda;

import java.util.List;
import java.util.function.Predicate;

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
            case PedirMascotas -> contestarPeticionMascotas((M_PedirMascotas) e);
            case PedirIndicadores -> contestarPeticionIndicadores();
            case AgregarJaula -> contestarAgregarJaula((M_AgregarJaula) e);
        }
    }

    private void contestarPeticionIndicadores() {
        int[][] indicadores = t.getJaulas().stream()
                .filter(Predicate.not(Jaula::estaVacia))
                .map(Jaula::getIndicadores)
                .map(ind -> ind.stream()
                        .mapToInt(Indicador::getValor)
                        .toArray())
                .toArray(int[][]::new);
        handler.enviar(new V_ActualizarIndicadoresMascotas(indicadores));
    }

    public void contestarPeticionMascotas(M_PedirMascotas e) {
        List<Jaula> mascotas = t.getJaulas();
        //filtra todas las ids pedidas que tiene la tienda, o entrega todas en caso de WILD
        List<Integer> ids = (e.getIDs() == M_PedirMascotas.WILD)
                ? mascotas.stream()
                .map(Jaula::getID)
                .toList()
                : mascotas.stream()
                .map(Jaula::getID)
                .filter(t::encontrarIDMascotas)
                .toList();

        //filtra todas las mascotas con las ids pedidas
        JaulaState[] filtrado = mascotas.stream()
                .filter(m -> ids.contains(m.getID()))
                .map(JaulaState::toState)
                .toArray(JaulaState[]::new);

        handler.enviar(new V_ActualizarMascotas(filtrado));
    }

    public void contestarAgregarJaula(M_AgregarJaula e) {
        if (!t.comprarJaula(e.tipoContenedor)) {
            handler.enviar(new V_MostrarMensaje("No hay dinero suficiente"));
        } else {
            handler.enviar(new V_MostrarMensaje("Compra exitosa"));
            handler.enviar(new V_MostrarDinero(t.getDinero()));
            handler.enviar(new M_PedirMascotas(M_PedirMascotas.WILD));
        }
    }
}
