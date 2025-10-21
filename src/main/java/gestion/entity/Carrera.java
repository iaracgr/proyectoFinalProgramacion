package gestion.entity;

import java.util.List;
import java.util.UUID;

public class Carrera {

    private final String id;
    String nombre;
    int duracion;
    List<Curso> cursos;

    public Carrera( String nombre, int duracion, List<Curso> cursos) {
        this.id = UUID.randomUUID().toString(); // Genera un ID único automáticamente
        this.nombre = nombre;
        this.duracion = duracion;
        this.cursos = cursos;
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
