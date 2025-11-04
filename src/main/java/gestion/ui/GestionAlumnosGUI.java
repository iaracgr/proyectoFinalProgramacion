package gestion.ui;

// Imports de entidades
import gestion.entity.Alumno;
import gestion.entity.FondoConLogoPanel;

// Imports de Swing y Java
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Interfaz Gráfica para la Gestión de Alumnos.
 * Incluye Fondo con Logo (FondoConLogoPanel) y JComboBox para Curso, División y Carrera.
 */
public class GestionAlumnosGUI extends JFrame implements ActionListener {

    // --- Componentes de la Interfaz ---
    private JTextArea areaResultados;
    private JButton btnAgregar;
    private JButton btnMostrar;
    private JButton btnBuscarDni;
    private JButton btnBuscarCurso;
    private JButton btnBuscarDivision;
    private JButton btnBuscarCarrera;

    // --- Lógica de Datos ---
    private List<Alumno> listaAlumnos;
    private int siguienteId = 1; // Contador para asignar IDs internos

    public GestionAlumnosGUI() {
        super("Administración de Alumnos IES 9-008");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 550);

        this.listaAlumnos = new ArrayList<>();
        // Precargar alumnos
        listaAlumnos.add(new Alumno(siguienteId++, "Jane", "Doe", 24, "12345678", "Software", "Primero", "Primera"));
        listaAlumnos.add(new Alumno(siguienteId++, "Agus", "Peralta", 23, "23456789", "Ciberseguridad", "Segundo", "Segunda"));
        listaAlumnos.add(new Alumno(siguienteId++, "Valentina", "Perez", 22, "34567890", "Analísis de Datos", "Tercero", "Tercera"));
        listaAlumnos.add(new Alumno(siguienteId++, "Martin", "Rojas", 25, "45678901", "Software", "Segundo", "Primera"));
        listaAlumnos.add(new Alumno(siguienteId++, "Lucia", "Fernandez", 21, "56789012", "Ciberseguridad", "Primero", "Segunda"));

        // 1. Panel de fondo con logo
        FondoConLogoPanel fondoPanel = new FondoConLogoPanel();
        fondoPanel.setLayout(new BorderLayout());

        // 2. Área de resultados
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setOpaque(false);
        areaResultados.setForeground(java.awt.Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(areaResultados);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // 3. Botones principales
        btnAgregar = new JButton("1. Agregar Nuevo Alumno");
        btnMostrar = new JButton("2. Mostrar Todos");
        btnBuscarDni = new JButton("3. Buscar por DNI");
        btnBuscarCurso = new JButton("4. Filtrar por Curso");
        btnBuscarDivision = new JButton("5. Filtrar por División");
        btnBuscarCarrera = new JButton("6. Filtrar por Carrera");

        // Panel de Botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 10, 5));
        panelBotones.setOpaque(false);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnBuscarDni);
        panelBotones.add(btnBuscarCurso);
        panelBotones.add(btnBuscarDivision);
        panelBotones.add(btnBuscarCarrera);

        fondoPanel.add(panelBotones, BorderLayout.NORTH);
        fondoPanel.add(scrollPane, BorderLayout.CENTER);
        setContentPane(fondoPanel);

        // 4. Eventos
        btnAgregar.addActionListener(this);
        btnMostrar.addActionListener(this);
        btnBuscarDni.addActionListener(this);
        btnBuscarCurso.addActionListener(this);
        btnBuscarDivision.addActionListener(this);
        btnBuscarCarrera.addActionListener(this);

