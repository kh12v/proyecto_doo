package gui.Paneles.PanelTienda;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirCalificacion;
import Controladores.Eventos.Tipos.V_ActualizarCalificacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCliente extends JPanel implements Publicador, Suscriptor {
    private static final Color COLOR_DE_FONDO = new Color(87, 177, 230);
    JLabel calificacion;
    EventHandler handler;

    public PanelCliente() {
        setLayout(new BorderLayout());
        setBackground(COLOR_DE_FONDO);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        calificacion = new JLabel();
        calificacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        IconoCliente iconoCliente = new IconoCliente("recursos/cliente.png", 300, 300, COLOR_DE_FONDO);
        add(calificacion, BorderLayout.NORTH);
        add(iconoCliente, BorderLayout.SOUTH);
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
        handler.enviar(new M_PedirCalificacion());
    }

    @Override
    public void recibir(Evento evento) {
        switch (evento.getTipo()){
            case ActualizarCalificacion -> actualizarCalificacion((V_ActualizarCalificacion) evento);
        }
    }

    private void actualizarCalificacion(V_ActualizarCalificacion evento) {
        calificacion.setText(String.format("Calificación: %.1f",evento.getNuevaCalificacion()));
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }
}
