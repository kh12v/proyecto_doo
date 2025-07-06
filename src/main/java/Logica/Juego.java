package Logica;

import Controladores.ControladorPrincipal;
import Controladores.Eventos.EventHandler;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Juego implements Actualizable {
    ControladorPrincipal controlador;
    VentanaPrincipal ventanaPrincipal;
    EventHandler eventHandler;

    public Juego(int dineroInicial) {
        VentanaNombre ventanaNombre = new VentanaNombre();
        while(ventanaNombre.getTexto() == null){
            Thread.onSpinWait();
        }
        String nombreTienda = ventanaNombre.getTexto();
        ventanaNombre.dispose();
        eventHandler = new EventHandler();
        Tienda tienda = new Tienda(nombreTienda,dineroInicial);
        controlador = new ControladorPrincipal(tienda);
        ventanaPrincipal = new VentanaPrincipal(nombreTienda);
        controlador.enviarHandler(eventHandler);
        ventanaPrincipal.enviarHandler(eventHandler);
    }

    public void iniciar() {
        ventanaPrincipal.mostrar();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                actualizar();
            }
        };
        timer.schedule(timerTask, Date.from(Instant.now()), 10000);
    }

    @Override
    public void actualizar() {
        controlador.actualizar();
        System.out.println(Instant.now());
    }
}

class VentanaNombre extends JFrame {
    JTextArea textArea;
    JButton boton;
    String textoActual = null;
    public VentanaNombre(){
        setSize(400,100);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);
        textArea = new JTextArea();
        // TODO quitar comic sans y elegir una fuente decente :)
        textArea.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        boton = new JButton("Confirmar");
        boton.addActionListener(e->textoActual = textArea.getText());
        JLabel titulo = new JLabel("Ingrese el nombre de su tienda");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setHorizontalTextPosition(SwingConstants.CENTER);
        panel.add(boton,BorderLayout.SOUTH);
        panel.add(titulo,BorderLayout.NORTH);
        panel.add(textArea,BorderLayout.CENTER);
        setVisible(true);
    }

    public String getTexto() {
        return textoActual;
    }

    @Override
    public void setVisible(boolean visible) {
        if(textoActual == null && !visible){
            textoActual = "DEFAULT";
        }
        super.setVisible(visible);
    }
}
