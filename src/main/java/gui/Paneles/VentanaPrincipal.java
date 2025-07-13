package gui.Paneles;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.V_CambiarVentana;
import Controladores.Eventos.Tipos.V_MostrarMensaje;
import gui.Paneles.PanelCompras.PanelCompras;
import gui.Paneles.PanelEmpleados.PanelEmpleados;
import gui.Paneles.PanelMascotas.PanelMascotas;
import gui.Paneles.PanelTienda.PanelTienda;

import javax.swing.*;
import java.awt.*;

/**
 * Esta es la ventana principal desde la que se muestran los paneles
 */
public class VentanaPrincipal extends JFrame implements Suscriptor, Publicador {
    static private final int ANCHO = 1000;
    static private final int ALTO = 850;
    private final PanelTienda PANEL_TIENDA;
    private final PanelMascotas PANEL_MASCOTAS;
    private final PanelEmpleados PANEL_EMPLEADOS;
    private final PanelCompras PANEL_COMPRAS;
    private JPanel panelPrincipal = null;

    public VentanaPrincipal(String titulo) {
        setTitle("Tienda");
        setSize(new Dimension(ANCHO, ALTO));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PANEL_TIENDA = new PanelTienda(titulo);
        PANEL_MASCOTAS = new PanelMascotas();
        PANEL_EMPLEADOS = new PanelEmpleados();
        PANEL_COMPRAS = new PanelCompras();

        setPanelPrincipal(Ventanas.TIENDA);

        add(panelPrincipal);

        setLocationRelativeTo(null);
    }

    /**
     * Se encarga de manejar los eventos recibidos
     * @param e
     */
    @Override
    public void recibir(Evento e) {
        switch (e.getTipo()) {
            case CambiarVentana -> setPanelPrincipal(((V_CambiarVentana) e).getVentana());
            case MostrarMensaje -> mostrarMensaje((V_MostrarMensaje) e);
        }
    }

    /**
     * Indica los tipos de datos que escucha la clase
     * @return un arreglo que india el tipo de evento que escucha la clase
     */
    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }

    /**
     * Reemplaza el panelPrincipal por aquel que indica el argumento
     * @param ventana: Indica cual es el panel que se desea mostrar
     */
    public void setPanelPrincipal(Ventanas ventana) {
        if (panelPrincipal != null) {
            remove(panelPrincipal);
        }

        panelPrincipal = switch (ventana) {
            case TIENDA -> PANEL_TIENDA;
            case MASCOTAS -> PANEL_MASCOTAS;
            case EMPLEADOS -> PANEL_EMPLEADOS;
            case COMPRAS -> PANEL_COMPRAS;
        };

        add(panelPrincipal);

        revalidate();
        repaint();
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    public void enviarHandler(EventHandler handler) {
        PANEL_TIENDA.enviarHandler(handler);
        PANEL_EMPLEADOS.enviarHandler(handler);
        PANEL_MASCOTAS.enviarHandler(handler);
        PANEL_COMPRAS.enviarHandler(handler);
        handler.suscribir(this);
    }

    /**
     * Muestra la ventana
     */
    public void mostrar() {
        setVisible(true);
    }

    /**
     * Muestra un mensaje cuando se envia el evento V_MostrarMensaje
     * @param evento
     */
    public void mostrarMensaje(V_MostrarMensaje evento) {
        JOptionPane.showMessageDialog(null, evento.mensaje);
    }
}
