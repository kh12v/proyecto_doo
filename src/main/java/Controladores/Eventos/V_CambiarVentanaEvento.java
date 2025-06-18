package Controladores.Eventos;

import gui.Paneles.Ventanas;

public class V_CambiarVentanaEvento extends Evento {
    Ventanas ventana;

    public V_CambiarVentanaEvento(Ventanas ventana) {
        super(TipoEvento.CambiarVentana, DestinoEvento.Vista);
        this.ventana = ventana;
    }

    public Ventanas getVentana() {
        return ventana;
    }
}
