package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import gui.Paneles.BordeRedondo;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCompraSuministros extends JPanel {
    private EventHandler handler;

    private static final Color COLOR_DE_FONDO = new Color(200, 200, 200);

    public PanelCompraSuministros() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(COLOR_DE_FONDO);
        setLayout(new FlowLayout());

        Box box = Box.createVerticalBox();

        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/juguetes/juguete_gato.png", "Juguete para gatos", 1000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/juguetes/juguete_perro.png", "Juguete para perros", 2000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/juguetes/juguete_loro.png", "Juguete para loros", 3000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/juguetes/juguete_hamster.png", "Juguete para hamsters", 4000));

        box.add(Box.createRigidArea(new Dimension(0, 15)));

        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/medicamentos/medicamento_gato.png", "Medicamento para gatos", 1000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/medicamentos/medicamento_perro.png", "Medicamento para perros", 2000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/medicamentos/medicamento_loro.png", "Medicamento para loros", 3000));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(new OpcionDeCompra(COLOR_DE_FONDO, "recursos/medicamentos/medicamento_hamster.png", "Medicamento para hamsters", 4000));


        add(box);
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
    }
}
