package Controladores.Eventos;

import Logica.Mascota;

public class M_ActualizarMascotasEvento extends Evento{
    Mascota mascota;
    public M_ActualizarMascotasEvento(Mascota mascota) {
        super(TipoEvento.ActualizarMascotas);
        this.mascota = mascota;
    }
}
