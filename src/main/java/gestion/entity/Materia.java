package gestion.entity;

import java.util.UUID;

public class Materia {
    private final String id;
    String nombre; // "Matemática"
        Curso curso;
        String profesor;

    public Materia(String nombre, String profesor, Curso curso) {
        this.id = UUID.randomUUID().toString(); // Genera un ID único automáticamente
        this.nombre = nombre;
        this.profesor = profesor;
        this.curso = curso;
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

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getId() {
        return id;
    }
}
