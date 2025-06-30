package gui.Paneles.PanelMascotas;

import Controladores.Estado.JaulaState;
import Controladores.Estado.MascotaState;
import Controladores.Eventos.EventHandler;

import javax.swing.*;
import java.awt.*;

public class PanelMascota extends JPanel {
    EventHandler handler;
    PanelIndicador panelIndicador;
    JaulaPanel jaulaPanel;
    JLabel labelNombre;
    public final static int ANCHO = 300;
    public final static int ALTO = 350;

    public PanelMascota(JaulaState jaula) {
        setBackground(new Color(0,0,0,0));
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        labelNombre = new JLabel(jaula.mascotaState().nombre(), SwingConstants.CENTER);
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        labelNombre.setForeground(Color.WHITE);
        labelNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelNombre);

        jaulaPanel = new JaulaPanel(jaula.mascotaState().especie());
        add(labelNombre);
        add(jaulaPanel);
        if(jaula.vacia()){
            panelIndicador = new PanelIndicador(ANCHO,new int[]{0,0,0,0});
        } else {
            panelIndicador = new PanelIndicador(ANCHO,jaula.getIndicadores());
        }
        add(panelIndicador);
    }

    public void modificarPanel(JaulaState estado){
        jaulaPanel.modificarJaula(estado);
        panelIndicador.modificarPanel(estado.getIndicadores());
        labelNombre.setText(estado.mascotaState().nombre());
    }
    
    public void enviarHandler(EventHandler handler){
        this.handler = handler;
    }
}
