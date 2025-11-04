package gestion.ui;

// Imports de entidades
import gestion.entity.Alumno;
import gestion.entity.FondoConLogoPanel; // ¡Asegúrate de importar el panel de fondo!

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
 * Incluye Fondo con Logo (FondoConLogoPanel) y JComboBox para Curso y División.
 */
public class GestionAlumnosGUI extends JFrame implements ActionListener {

    // --- Componentes de la Interfaz ---
    private JTextArea areaResultados;
    private JButton btnAgregar;
    private JButton btnMostrar;
    private JButton btnBuscar;

    // --- Lógica de Datos ---
    private List<Alumno> listaAlumnos;
    private int siguienteId = 1; // Contador para asignar IDs internos

    public GestionAlumnosGUI() {
        super("Administración de Alumnos IES 9-008");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        this.listaAlumnos = new ArrayList<>();

        // 1. Crear el panel de fondo e imagen (FondoConLogoPanel)
        FondoConLogoPanel fondoPanel = new FondoConLogoPanel();
        fondoPanel.setLayout(new BorderLayout()); // Necesario para organizar componentes encima

        // 2. Componentes UI
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaResultados);

        btnAgregar = new JButton("1. Agregar Nuevo Alumno");
        btnMostrar = new JButton("2. Mostrar Todos");
        btnBuscar = new JButton("3. Buscar por DNI");

        // Panel de Botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnBuscar);

        // 3. Distribución de la Ventana: Añadir componentes al fondoPanel
        fondoPanel.add(scrollPane, BorderLayout.CENTER); // Centro: área de resultados
        fondoPanel.add(panelBotones, BorderLayout.NORTH); // Norte: botones

        // 4. Establecer el fondoPanel como el contenido principal del JFrame
        setContentPane(fondoPanel);

        // 5. Registro de Listeners
        btnAgregar.addActionListener(this);
        btnMostrar.addActionListener(this);
        btnBuscar.addActionListener(this);

