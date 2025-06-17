package gui.Paneles.PanelMascotas;

import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;

public class PanelMascota extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    private final static int ANCHO = 300;
    private final static int ALTO = 700;

    public PanelMascota(VentanaPrincipal ventanaPrincipal, Color colorDeFondo, int i) {
        this.ventanaPrincipal = ventanaPrincipal;
        setBackground(colorDeFondo);
        setSize(new Dimension(ANCHO, ALTO));
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setMaximumSize(new Dimension(ANCHO, ALTO));
        setMinimumSize(new Dimension(ANCHO, ALTO));

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
}
