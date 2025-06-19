package gui.Paneles.PanelMascotas;

import Controladores.Estado.MascotaState;
import Controladores.Eventos.EventHandler;
import Logica.Especies;

import javax.swing.*;
import java.awt.*;

public class PanelMascota extends JPanel {
    EventHandler handler;
    Jaula jaula;
    public final static int ANCHO = 300;
    public final static int ALTO = 400;

    public PanelMascota(Color colorDeFondo, Especies especie) {
        setBackground(colorDeFondo);
        Dimension tamanio = new Dimension(ANCHO, ALTO);
        setPreferredSize(tamanio);

        Box box = Box.createVerticalBox();

        JPanel panelNombre = new JPanel();
        panelNombre.setBackground(colorDeFondo);
        panelNombre.setPreferredSize(new Dimension(ANCHO, (int)(ALTO*0.05)));
        panelNombre.setMaximumSize(new Dimension(ANCHO, (int)(ALTO*0.05)));

        JLabel labelNombre = new JLabel("Nombre aquí", SwingConstants.LEFT);
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        labelNombre.setForeground(Color.WHITE);
        panelNombre.add(labelNombre);

        jaula = new Jaula(false, especie);
        PanelIndicador panelIndicador = new PanelIndicador(ANCHO, colorDeFondo);

        box.add(panelNombre);
        box.add(jaula);
        box.add(panelIndicador);

        add(box);
    }

    public void modificarPanel(MascotaState estado){
        jaula.modificarJaula(estado);
    }
    
    public void enviarHandler(EventHandler handler){
        this.handler = handler;
    }
}
