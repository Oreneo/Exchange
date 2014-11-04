package il.co.orentomer.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * @author Oren Nahum and Tomer Berger
 *
 */

/**
 * 
 * ImagePanel class, overriding functions in order to enable presenting of jpeg file as 
 * background for the panel in Gui class
 */

public class ImagePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    Image imageOrg = null;

    public ImagePanel(Image image) {
        imageOrg = image;
        setOpaque(false);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(imageOrg.getWidth(this), imageOrg.getHeight(this));
    }

    /**
     * overrides the method for displaying the image in background
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imageOrg != null) {
            g.drawImage(imageOrg, 0, 0, getWidth(), getHeight(), this);
        }
    }
}