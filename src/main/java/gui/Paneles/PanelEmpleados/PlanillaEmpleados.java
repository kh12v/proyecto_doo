package gui.Paneles.PanelEmpleados;

import Controladores.Eventos.EventHandler;
import javafx.embed.swing.JFXPanel;

import java.awt.*;
import java.util.ArrayList;

public class PlanillaEmpleados extends JFXPanel {
    EventHandler handler;
    ArrayList<PanelEmpleado> empleados;

    private final static Color COLOR_DE_FONDO = Color.GRAY;

    public PlanillaEmpleados() {
        empleados = new ArrayList<>();
        setBackground(COLOR_DE_FONDO);
        setLayout(new GridLayout(0, 3));

        for (int i = 0; i < 3; i++) {
            PanelEmpleado empleado = new PanelEmpleado(COLOR_DE_FONDO);
            add(empleado);
            empleados.add(empleado);
        }
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        empleados.forEach(empleado -> empleado.enviarHandler(handler));
    }
}
