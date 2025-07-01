package gui.Paneles;

import Controladores.Eventos.*;
import Controladores.Eventos.Tipos.V_CambiarVentana;
import Controladores.Eventos.Tipos.V_MostrarMensaje;
import Logica.Tienda;
import gui.Paneles.PanelCompras.PanelCompras;
import gui.Paneles.PanelEmpleados.PanelEmpleados;
import gui.Paneles.PanelMascotas.PanelMascotas;
import gui.Paneles.PanelTienda.PanelTienda;

import javax.swing.*;
import java.awt.*;

/**
 * Esta es la ventana principal desde la que se mostrarán los paneles
 */
public class VentanaPrincipal extends JFrame implements Suscriptor, Publicador{
    static private final int ANCHO = 1000;
    static private final int ALTO = 850;

    private JPanel panelPrincipal = null;

    private final PanelTienda PANEL_TIENDA;
    private final PanelMascotas PANEL_MASCOTAS;
    private final PanelEmpleados PANEL_EMPLEADOS;
     private final PanelCompras PANEL_COMPRAS;

    @Override
    public void recibir(Evento evento) {
        switch (evento.getTipo()){
            case CambiarVentana -> setPanelPrincipal(((V_CambiarVentana) evento).getVentana());
            case MostrarMensaje -> mostrarMensaje((V_MostrarMensaje) evento);
        }
    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Vista};
    }

    public VentanaPrincipal(Tienda tienda, String titulo) {
        setTitle(titulo);
        setSize(new Dimension(ANCHO, ALTO));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PANEL_TIENDA = new PanelTienda(tienda);
        PANEL_MASCOTAS = new PanelMascotas(tienda);
        PANEL_EMPLEADOS = new PanelEmpleados(tienda);
        PANEL_COMPRAS = new PanelCompras(tienda);

        setPanelPrincipal(Ventanas.TIENDA);

        add(panelPrincipal);

        setLocationRelativeTo(null);
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

    public void enviarHandler(EventHandler handler) {
        PANEL_TIENDA.enviarHandler(handler);
        PANEL_EMPLEADOS.enviarHandler(handler);
        PANEL_MASCOTAS.enviarHandler(handler);
        PANEL_COMPRAS.enviarHandler(handler);
        handler.suscribir(this);
    }

    public void mostrar() {
        setVisible(true);
    }

    public void mostrarMensaje(V_MostrarMensaje evento) {
        JOptionPane.showMessageDialog(null, evento.mensaje);
    }
}
