package gui.Paneles.PanelMascotas;

import Controladores.Eventos.EventHandler;
import javax.swing.*;
import java.awt.*;

public class PanelMascota extends JPanel {
    EventHandler handler;
    public final static int ANCHO = 300;
    public final static int ALTO = 400;

    public PanelMascota(Color colorDeFondo) {
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

        Jaula jaula = new Jaula(false, Jaula.Animal.PERRO);
        PanelIndicador panelIndicador = new PanelIndicador(ANCHO, colorDeFondo);

        box.add(panelNombre);
        box.add(jaula);
        box.add(panelIndicador);

        add(box);
    }
    
    public void enviarHandler(EventHandler handler){
        this.handler = handler;
    }
}
