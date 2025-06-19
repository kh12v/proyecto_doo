package gui.Paneles;

import Controladores.Eventos.*;
import gui.Paneles.PanelCompras.PanelCompras;
import gui.Paneles.PanelEmpleados.PanelEmpleados;
import gui.Paneles.PanelMascotas.PanelMascotas;
import gui.Paneles.PanelTienda.PanelTienda;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Esta es la ventana principal desde la que se mostrarán los paneles
 */
public class VentanaPrincipal extends JFrame implements Suscriptor {
    static private final int ANCHO = 950;
    static private final int ALTO = 950;

    private JPanel panelPrincipal = null;

    private final PanelTienda PANEL_TIENDA;
    private final PanelMascotas PANEL_MASCOTAS;
    private final PanelEmpleados PANEL_EMPLEADOS;
     private final PanelCompras PANEL_COMPRAS;

    @Override
    public void recibir(Evento evento) {
        switch (evento.getTipo()){
            case CambiarVentana -> setPanelPrincipal(((V_CambiarVentanaEvento) evento).getVentana());
        }
    }

    @Override
    public ArrayList<DestinoEvento> getEventosEscuchados() {
        ArrayList<DestinoEvento> eventos = new ArrayList<>();
        eventos.add(DestinoEvento.Vista);
        return eventos;
    }

    public VentanaPrincipal(String titulo) {
        setTitle(titulo);
        setSize(new Dimension(ANCHO, ALTO));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PANEL_TIENDA = new PanelTienda();
        PANEL_MASCOTAS = new PanelMascotas();
        PANEL_EMPLEADOS = new PanelEmpleados();
        PANEL_COMPRAS = new PanelCompras();

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

        switch (ventana) {
            case TIENDA:
                panelPrincipal = PANEL_TIENDA;
                break;
            case MASCOTAS:
                panelPrincipal = PANEL_MASCOTAS;
                break;
            case PERSONAL:
                panelPrincipal = PANEL_EMPLEADOS;
                break;
            case COMPRAS:
                panelPrincipal = PANEL_COMPRAS;
                break;
        }

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
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void mostrar() {
        setVisible(true);
    }
}
