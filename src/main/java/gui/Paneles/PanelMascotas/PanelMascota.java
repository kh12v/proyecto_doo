package gui.Paneles.PanelMascotas;

import Controladores.Estado.MascotaState;
import Controladores.Eventos.EventHandler;

import javax.swing.*;
import java.awt.*;

public class PanelMascota extends JPanel {
    EventHandler handler;
    Jaula jaula;
    JLabel labelNombre;
    public final static int ANCHO = 300;
    public final static int ALTO = 350;

    public PanelMascota(MascotaState mascota) {
        setBackground(new Color(0,0,0,0));
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        labelNombre = new JLabel(mascota.nombre(), SwingConstants.CENTER);
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        labelNombre.setForeground(Color.WHITE);
        labelNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelNombre);

        jaula = new Jaula(mascota.especie());
        PanelIndicador panelIndicador = new PanelIndicador(ANCHO);

        add(labelNombre);
        add(jaula);
        add(panelIndicador);
    }

    public void modificarPanel(MascotaState estado){
        jaula.modificarJaula(estado);
        labelNombre.setText(estado.nombre());
    }
    
    public void enviarHandler(EventHandler handler){
        this.handler = handler;
    }
}
