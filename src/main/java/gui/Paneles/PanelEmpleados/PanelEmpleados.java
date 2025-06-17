package gui.Paneles.PanelEmpleados;

import gui.Paneles.PanelMascotas.AlmacenMascotas;
import gui.Paneles.PanelMascotas.PanelMascotas;
import gui.Paneles.PanelTienda.PanelTienda;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEmpleados extends JPanel {
    VentanaPrincipal ventanaPrincipal;

    private static class BackButton extends JButton {
        public BackButton(VentanaPrincipal ventanaPrincipal) {
            setText("<--  Volver");
            setPreferredSize(new Dimension(125, 55));
            setMaximumSize(new Dimension(125, 55));
            setMinimumSize(new Dimension(125, 55));
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ventanaPrincipal.setPanelPrincipal(VentanaPrincipal.PANELES.PANEL_TIENDA);
                }
            });
        }
    }

    public PanelEmpleados(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(new PanelEmpleados.BackButton(ventanaPrincipal), BorderLayout.WEST);
        add(panelNorte, BorderLayout.NORTH);

        add(new PlanillaEmpleados(ventanaPrincipal));
    }
}
