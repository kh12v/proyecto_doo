package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import gui.Paneles.Ventanas;

/**
 * Pide a la ventana principal cambiar el panel mostrado actualmente
 */
public class V_CambiarVentana extends Evento {
    Ventanas ventana;

    public V_CambiarVentana(Ventanas ventana) {
        super(TipoEvento.CambiarVentana, DestinoEvento.Vista);
        this.ventana = ventana;
    }

    public Ventanas getVentana() {
        return ventana;
    }
}
