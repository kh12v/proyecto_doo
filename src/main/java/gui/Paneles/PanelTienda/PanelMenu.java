package gui.Paneles.PanelTienda;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import gui.Paneles.BotonVentana;
import gui.Paneles.Ventanas;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * En este panel el usuario puede acceder a los otros menus de la simulación
 */
public class PanelMenu extends JPanel implements Publicador {
    private static final Color COLOR_DE_FONDO = Color.GRAY;
    public EventHandler handler;
    private BotonVentana[] ventana;
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        Arrays.stream(ventana).forEach(v -> v.enviarHandler(handler));
    }



    public PanelMenu() {
        setLayout(new BorderLayout());
        setBackground(COLOR_DE_FONDO);

        Box box = Box.createHorizontalBox();

        ventana = new BotonVentana[3];
        box.add(Box.createHorizontalGlue());
        BotonVentana botonMascota = new BotonVentana("Mascotas", Ventanas.MASCOTAS);
        box.add(botonMascota);
        ventana[0] = botonMascota;
        box.add(Box.createHorizontalGlue());
        BotonVentana botonEmpleados = new BotonVentana("Empleados", Ventanas.EMPLEADOS  );
        box.add(botonEmpleados);
        ventana[1] = botonEmpleados;
        box.add(Box.createHorizontalGlue());
        BotonVentana botonComprar = new BotonVentana("Comprar", Ventanas.COMPRAS);
        box.add(botonComprar);
        ventana[2] = botonComprar;
        box.add(Box.createHorizontalGlue());
        add(box, BorderLayout.CENTER);
    }
}
