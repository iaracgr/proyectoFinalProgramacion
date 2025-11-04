
package gestion.entity;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * JPanel personalizado que dibuja una imagen de fondo (logo) centrada.
 * Los componentes se agregan normalmente al panel y se dibujan por encima.
 */
public class FondoConLogoPanel extends JPanel {

    private Image backgroundImage;

    private static final String LOGO_FILE = "img/logo_ies.jpg";

    public FondoConLogoPanel() {
        // Carga la imagen del logo en el constructor
        try {
            // Intenta cargar la imagen como un recurso (ruta relativa al paquete)
            backgroundImage = new ImageIcon(getClass().getResource("/" + LOGO_FILE)).getImage();

            // Si eso falla, intenta cargarlo desde el directorio de trabajo (ruta directa)
            if (backgroundImage == null) {
                System.err.println("Advertencia: No se pudo cargar el logo como recurso. Intentando carga directa.");
                backgroundImage = new ImageIcon(LOGO_FILE).getImage();
            }
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen de fondo: " + LOGO_FILE + ". Verifique la ruta.");
            backgroundImage = null;
        }
    }

    /**
     * Sobrescribe el método de pintado para dibujar el fondo antes que los componentes.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 1. Pinta el fondo del JPanel

        // 2. Dibuja la imagen del logo
        if (backgroundImage != null) {
            // Calcula las coordenadas para centrar la imagen en el panel
            int imgWidth = backgroundImage.getWidth(this);
            int imgHeight = backgroundImage.getHeight(this);
            int x = (getWidth() - imgWidth) / 2;
            int y = (getHeight() - imgHeight) / 2;

            // Dibuja la imagen centrada
            g.drawImage(backgroundImage, x, y, this);
        }
    }
}