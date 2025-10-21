// Archivo: Alumno.java
package gestion.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Alumno {

    private final String id;          // UUID autogenerado
    private String nombre;
    private String apellido;
    private int edad;
    private String dni;
    private Carrera carrera;          // Relación con Carrera
    private Curso curso;              // Relación con Curso
    private Division division;        // Relación con Division

    // Listas para relacionar al alumno con otras entidades
    private List<Inscripcion> inscripciones;
    private List<Nota> notas;
    private List<Asistencia> asistencias;

    public Alumno(String nombre, String apellido, int edad, String dni,
                  Carrera carrera, Curso curso, Division division) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.carrera = carrera;
        this.curso = curso;
        this.division = division;

        // Inicializamos las listas vacías
        this.inscripciones = new ArrayList<>();
        this.notas = new ArrayList<>();
        this.asistencias = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public Carrera getCarrera() { return carrera; }
    public void setCarrera(Carrera carrera) { this.carrera = carrera; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    public Division getDivision() { return division; }
    public void setDivision(Division division) { this.division = division; }

    public List<Inscripcion> getInscripciones() { return inscripciones; }
    public List<Nota> getNotas() { return notas; }
    public List<Asistencia> getAsistencias() { return asistencias; }

    // --- Métodos para agregar relaciones ---
    public void agregarInscripcion(Inscripcion inscripcion) {
        this.inscripciones.add(inscripcion);
    }

    public void agregarNota(Nota nota) {
        this.notas.add(nota);
    }

    public void agregarAsistencia(Asistencia asistencia) {
        this.asistencias.add(asistencia);
    }

    @Override
    public String toString() {
        return String.format(
                "Alumno[id=%s, DNI=%s, Nombre=%s %s, Edad=%d, Carrera=%s, Curso=%s, División=%s]",
                id,
                dni,
                nombre,
                apellido,
                edad,
                carrera != null ? carrera.getNombre() : "No asignada",
                curso != null ? curso.getNombre() : "No asignado",
                division != null ? division.getNombre() : "No asignada"
        );
    }
}