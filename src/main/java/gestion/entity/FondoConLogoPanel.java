
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

    private static final String LOGO_FILE = "/img/logo_ies.jpg";

    public FondoConLogoPanel() {
        try {
            // 1. Intenta cargar desde los recursos del classpath
            java.net.URL url = getClass().getResource(LOGO_FILE);
            if (url != null) {
                backgroundImage = new ImageIcon(url).getImage();
                System.out.println("✅ Logo cargado desde recursos.");
            } else {
                // 2. Si no está en classpath, intenta ruta absoluta o relativa al proyecto
                System.err.println("⚠️ No se encontró /img/logo_ies.jpg en el classpath. Intentando ruta directa...");
                backgroundImage = new ImageIcon("src/main/resources/img/logo_ies.jpg").getImage();
            }
        } catch (Exception e) {
            System.err.println("❌ Error al cargar el logo: " + e.getMessage());
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