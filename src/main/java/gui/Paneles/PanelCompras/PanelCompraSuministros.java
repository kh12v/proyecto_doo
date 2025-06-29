package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Logica.Producto;
import gui.Paneles.BordeRedondo;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PanelCompraSuministros extends JPanel implements Publicador {
    private EventHandler handler;
    private ArrayList<OpcionDeCompra> opciones = new ArrayList<>();

    private static final Color COLOR_DE_FONDO = new Color(200, 200, 200);

    public PanelCompraSuministros() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(COLOR_DE_FONDO);
        setLayout(new FlowLayout());

        Box box = Box.createVerticalBox();

        box.add(crearOpcion(Producto.JuguetePerro, "recursos/juguetes/juguete_perro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.JugueteGato, "recursos/juguetes/juguete_gato.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.JugueteLoro, "recursos/juguetes/juguete_loro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.Hamster, "recursos/juguetes/juguete_hamster.png"));

        box.add(Box.createRigidArea(new Dimension(0, 15)));

        box.add(crearOpcion(Producto.MedicamentoPerro, "recursos/medicamentos/medicamento_perro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.MedicamentoGato, "recursos/medicamentos/medicamento_gato.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.MedicamentoLoro, "recursos/medicamentos/medicamento_loro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.MedicamentoHamster, "recursos/medicamentos/medicamento_hamster.png"));

        add(box);
    }

    public OpcionDeCompra crearOpcion(Producto producto, String rutaImagen) {
        OpcionDeCompra op = new OpcionDeCompra(COLOR_DE_FONDO, producto, rutaImagen);
        opciones.add(op);
        return op;
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
        for (OpcionDeCompra op : opciones) {
            op.enviarHandler(handler);
        }
    }
}
