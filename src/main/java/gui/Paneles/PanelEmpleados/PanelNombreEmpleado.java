package gui.Paneles.PanelEmpleados;

import Controladores.Estado.EmpleadoState;

import javax.swing.*;
import java.awt.*;

public class PanelNombreEmpleado extends JPanel {
    private final JLabel labelNombre;

    public PanelNombreEmpleado(int ancho, int alto, Color colorDeFondo, EmpleadoState estado) {
        setBackground(colorDeFondo);
        setPreferredSize(new Dimension(ancho, alto));
        setMaximumSize(new Dimension(ancho+100, alto));

        labelNombre = new JLabel(estado.nombre(), SwingConstants.CENTER);
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        labelNombre.setForeground(Color.WHITE);

        add(labelNombre);
    }

    public void modificarEstado(EmpleadoState estado) {
        labelNombre.setText(estado.nombre());
    }
}
