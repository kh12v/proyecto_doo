package gui.Paneles.PanelEmpleados;

import Controladores.Estado.EmpleadoState;

import javax.swing.*;
import java.awt.*;

/**
 * Muestra el nombre de un empleado, se actualiza en caso de ser necesario
 */
public class PanelNombreEmpleado extends JPanel {
    private final JLabel labelNombre;

    public PanelNombreEmpleado(int ancho, int alto, Color colorDeFondo, EmpleadoState estado) {
        setBackground(colorDeFondo);
        setPreferredSize(new Dimension(ancho, alto));
        setMaximumSize(new Dimension(ancho + 100, alto));

        labelNombre = new JLabel(estado.nombre(), SwingConstants.CENTER);
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        labelNombre.setForeground(Color.WHITE);

        add(labelNombre);
    }

    /**
     * Modifica el nombre del empleado si es que esta cambia
     * @param estado: Contiene la nueva información del empleado
     */
    public void modificarEstado(EmpleadoState estado) {
        labelNombre.setText(estado.nombre());
    }
}
