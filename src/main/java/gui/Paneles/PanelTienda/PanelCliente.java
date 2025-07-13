package gui.Paneles.PanelTienda;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.M_PedirCalificacion;
import Controladores.Eventos.Tipos.V_ActualizarCalificacion;
import Controladores.Eventos.Tipos.V_ActualizarCliente;
import gui.Paneles.IndicadorDinero;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel que muestra un cliente
 */
public class PanelCliente extends JPanel implements Publicador, Suscriptor {
    private static final Color COLOR_DE_FONDO = new Color(87, 177, 230);
    JLabel calificacion;
    IndicadorDinero indicadorDinero;
    EventHandler handler;
    IconoCliente iconoCliente;
    public PanelCliente(String nombre) {
        setLayout(new BorderLayout());
        setBackground(COLOR_DE_FONDO);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        calificacion = new JLabel();
        calificacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        iconoCliente = new IconoCliente("/clientes/cliente.png", 300, 300);

        indicadorDinero = new IndicadorDinero();


        JLabel nombreTienda = new JLabel();
        nombreTienda.setText(nombre);
        nombreTienda.setFont(new Font("Tahoma", Font.BOLD, 25));
        nombreTienda.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelNorte = new JPanel();
        panelNorte.setBorder(new EmptyBorder(0, 10, 0, 10));
        panelNorte.setBackground(COLOR_DE_FONDO);
        panelNorte.setLayout(new BorderLayout());

        panelNorte.add(calificacion, BorderLayout.WEST);
        panelNorte.add(nombreTienda, BorderLayout.CENTER);
        panelNorte.add(indicadorDinero, BorderLayout.EAST);

        add(panelNorte, BorderLayout.NORTH);
        add(iconoCliente, BorderLayout.SOUTH);
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        handler.suscribir(this);
        handler.enviar(new M_PedirCalificacion());
        indicadorDinero.enviarHandler(handler);
    }

    /**
     * Maneja los eventos recibidos
     * @param e: Evento enviado
     */
    @Override
    public void recibir(Evento e) {
        switch (e.getTipo()) {
            case ActualizarCalificacion -> actualizarCalificacion((V_ActualizarCalificacion) e);
            case ActualizarCliente -> iconoCliente.cambiarIcono(((V_ActualizarCliente) e).getEspeciePedida());
        }
    }

    /**
     * Actualiza la calificación de la tienda
     * @param evento
     */
    private void actualizarCalificacion(V_ActualizarCalificacion evento) {
        calificacion.setText(String.format("Calificación: %.1f", evento.getNuevaCalificacion()));
    }

    /**
     * Indica el tipo de eventos que maneja esta clase
     * @return
     */
    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }
}
