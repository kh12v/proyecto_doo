package gui.Paneles.PanelEmpleados;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InformacionEmpleado extends JPanel {
    public InformacionEmpleado(int ANCHO, Color colorDeFondo) {
        setBackground(colorDeFondo);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(ANCHO, 100));

        Box box = Box.createVerticalBox();

        JLabel labelCargo = new JLabel("Cargo: Cuidador");
        labelCargo.setFont(new Font("Arial", Font.PLAIN, 14));
        labelCargo.setForeground(Color.WHITE);

        JLabel labelSalario = new JLabel("Salario: $500");
        labelSalario.setFont(new Font("Arial", Font.PLAIN, 14));
        labelSalario.setForeground(Color.WHITE);

        box.add(labelCargo);
        box.add(Box.createRigidArea(new Dimension(0, 7)));
        box.add(labelSalario);

        add(box);
    }
}
