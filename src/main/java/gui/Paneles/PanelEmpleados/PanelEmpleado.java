package gui.Paneles.PanelEmpleados;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;

import javax.swing.*;
import java.awt.*;

public class PanelEmpleado extends JPanel implements Publicador {
    EventHandler handler;
    private final static int ANCHO = 300;
    private final static int ALTO = 700;

    public PanelEmpleado(Color colorDeFondo) {
        setBackground(colorDeFondo);
        Dimension tamanio = new Dimension(ANCHO, ALTO);
        setSize(tamanio);
        setPreferredSize(tamanio);
        setMaximumSize(tamanio);
        setMinimumSize(tamanio);

        Box box = Box.createVerticalBox();

        JPanel panelNombre = crearPanelNombre(colorDeFondo);

        ImagenEmpleado imagenEmpleado = new ImagenEmpleado();
        InformacionEmpleado informacionEmpleado = new InformacionEmpleado(ANCHO, colorDeFondo);

        box.add(panelNombre);
        box.add(imagenEmpleado);
        box.add(informacionEmpleado);

        add(box);
    }

    private static JPanel crearPanelNombre(Color colorDeFondo) {
        JPanel panelNombre = new JPanel();
        panelNombre.setBackground(Color.GREEN);
        panelNombre.setBackground(colorDeFondo);
        panelNombre.setPreferredSize(new Dimension(ANCHO, (int)(ALTO*0.05)));
        panelNombre.setMaximumSize(new Dimension(ANCHO+100, (int)(ALTO*0.05)));

        JLabel labelNombre = new JLabel("Nombre aquí", SwingConstants.CENTER);
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        labelNombre.setForeground(Color.WHITE);
        panelNombre.add(labelNombre);
        return panelNombre;
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
    }
}