        setVisible(true);
    }

    // -----------------------------------------------------------------
    // MÉTODOS DE LÓGICA Y DATOS
    // -----------------------------------------------------------------

    /** Verifica si un DNI ya existe en la lista de alumnos. */
    private boolean dniExiste(String dni) {
        return listaAlumnos.stream().anyMatch(a -> a.getDni().equals(dni));
    }

    /**
     * Agrega un nuevo alumno
     */
    private void agregarAlumno(String nombre, String apellido, int edad,
                               String carrera, String division, String curso, String dni) {

        // 1. Validaciones
        if (dni.length() < 6) {
            JOptionPane.showMessageDialog(this, "⚠️ El DNI debe tener mínimo 6 dígitos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (dniExiste(dni)) {
            JOptionPane.showMessageDialog(this, "⚠️ Ya existe un alumno con el DNI: " + dni, "DNI Duplicado", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Creación del Alumno
        Alumno nuevoAlumno = new Alumno(siguienteId++, nombre, apellido, edad, dni, carrera, curso, division);
        listaAlumnos.add(nuevoAlumno);

        JOptionPane.showMessageDialog(this, "✅ Alumno agregado: " + nombre + " " + apellido + " (ID: " + nuevoAlumno.getId() + ")", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        mostrarTodosLosAlumnos();
    }

    /** Muestra todos los alumnos en el área de resultados. */
    private void mostrarTodosLosAlumnos() {
        areaResultados.setText("");
        areaResultados.append("--- LISTA DE ALUMNOS REGISTRADOS (" + listaAlumnos.size() + ") ---\n");

        if (listaAlumnos.isEmpty()) {
            areaResultados.append("❌ No hay alumnos registrados todavía.\n");
            return;
        }

        areaResultados.append(String.format("%-4s | %-10s | %-20s | %-5s | %-10s | %-30s\n",
                "ID", "DNI", "NOMBRE COMPLETO", "EDAD", "CURSO", "CARRERA"));
        areaResultados.append("--------------------------------------------------------------------------------------\n");

        for (Alumno alumno : listaAlumnos) {
            areaResultados.append(String.format("%-4d | %-10s | %-20s | %-5d | %-10s | %-30s\n",
                    alumno.getId(),
                    alumno.getDni(),
                    alumno.getNombre() + " " + alumno.getApellido(),
                    alumno.getEdad(),
                    alumno.getCurso()+ " " + alumno.getDivision(),
                    alumno.getCarrera()
            ));
        }
        areaResultados.append("--------------------------------------------------------------------------------------\n");
    }

    /** Busca un alumno por DNI. */
    private Alumno buscarAlumnoPorDni(String dni) {
        return listaAlumnos.stream().filter(a -> a.getDni().equals(dni)).findFirst().orElse(null);
    }

    // -----------------------------------------------------------------
    // MANEJO DE EVENTOS Y DIÁLOGOS
    // -----------------------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            solicitarDatosAgregar();
        } else if (e.getSource() == btnMostrar) {
            mostrarTodosLosAlumnos();
        } else if (e.getSource() == btnBuscar) {
            solicitarDatosBuscar();
        }
    }

    /** Muestra el diálogo para agregar alumno, utilizando JComboBox. */
    private void solicitarDatosAgregar() {
        // --- JText Fields ---
        JTextField txtNombre = new JTextField(15);
        JTextField txtApellido = new JTextField(15);
        JTextField txtEdad = new JTextField(5);
        JTextField txtCarrera = new JTextField(15);
        JTextField txtDni = new JTextField(10);

        // --- JComboBox (Desplegables) ---
        String[] opcionesCurso = {"Primero", "Segundo", "Tercero"};
        JComboBox<String> cmbCurso = new JComboBox<>(opcionesCurso);

        String[] opcionesDivision = {"Primera", "Segunda", "Tercera"};
        JComboBox<String> cmbDivision = new JComboBox<>(opcionesDivision);

        String[] opcionesCarrera = {"Software", "Ciberseguridad", "Analísis de Datos"};
        JComboBox<String> cmCarrera = new JComboBox<>(opcionesCarrera);

        // --- Panel de Formulario ---
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Nombre:")); panel.add(txtNombre);
        panel.add(new JLabel("Apellido:")); panel.add(txtApellido);
        panel.add(new JLabel("Edad:")); panel.add(txtEdad);
        panel.add(new JLabel("Curso:")); panel.add(cmbCurso);        // Desplegable
        panel.add(new JLabel("División:")); panel.add(cmbDivision);  // Desplegable
        panel.add(new JLabel("Carrera:")); panel.add(cmCarrera); //Desplegable
        panel.add(new JLabel("DNI (min 6 dígitos):")); panel.add(txtDni);

        int result = JOptionPane.showConfirmDialog(this, panel, "Ingrese Datos del Nuevo Alumno", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String dni = txtDni.getText().trim();
            String carrera = txtCarrera.getText().trim();

            String cursoSeleccionado = (String) cmbCurso.getSelectedItem();
            String divisionSeleccionada = (String) cmbDivision.getSelectedItem();

            // Validación de campos vacíos
            if (nombre.isEmpty() || apellido.isEmpty() || txtEdad.getText().isEmpty() || dni.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Validación de Edad
                int edad = Integer.parseInt(txtEdad.getText().trim());
                if (edad <= 0 || edad > 100) throw new NumberFormatException();

                // Llama al método de agregar
                agregarAlumno(nombre, apellido, edad,
                        cursoSeleccionado, divisionSeleccionada, carrera, dni);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ La Edad debe ser un número entero válido.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** Muestra el diálogo para buscar alumno por DNI. */
    private void solicitarDatosBuscar() {
        String dniStr = JOptionPane.showInputDialog(this, "Ingrese el DNI (mínimo 6 dígitos) del alumno a buscar:", "Buscar Alumno por DNI", JOptionPane.QUESTION_MESSAGE);
        if (dniStr == null || dniStr.trim().isEmpty()) return;

        String dniBuscar = dniStr.trim();
        if (dniBuscar.length() < 6) {
            JOptionPane.showMessageDialog(this, "⚠️ El DNI debe tener mínimo 6 dígitos para la búsqueda.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno encontrado = buscarAlumnoPorDni(dniBuscar);
        if (encontrado != null) {
            JOptionPane.showMessageDialog(this, "🔎 Alumno encontrado:\n" + encontrado.toString(), "Resultado de Búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Alumno con DNI " + dniBuscar + " no encontrado.", "Resultado de Búsqueda", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new GestionAlumnosGUI());
    }
}