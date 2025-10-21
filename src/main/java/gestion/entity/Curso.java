package gestion.entity;

import java.util.List;
import java.util.UUID;

public class Curso {

    private final String id;

    String nombre; // "3ro"
    Carrera carrera;
    List<Division> divisiones;

    public Curso(String nombre, Carrera carrera, List<Division> divisiones) {
        this.id = UUID.randomUUID().toString(); // Genera un ID único automáticamente
        this.nombre = nombre;
        this.carrera = carrera;
        this.divisiones = divisiones;
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

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public List<Division> getDivisiones() {
        return divisiones;
    }

    public void setDivisiones(List<Division> divisiones) {
        this.divisiones = divisiones;
    }
}
