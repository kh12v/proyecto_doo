package gui.Paneles;

import gui.Paneles.PanelCompras.PanelCompras;
import gui.Paneles.PanelEmpleados.PanelEmpleados;
import gui.Paneles.PanelMascotas.PanelMascotas;
import gui.Paneles.PanelTienda.PanelTienda;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Esta es la ventana principal desde la que se mostrarán los paneles
 */
public class VentanaPrincipal extends JFrame {
    static private final int ANCHO = 950;
    static private final int ALTO = 950;

    private JPanel panelPrincipal = null;
    private JPanel panelConBoton = null;

    private final PanelTienda PANEL_TIENDA;
    private final PanelMascotas PANEL_MASCOTAS;
    private final PanelEmpleados PANEL_EMPLEADOS;
    private final PanelCompras PANEL_COMPRAS;

    public enum PANELES {
        PANEL_TIENDA, PANEL_MASCOTAS, PANEL_EMPLEADOS, PANEL_COMPRAS
    }

    private static class BotonVolver extends JButton {
        public BotonVolver(VentanaPrincipal ventanaPrincipal) {
            setText("<<  Volver");
            setPreferredSize(new Dimension(125, 55));
            setMaximumSize(new Dimension(125, 55));
            setMinimumSize(new Dimension(125, 55));
            addActionListener(e -> ventanaPrincipal.setPanelPrincipal(PANELES.PANEL_TIENDA));
        }
    }

    public VentanaPrincipal(String titulo) {
        setTitle(titulo);
        setSize(new Dimension(ANCHO, ALTO));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PANEL_TIENDA = new PanelTienda(this);
        PANEL_MASCOTAS = new PanelMascotas(this);
        PANEL_EMPLEADOS = new PanelEmpleados(this);
        PANEL_COMPRAS = new PanelCompras(this);

        setPanelPrincipal(PANELES.PANEL_TIENDA);

        add(panelPrincipal);

        setLocationRelativeTo(null);
    }

    public JPanel crearPanelConBotonDeRegreso(JPanel panelAEnvolver) {
        if (panelConBoton == null) {
            panelConBoton = new JPanel();
            panelConBoton.setLayout(new BorderLayout());
        } else {
            panelConBoton.removeAll();
        }

        // TODO: Por alguna razón el botón de regreso no se quita
        // TODO: Help

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());

        JPanel panelTexto = new JPanel();
        panelTexto.setBorder(new EmptyBorder(12, 0, 0, 0));
        JLabel texto = new JLabel("TIENDA DE MASCOTAS", SwingConstants.CENTER);
        texto.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        texto.setForeground(Color.GRAY);
        panelTexto.add(texto);

        panelNorte.add(new BotonVolver(this), BorderLayout.WEST);
        panelNorte.add(panelTexto, BorderLayout.CENTER);
        panelNorte.add(Box.createRigidArea(new Dimension(125, 0)), BorderLayout.EAST);

        add(panelNorte, BorderLayout.NORTH);

        panelConBoton.add(panelAEnvolver);

        return panelConBoton;
    }

    /**
     * Reemplaza el panelPrincipal por aquel que indica el argumento
     * @param panelAAbrir: Indica cual es el panel que se desea mostrar
     */
    public void setPanelPrincipal(PANELES panelAAbrir) {
        // TODO: Delete this is for debugging
        if (panelPrincipal instanceof PanelMascotas) {
            panelPrincipal.getComponents();
        }
        //TODO:------------------------------

        if (panelPrincipal != null) {
            remove(panelPrincipal);
        }

        switch (panelAAbrir) {
            case PANEL_TIENDA:
                panelPrincipal = PANEL_TIENDA;
                break;
            case PANEL_MASCOTAS:
                panelPrincipal = crearPanelConBotonDeRegreso(PANEL_MASCOTAS);
                break;
            case PANEL_EMPLEADOS:
                panelPrincipal = crearPanelConBotonDeRegreso(PANEL_EMPLEADOS);
                break;
            case PANEL_COMPRAS:
                panelPrincipal = crearPanelConBotonDeRegreso(PANEL_COMPRAS);
                break;
        }

        add(panelPrincipal);

        revalidate();
        repaint();
    }

    public void mostrar() {
        setVisible(true);
    }
}
