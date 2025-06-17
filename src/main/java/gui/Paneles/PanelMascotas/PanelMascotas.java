package gui.Paneles.PanelMascotas;

import gui.Paneles.PanelTienda.PanelTienda;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMascotas extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

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

    public PanelMascotas(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.add(new BackButton(ventanaPrincipal), BorderLayout.WEST);
        add(panelNorte, BorderLayout.NORTH);

        add(new AlmacenMascotas(ventanaPrincipal));
    }
}
