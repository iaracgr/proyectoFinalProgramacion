package gestion.entity;

import java.util.List;
import java.util.UUID;

public class Division {
    private final String id;
        String nombre; // "A"
        Curso curso;
        List<Alumno> alumnos;

    public Division(String nombre, Curso curso, List<Alumno> alumnos) {
        this.id = UUID.randomUUID().toString(); // Genera un ID único automáticamente
        this.nombre = nombre;
        this.curso = curso;
        this.alumnos = alumnos;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
