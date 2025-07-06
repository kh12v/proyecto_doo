package Controladores;

import Controladores.Estado.JaulaState;
import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Controladores.Eventos.Tipos.M_PedirCliente;
import Controladores.Eventos.Tipos.*;
import Logica.Jaula;
import Logica.Tienda;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ControladorMascotas implements Controlador {
    EventHandler handler;
    Tienda t;

    public ControladorMascotas(Tienda t) {
        this.t = t;
    }

    public void enviarHandler(EventHandler handler) {
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
            case PedirIndicadores -> contestarPeticionIndicadores((M_PedirIndicadoresMascotas) e);
            case AgregarJaula -> contestarAgregarJaula((M_AgregarJaula) e);
            case EntregarMascota -> contestarEntregaMascotas((M_EntregarMascota) e);
        }
    }

    private void contestarEntregaMascotas(M_EntregarMascota e) {
        if(t.servirCliente(e.getId())){
            handler.enviar(new V_MostrarMensaje("¡Cliente servido exitosamente!"));
            handler.enviar(new M_PedirMascotas(new int[]{e.getId()}));
            handler.enviar(new M_PedirCliente());
            handler.enviar(new M_PedirDinero());
            handler.enviar(new M_PedirCalificacion());
        } else {
            handler.enviar(new V_MostrarMensaje("¡El cliente no aceptó la mascota!"));
        }
    }

    private void contestarPeticionIndicadores(M_PedirIndicadoresMascotas e) {
        List<Jaula> mascotas = t.getJaulas();
        // lo siento mucho, si quieres hacer un for loop esta bien
        Map<Integer, int[]> indicadores = (e.getIds() == M_PedirIndicadoresMascotas.WILD)
                ? mascotas.stream()
                .filter(Predicate.not(Jaula::estaVacia))
                .collect(Collectors.toMap(Jaula::getID, Jaula::getIndicadores))
                : mascotas.stream()
                .filter(Predicate.not(Jaula::estaVacia))
                .filter(jaula -> Arrays.stream(e.getIds()).anyMatch(i -> i == jaula.getID()))
                .collect(Collectors.toMap(Jaula::getID, Jaula::getIndicadores));

        handler.enviar(new V_ActualizarIndicadoresMascotas(new HashMap<>(indicadores)));
    }

    public void contestarPeticionMascotas(M_PedirMascotas evento) {
        // me gusta asi con el ternario, pero si no te gusta lo puedes cambiar por un if else
        int[] ids = (evento.getIDs() == M_PedirMascotas.WILD)
                ? t.getJaulas().stream().mapToInt(Jaula::getID).toArray()
                : evento.getIDs();

        ArrayList<Jaula> jaulas = t.getJaulas();

        JaulaState[] jaulasEncontradas = jaulas.stream()
                .filter(jaula -> Arrays.stream(ids).anyMatch(i -> i == jaula.getID()))
                .map(JaulaState::toState).toArray(JaulaState[]::new);

        handler.enviar(new V_ActualizarMascotas(jaulasEncontradas));
    }

    public void contestarAgregarJaula(M_AgregarJaula e) {
        int id = t.comprarJaula(e.tipoContenedor);
        if (id < 0) {
            handler.enviar(new V_MostrarMensaje("No hay dinero suficiente"));
        } else {
            handler.enviar(new V_MostrarMensaje("Compra exitosa"));
            handler.enviar(new V_MostrarDinero(t.getDinero()));
            handler.enviar(new M_PedirMascotas(new int[]{id}));
        }
    }
}
