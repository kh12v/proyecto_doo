package gui.Paneles.PanelMascotas;

import Controladores.Estado.JaulaState;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que muestra una mascota de la tienda
 */
public class PanelMascota extends JPanel {
    public final static int ANCHO = 300;
    public final static int ALTO = 350;
    EventHandler handler;
    PanelIndicador panelIndicador;
    JaulaPanel jaulaPanel;
    JLabel labelNombre;

    public PanelMascota(JaulaState jaula) {
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        labelNombre = new JLabel(jaula.mascotaState().nombre(), SwingConstants.CENTER);
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        labelNombre.setForeground(Color.WHITE);
        labelNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelNombre);

        jaulaPanel = new JaulaPanel(jaula);
        add(labelNombre);
        add(jaulaPanel);
        if (jaula.vacia()) {
            panelIndicador = new PanelIndicador(ANCHO, new int[]{0, 0, 0, 0});
        } else {
            panelIndicador = new PanelIndicador(ANCHO, jaula.getIndicadores());
        }
        add(panelIndicador);
    }

    /**
     * Modifica la información en pantalla de la mascota si es que hubo un cambio
     * @param estado: Nueva información de la mascota
     */
    public void modificarPanel(JaulaState estado) {
        jaulaPanel.modificarJaula(estado);
        panelIndicador.modificarPanel(estado.getIndicadores());
        labelNombre.setText(estado.mascotaState().nombre());
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
    }

    /**
     * Actualizar los indicadores
     * @param indicadores: Nueva información de los indicadores
     */
    public void actualizarIndicadores(int[] indicadores) {
        panelIndicador.modificarPanel(indicadores);
    }
}
