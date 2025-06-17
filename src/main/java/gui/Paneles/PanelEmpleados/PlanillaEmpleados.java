package gui.Paneles.PanelEmpleados;

import gui.Paneles.PanelMascotas.PanelMascota;
import gui.Paneles.VentanaPrincipal;
import javafx.embed.swing.JFXPanel;

import java.awt.*;

public class PlanillaEmpleados extends JFXPanel {
    VentanaPrincipal ventanaPrincipal;

    private final static Color COLOR_DE_FONDO = Color.GRAY;

    public PlanillaEmpleados(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        setBackground(COLOR_DE_FONDO);
        setLayout(new GridLayout(0, 3));

        for (int i = 0; i < 3; i++) {
            add(new PanelEmpleado(ventanaPrincipal, COLOR_DE_FONDO));
        }
    }
}
