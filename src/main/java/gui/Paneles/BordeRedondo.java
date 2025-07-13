package gui.Paneles;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * Permite dar un borde redondo a un componente y que se muestre el fondo
 */
public class BordeRedondo extends AbstractBorder {
    private final int radio;
    private final Color colorDeFondo;

    public BordeRedondo(Color colorDeFondo, int radio) {
        this.colorDeFondo = colorDeFondo;
        this.radio = radio;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorDeFondo);
        //g2.setStroke(new java.awt.BasicStroke(100));
        g2.fillRoundRect(x, y, width - 1, height - 1, radio, radio);
        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radio, radio, radio, radio);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = radio;
        return insets;
    }
}
