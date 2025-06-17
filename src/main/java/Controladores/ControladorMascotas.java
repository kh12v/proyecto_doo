package Controladores;

import Controladores.Eventos.Evento;
import Controladores.Eventos.Suscriptor;

public class ControladorMascotas implements Suscriptor {

    @Override
    public void recibir(Evento e){
        switch (e.getTipo()){
            case ActualizarMascotas: System.out.print(2);
            break;

        }
    }
}
