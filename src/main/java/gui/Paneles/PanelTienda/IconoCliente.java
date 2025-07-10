package gui.Paneles.PanelTienda;

import Logica.Enums.Especie;
import gui.Paneles.ImageLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class IconoCliente extends JPanel {
    JPanel icono = new JPanel();
    private final int ancho,alto;
    public IconoCliente(String ruta, int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        setBorder(new EmptyBorder(0, 0, 0, 0));
        ImageLoader loader = ImageLoader.getInstancia();
        try {
            icono = loader.cargarImagenEscalada(ancho, alto, ruta);
            add(icono, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }

    public void cambiarIcono(Especie especie){
        System.out.println(especie+"cambiada");
        ImageLoader loader = ImageLoader.getInstancia();
        String ruta = switch (especie){
            case Null -> "/clientes/cliente.png";
            default -> "/clientes/cliente_" + especie + ".png";
        };
        try{
            icono = loader.cargarImagenEscalada(ancho,alto,ruta);
            removeAll();
            add(icono, BorderLayout.CENTER);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
