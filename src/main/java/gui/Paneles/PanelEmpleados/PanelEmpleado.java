package gui.Paneles.PanelEmpleados;

import Controladores.Estado.EmpleadoState;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.M_DespedirEmpleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Muestra información sobre un empleado de la tienda
 */
public class PanelEmpleado extends JPanel implements Publicador {
    public final static int ANCHO = 300;
    public final static int ALTO = 350;
    EventHandler handler;
    PanelNombreEmpleado panelNombre;
    InformacionEmpleado informacionEmpleado;
    private EmpleadoState estado;

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

        panelNombre = new PanelNombreEmpleado(ANCHO, (int) (ALTO * 0.1), colorDeFondo, empleado);

        ImagenEmpleado imagenEmpleado = new ImagenEmpleado();
        informacionEmpleado = new InformacionEmpleado(ANCHO, colorDeFondo, empleado);

        addMouseListener(new MyMouseListener());

        box.add(panelNombre);
        box.add(imagenEmpleado);
        box.add(informacionEmpleado);

        add(box);
    }

    /**
     * Actualiza la información en pantalla del empleado cuando esta es modificada
     * @param estado: Contiene la nueva información del empleado
     */
    public void modificarPanel(EmpleadoState estado) {
        this.estado = estado;

        panelNombre.modificarEstado(estado);
        informacionEmpleado.modificarEstado(estado);
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
    }

    /**
     * El usuario puede seleccionar si desea despedir al empleado
     */
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
}
