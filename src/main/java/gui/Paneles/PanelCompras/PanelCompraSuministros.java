package gui.Paneles.PanelCompras;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Logica.Enums.Producto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel que muestra los suministros que el usuario puede comprar
 */
public class PanelCompraSuministros extends JPanel implements Publicador {
    private static final Color COLOR_DE_FONDO = new Color(200, 200, 200);
    private EventHandler handler;
    private final ArrayList<OpcionDeCompra> opciones = new ArrayList<>();

    public PanelCompraSuministros() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(COLOR_DE_FONDO);
        setLayout(new FlowLayout());

        Box box = Box.createVerticalBox();

        box.add(crearOpcion(Producto.Jabon, "/higiene/jabon.png"));

        box.add(Box.createRigidArea(new Dimension(0, 15)));

        box.add(crearOpcion(Producto.ComidaPerro, "/comida/comida_perro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.ComidaGato, "/comida/comida_gato.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.ComidaLoro, "/comida/comida_loro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.ComidaHamster, "/comida/comida_hamster.png"));

        box.add(Box.createRigidArea(new Dimension(0, 15)));

        box.add(crearOpcion(Producto.JuguetePerro, "/juguetes/juguete_perro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.JugueteGato, "/juguetes/juguete_gato.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.JugueteLoro, "/juguetes/juguete_loro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.JugueteHamster, "/juguetes/juguete_hamster.png"));

        box.add(Box.createRigidArea(new Dimension(0, 15)));

        box.add(crearOpcion(Producto.MedicamentoPerro, "/medicamentos/medicamento_perro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.MedicamentoGato, "/medicamentos/medicamento_gato.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.MedicamentoLoro, "/medicamentos/medicamento_loro.png"));
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(crearOpcion(Producto.MedicamentoHamster, "/medicamentos/medicamento_hamster.png"));

        add(box);
    }

    /**
     * Crea la opción de compra y la almacena en opciones para poder pasarles el eventHandler
     * @param producto: El producto que permite comprar
     * @param rutaImagen: Ruta a la imagen del producto
     * @return El objeto de tipo OpcionDeCompra
     */
    public OpcionDeCompra crearOpcion(Producto producto, String rutaImagen) {
        OpcionDeCompra op = new OpcionDeCompra(COLOR_DE_FONDO, producto, rutaImagen);
        opciones.add(op);
        return op;
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        for (OpcionDeCompra op : opciones) {
            op.enviarHandler(handler);
        }
    }
}
