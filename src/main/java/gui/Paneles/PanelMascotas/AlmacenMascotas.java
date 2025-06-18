package gui.Paneles.PanelMascotas;

import Controladores.Eventos.EventHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlmacenMascotas extends JPanel {
    private EventHandler handler;
    private final ArrayList<PanelMascota> mascotas;

    public AlmacenMascotas() {
        mascotas = new ArrayList<>();
        setBackground(Color.GRAY);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(900, 450*9/3));

        for (int i = 0; i < 9; i++) {
            PanelMascota mascota = new PanelMascota(Color.GRAY);
            mascotas.add(mascota);
            add(mascota);
        }
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        mascotas.forEach(m -> m.enviarHandler(handler));

    }
}
