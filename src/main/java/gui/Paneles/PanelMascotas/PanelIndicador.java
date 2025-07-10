package gui.Paneles.PanelMascotas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PanelIndicador extends JPanel {
    ArrayList<JProgressBar> indicadores;

    public PanelIndicador(int ancho, int[] estadoIndicadores) {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(new Color(0, 0, 0, 0));
        indicadores = new ArrayList<>();
        Box box = Box.createVerticalBox();
        String[] nombresIndicadores = new String[]{"Hambre", "Salud", "Felicidad", "Higiene"};
        for (int i = 0; i < 4; i++) {
            indicadores.add(crearIndicador(ancho, nombresIndicadores[i], estadoIndicadores[i]));
        }
        indicadores.forEach(box::add);
        add(box);
    }

    private JProgressBar crearIndicador(int ancho, String texto, int progreso) {
        JProgressBar indicador = new JProgressBar(0, 100);
        indicador.setPreferredSize(new Dimension(ancho, 25));
        indicador.setValue(progreso);
        indicador.setStringPainted(true);
        indicador.setString(texto);
        indicador.setBorder(new EmptyBorder(0, 0, 0, 0));

        return indicador;
    }

    public void modificarPanel(int[] estadoIndicadores) {
        if (estadoIndicadores.length == 0) {
            modificarPanel(new int[]{0,0,0,0});

        } else for (int i = 0; i < 4; i++) {
            indicadores.get(i).setValue(estadoIndicadores[i]);
        }
    }
}
