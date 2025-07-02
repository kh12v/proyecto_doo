package gui.Paneles.PanelTienda;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirCalificacion;
import Controladores.Eventos.Tipos.V_ActualizarCalificacion;
import Logica.Tienda;
import gui.Paneles.IndicadorDinero;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCliente extends JPanel implements Publicador, Suscriptor {
    private static final Color COLOR_DE_FONDO = new Color(87, 177, 230);
    JLabel calificacion;
    IndicadorDinero indicadorDinero;
    EventHandler handler;

    public PanelCliente() {
        setLayout(new BorderLayout());
        setBackground(COLOR_DE_FONDO);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        calificacion = new JLabel();
        calificacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        IconoCliente iconoCliente = new IconoCliente("recursos/cliente.png", 300, 300, COLOR_DE_FONDO);

        indicadorDinero = new IndicadorDinero();

        JPanel panelNorte = new JPanel();
        panelNorte.setBorder(new EmptyBorder(0, 10, 0, 10));
        panelNorte.setBackground(COLOR_DE_FONDO);
        panelNorte.setLayout(new BorderLayout());

        panelNorte.add(calificacion, BorderLayout.WEST);
        panelNorte.add(indicadorDinero, BorderLayout.EAST);

        add(panelNorte, BorderLayout.NORTH);
        add(iconoCliente, BorderLayout.SOUTH);
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
        handler.enviar(new M_PedirCalificacion());
        indicadorDinero.enviarHandler(handler);
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