        fondoPanel.revalidate();
        fondoPanel.repaint();
        setVisible(true);
    }

    // -----------------------------------------------------------------
    // MÉTODOS DE LÓGICA Y DATOS
    // -----------------------------------------------------------------

    private boolean dniExiste(String dni) {
        return listaAlumnos.stream().anyMatch(a -> a.getDni().equals(dni));
    }

    private void agregarAlumno(String nombre, String apellido, int edad,
                               String carrera, String division, String curso, String dni) {

        if (dni.length() < 6) {
            JOptionPane.showMessageDialog(this, "⚠️ El DNI debe tener mínimo 6 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (dniExiste(dni)) {
            JOptionPane.showMessageDialog(this, "⚠️ Ya existe un alumno con el DNI: " + dni, "DNI duplicado", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno nuevoAlumno = new Alumno(siguienteId++, nombre, apellido, edad, dni, carrera, curso, division);
        listaAlumnos.add(nuevoAlumno);

        JOptionPane.showMessageDialog(this, "✅ Alumno agregado: " + nombre + " " + apellido, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        mostrarTodosLosAlumnos();
    }

    private void mostrarTodosLosAlumnos() {
        areaResultados.setText("");
        areaResultados.append("--- LISTA DE ALUMNOS REGISTRADOS (" + listaAlumnos.size() + ") ---\n");

        if (listaAlumnos.isEmpty()) {
            areaResultados.append("❌ No hay alumnos registrados todavía.\n");
            return;
        }

        areaResultados.append(String.format("%-4s | %-10s | %-20s | %-5s | %-15s | %-10s | %-20s\n",
                "ID", "DNI", "NOMBRE COMPLETO", "EDAD", "CURSO", "DIVISIÓN", "CARRERA"));
        areaResultados.append("------------------------------------------------------------------------------------------\n");

        for (Alumno a : listaAlumnos) {
            areaResultados.append(String.format("%-4d | %-10s | %-20s | %-5d | %-15s | %-10s | %-20s\n",
                    a.getId(), a.getDni(), a.getNombre() + " " + a.getApellido(),
                    a.getEdad(), a.getCurso(), a.getDivision(), a.getCarrera()));
        }
    }

    private Alumno buscarAlumnoPorDni(String dni) {
        return listaAlumnos.stream().filter(a -> a.getDni().equals(dni)).findFirst().orElse(null);
    }

    // -----------------------------------------------------------------
    // FILTROS
    // -----------------------------------------------------------------
    private void filtrarPorCurso() {
        String[] opciones = {"Primero", "Segundo", "Tercero"};
        String curso = (String) JOptionPane.showInputDialog(this, "Seleccione un curso:",
                "Filtrar por Curso", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (curso != null) mostrarFiltrado("Curso: " + curso, listaAlumnos.stream().filter(a -> a.getCurso().equalsIgnoreCase(curso)).toList());
    }

    private void filtrarPorDivision() {
        String[] opciones = {"Primera", "Segunda", "Tercera"};
        String division = (String) JOptionPane.showInputDialog(this, "Seleccione una división:",
                "Filtrar por División", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (division != null) mostrarFiltrado("División: " + division, listaAlumnos.stream().filter(a -> a.getDivision().equalsIgnoreCase(division)).toList());
    }

    private void filtrarPorCarrera() {
        String[] opciones = {"Software", "Ciberseguridad", "Analísis de Datos"};
        String carrera = (String) JOptionPane.showInputDialog(this, "Seleccione una carrera:",
                "Filtrar por Carrera", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (carrera != null) mostrarFiltrado("Carrera: " + carrera, listaAlumnos.stream().filter(a -> a.getCarrera().equalsIgnoreCase(carrera)).toList());
    }

    private void mostrarFiltrado(String titulo, List<Alumno> filtrados) {
        areaResultados.setText("");
        areaResultados.append("🔍 Resultado del filtro - " + titulo + "\n");
        areaResultados.append("--------------------------------------------------------------\n");

        if (filtrados.isEmpty()) {
            areaResultados.append("❌ No se encontraron alumnos.\n");
            return;
        }

        areaResultados.append(String.format("%-4s | %-10s | %-20s | %-5s | %-15s | %-10s | %-20s\n",
                "ID", "DNI", "NOMBRE COMPLETO", "EDAD", "CURSO", "DIVISIÓN", "CARRERA"));
        areaResultados.append("------------------------------------------------------------------------------------------\n");

        for (Alumno a : filtrados) {
            areaResultados.append(String.format("%-4d | %-10s | %-20s | %-5d | %-15s | %-10s | %-20s\n",
                    a.getId(), a.getDni(), a.getNombre() + " " + a.getApellido(),
                    a.getEdad(), a.getCurso(), a.getDivision(), a.getCarrera()));
        }
    }

    // -----------------------------------------------------------------
    // EVENTOS
    // -----------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == btnAgregar) {
            solicitarDatosAgregar();
        } else if (src == btnMostrar) {
            mostrarTodosLosAlumnos();
        } else if (src == btnBuscarDni) {
            solicitarDatosBuscar();
        } else if (src == btnBuscarCurso) {
            filtrarPorCurso();
        } else if (src == btnBuscarDivision) {
            filtrarPorDivision();
        } else if (src == btnBuscarCarrera) {
            filtrarPorCarrera();
        }
    }

    // -----------------------------------------------------------------
    private void solicitarDatosAgregar() {
        JTextField txtNombre = new JTextField(15);
        JTextField txtApellido = new JTextField(15);
        JTextField txtEdad = new JTextField(5);
        JTextField txtDni = new JTextField(10);

        String[] opcionesCurso = {"Primero", "Segundo", "Tercero"};
        JComboBox<String> cmbCurso = new JComboBox<>(opcionesCurso);

        String[] opcionesDivision = {"Primera", "Segunda", "Tercera"};
        JComboBox<String> cmbDivision = new JComboBox<>(opcionesDivision);

        String[] opcionesCarrera = {"Software", "Ciberseguridad", "Analísis de Datos"};
        JComboBox<String> cmbCarrera = new JComboBox<>(opcionesCarrera);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Nombre:")); panel.add(txtNombre);
        panel.add(new JLabel("Apellido:")); panel.add(txtApellido);
        panel.add(new JLabel("Edad:")); panel.add(txtEdad);
        panel.add(new JLabel("Curso:")); panel.add(cmbCurso);
        panel.add(new JLabel("División:")); panel.add(cmbDivision);
        panel.add(new JLabel("Carrera:")); panel.add(cmbCarrera);
        panel.add(new JLabel("DNI:")); panel.add(txtDni);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Nuevo Alumno",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                int edad = Integer.parseInt(txtEdad.getText().trim());
                String curso = (String) cmbCurso.getSelectedItem();
                String division = (String) cmbDivision.getSelectedItem();
                String carrera = (String) cmbCarrera.getSelectedItem();
                String dni = txtDni.getText().trim();

                if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "⚠️ Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                agregarAlumno(nombre, apellido, edad, carrera, division, curso, dni);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void solicitarDatosBuscar() {
        String dni = JOptionPane.showInputDialog(this, "Ingrese el DNI:", "Buscar Alumno", JOptionPane.QUESTION_MESSAGE);
        if (dni == null || dni.trim().isEmpty()) return;

        Alumno encontrado = buscarAlumnoPorDni(dni.trim());
        areaResultados.setText("");

        if (encontrado != null) {
            mostrarFiltrado("DNI: " + dni, List.of(encontrado));
        } else {
            areaResultados.append("❌ Alumno con DNI " + dni + " no encontrado.\n");
        }
    }

}
