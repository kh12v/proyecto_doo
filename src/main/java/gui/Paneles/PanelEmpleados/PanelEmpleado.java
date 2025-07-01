package gui.Paneles.PanelEmpleados;

import Controladores.Estado.EmpleadoState;
import Controladores.Estado.JaulaState;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.M_DespedirEmpleado;
import gui.Paneles.PanelMascotas.JaulaPanel;
import gui.Paneles.PanelMascotas.PanelIndicador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelEmpleado extends JPanel implements Publicador {
    EventHandler handler;
    PanelNombreEmpleado panelNombre;
    InformacionEmpleado informacionEmpleado;
    public final static int ANCHO = 300;
    public final static int ALTO = 350;

    private EmpleadoState estado;

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int resultado = JOptionPane.showConfirmDialog(
                    null,
                    "¿Desea despedir a" + estado.nombre() + "?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (resultado == JOptionPane.YES_OPTION) {
                handler.enviar(new M_DespedirEmpleado(estado));
            }
        }
    }

    public PanelEmpleado(Color colorDeFondo, EmpleadoState empleado) {
        this.estado = empleado;

        setBackground(Color.GRAY);
        Dimension tamanio = new Dimension(ANCHO, ALTO);
        setSize(tamanio);
        setPreferredSize(tamanio);
        setMaximumSize(tamanio);
        setMinimumSize(tamanio);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        Box box = Box.createVerticalBox();

        panelNombre = new PanelNombreEmpleado(ANCHO, (int)(ALTO*0.1), colorDeFondo, empleado);

        ImagenEmpleado imagenEmpleado = new ImagenEmpleado();
        informacionEmpleado = new InformacionEmpleado(ANCHO, colorDeFondo, empleado);

        addMouseListener(new MyMouseListener());

        box.add(panelNombre);
        box.add(imagenEmpleado);
        box.add(informacionEmpleado);

        add(box);
    }

    public void modificarPanel(EmpleadoState estado){
        this.estado = estado;

        panelNombre.modificarEstado(estado);
        informacionEmpleado.modificarEstado(estado);
    }

    public void enviarHandler(EventHandler handler){
        this.handler = handler;
    }
}
