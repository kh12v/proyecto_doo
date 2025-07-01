package gui.Paneles.PanelEmpleados;

import Controladores.Estado.EmpleadoState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InformacionEmpleado extends JPanel {
    JLabel labelCargo;
    JLabel labelSalario;

    public InformacionEmpleado(int ANCHO, Color colorDeFondo, EmpleadoState estado) {
        setBackground(colorDeFondo);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(ANCHO, 100));

        Box box = Box.createVerticalBox();

        labelCargo = new JLabel("Cargo: " + estado.cargo().toString());
        labelCargo.setFont(new Font("Arial", Font.PLAIN, 14));
        labelCargo.setForeground(Color.WHITE);

        labelSalario = new JLabel("Salario: $" + estado.salario());
        labelSalario.setFont(new Font("Arial", Font.PLAIN, 14));
        labelSalario.setForeground(Color.WHITE);

        box.add(labelCargo);
        box.add(Box.createRigidArea(new Dimension(0, 7)));
        box.add(labelSalario);

        add(box);
    }

    public void modificarEstado(EmpleadoState estado) {
        labelCargo.setText("Cargo: " + estado.cargo().toString());
        labelSalario.setText("Salario: $" + estado.salario());
    }
}
