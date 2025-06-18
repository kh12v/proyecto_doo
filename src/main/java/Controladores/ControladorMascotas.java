package Controladores;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.Suscriptor;

import java.util.ArrayList;

public class ControladorMascotas implements Suscriptor {

    @Override
    public ArrayList<DestinoEvento> getEventosEscuchados() {
        ArrayList<DestinoEvento> eventos = new ArrayList<>();
        eventos.add(DestinoEvento.Controlador);
        return eventos;
    }

    @Override
    public void recibir(Evento e){
        switch (e.getTipo()){
            case ActualizarMascotas: System.out.print(2);
            break;

        }
    }
}
