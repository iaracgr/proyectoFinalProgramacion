package gestion;

import gestion.ui.GestionAlumnosGUI;

public class Main {
    public static void main(String[] args) {
          // Inicializa la GUI en el hilo de Swing
        javax.swing.SwingUtilities.invokeLater(() -> new GestionAlumnosGUI());
    }
}