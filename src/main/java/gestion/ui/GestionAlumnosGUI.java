package gestion.ui;

import gestion.entity.Alumno;
import gestion.entity.Carrera;
import gestion.entity.Curso;
import gestion.entity.Division;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GestionAlumnosGUI extends JFrame implements ActionListener {

    private JTextArea areaResultados;
    private JButton btnAgregar;
    private JButton btnMostrar;
    private JButton btnBuscar;

    private List<Alumno> listaAlumnos;

    public GestionAlumnosGUI() {
        super("Administración de Alumnos (Swing Avanzado)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        this.listaAlumnos = new ArrayList<>();

        // Componentes UI
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaResultados);

        btnAgregar = new JButton("1. Agregar Nuevo Alumno");
        btnMostrar = new JButton("2. Mostrar Todos");
        btnBuscar = new JButton("3. Buscar por DNI");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnBuscar);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.NORTH);

        btnAgregar.addActionListener(this);
        btnMostrar.addActionListener(this);
        btnBuscar.addActionListener(this);

        setVisible(true);
    }

    private boolean dniExiste(String dni) {
        return listaAlumnos.stream().anyMatch(a -> a.getDni().equals(dni));
    }

    private void agregarAlumno(String nombre, String apellido, int edad,
                               String cursoNombre, String divisionNombre, String carreraNombre, String dni) {

        if (dni.length() < 6) {
            JOptionPane.showMessageDialog(this, "⚠️ El DNI debe tener mínimo 6 dígitos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dniExiste(dni)) {
            JOptionPane.showMessageDialog(this, "⚠️ Ya existe un alumno con el DNI: " + dni, "DNI Duplicado", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear objetos completos con listas vacías
        Division division = new Division(divisionNombre, null, new ArrayList<>());
        Curso curso = new Curso(cursoNombre, null, new ArrayList<>());
        curso.getDivisiones().add(division);       // asignamos la división al curso
        division.setCurso(curso);                  // asignamos el curso a la división

        Carrera carrera = new Carrera(carreraNombre, 0, new ArrayList<>());
        carrera.getCursos().add(curso);            // asignamos el curso a la carrera
        curso.setCarrera(carrera);                 // asignamos la carrera al curso

        // Crear alumno
        Alumno nuevoAlumno = new Alumno(nombre, apellido, edad, dni, carrera, curso, division);
        listaAlumnos.add(nuevoAlumno);
        division.getAlumnos().add(nuevoAlumno);   // agregamos el alumno a la división

        JOptionPane.showMessageDialog(this, "✅ Alumno agregado exitosamente con ID: " + nuevoAlumno.getId(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        mostrarTodosLosAlumnos();
    }

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
                    alumno.getCurso().getNombre() + " " + alumno.getDivision().getNombre(),
                    alumno.getCarrera().getNombre()
            ));
        }
        areaResultados.append("--------------------------------------------------------------------------------------\n");
    }

    private Alumno buscarAlumnoPorDni(String dni) {
        return listaAlumnos.stream().filter(a -> a.getDni().equals(dni)).findFirst().orElse(null);
    }

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

    private void solicitarDatosAgregar() {
        JTextField txtNombre = new JTextField(15);
        JTextField txtApellido = new JTextField(15);
        JTextField txtEdad = new JTextField(5);
        JTextField txtCurso = new JTextField(5);
        JTextField txtDivision = new JTextField(5);
        JTextField txtCarrera = new JTextField(15);
        JTextField txtDni = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Nombre:")); panel.add(txtNombre);
        panel.add(new JLabel("Apellido:")); panel.add(txtApellido);
        panel.add(new JLabel("Edad:")); panel.add(txtEdad);
        panel.add(new JLabel("Curso:")); panel.add(txtCurso);
        panel.add(new JLabel("División:")); panel.add(txtDivision);
        panel.add(new JLabel("Carrera:")); panel.add(txtCarrera);
        panel.add(new JLabel("DNI (min 6 dígitos):")); panel.add(txtDni);

        int result = JOptionPane.showConfirmDialog(this, panel, "Ingrese Datos del Nuevo Alumno", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String dni = txtDni.getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty() || txtEdad.getText().isEmpty() || dni.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int edad = Integer.parseInt(txtEdad.getText().trim());
                if (edad <= 0 || edad > 100) throw new NumberFormatException();
                agregarAlumno(nombre, apellido, edad, txtCurso.getText().trim(), txtDivision.getText().trim(), txtCarrera.getText().trim(), dni);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ La Edad debe ser un número entero válido.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void solicitarDatosBuscar() {
        String dniStr = JOptionPane.showInputDialog(this, "Ingrese el DNI (6 dígitos) del alumno a buscar:", "Buscar Alumno por DNI", JOptionPane.QUESTION_MESSAGE);
        if (dniStr == null || dniStr.trim().isEmpty()) return;

        String dniBuscar = dniStr.trim();
        if (dniBuscar.length() < 6) {
            JOptionPane.showMessageDialog(this, "⚠️ El DNI debe ser mayor a 6 dígitos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno encontrado = buscarAlumnoPorDni(dniBuscar);
        if (encontrado != null) {
            JOptionPane.showMessageDialog(this, "🔎 Alumno encontrado:\n" + encontrado.toString(), "Resultado de Búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Alumno con DNI " + dniBuscar + " no encontrado.", "Resultado de Búsqueda", JOptionPane.WARNING_MESSAGE);
        }
    }

}
