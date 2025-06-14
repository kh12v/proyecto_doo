package gui.ventanas;

import javax.swing.*;
import java.awt.*;

/**
 * Clase genérica para crear una ventana, pese a que puede ser utilizada
 * por si sola es recomendado utilizarla como clase padre para crear
 * una ventana más compleja
 */
public class Ventana extends JFrame {
    public Ventana(String titulo, int ancho, int alto, boolean exit_on_close) {
        setTitle(titulo);
        setSize(new Dimension(ancho, alto));
        if (exit_on_close) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        setLocationRelativeTo(null);
    }

    void mostrar() {
        setVisible(true);
    }
}
